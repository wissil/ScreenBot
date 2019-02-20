package com.util.ai.screenbot.main.automata.states;

import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.bookie.core.gui.elements.VBSingleBetElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public class CleanBetState extends VBState {

	private static final Logger log = LoggerFactory.getLogger(CleanBetState.class);

	private static final Bookie NO_BOOKIE = null;

	private final String participants;

	private final Bookie bookie;

	private final boolean removeBet;

	public CleanBetState(InputHandler in, OutputHandler out, EmailSender email, String participants, Bookie bookie,
			boolean removeBet) {
		super(in, out, email);
		this.participants = Objects.requireNonNull(participants);
		this.bookie = bookie;
		this.removeBet = removeBet;
	}

	public CleanBetState(InputHandler in, OutputHandler out, EmailSender email, String participants,
			boolean removeBet) {
		this(in, out, email, participants, NO_BOOKIE, removeBet);
	}

	@Override
	void onEnter() {
		log.debug("Entered CLEAN_BET state!");
	}

	@Override
	void onExit() {
		log.debug("Exiting CLEAN BET state...");
	}

	@Override
	void execute() throws InterruptedException, FatalVBException {
		if (bookieExists() && removeBet) {
			// remove from the betting slip
			log.debug("Removing bet from the bookmaker betting slip ...");
			try {
				in.removeBet(bookie);
			} catch (InvalidBetSlipException e) {
				log.warn("Bet slip empty -- no remove all button found!");
			}
			log.debug("Bet removed from the slip");
		}

		// 2) click Cancel
		Thread.sleep(1000);
		log.debug("Clicking cancel at the betting browser ...");
		in.clickCancelAtBettingBrowser();

		// 3) go to main screen
		Thread.sleep(1000);
		log.debug("Opening main betting window ...");
		in.openMainWindow();

		Thread.sleep(800);

		// click and remove all events on the main screen
		// if the top event is the same as the failed event (input event)
		log.debug("Removing all bets of the failed event from the main window ...");
		try {
			final BufferedImage singleBetImage = in.getSingleBetImage();
			final VBSingleBetElement element = out.readSingleBet(singleBetImage);
			final String actualParticipants = element.getParticipants();

			if (consideredEqual(actualParticipants, participants)) {
				in.removeAllBetsFromTopBetEvent();
			}
		} catch (VBElementInterpretationException e) {
			log.warn("Interpretation failed. Possibly no single bet left to clean.", e);
			new IdleState(in, out, email).process();
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			sendEmail();
		}

		new IdleState(in, out, email).process();
	}

	private boolean bookieExists() {
		return bookie != NO_BOOKIE;
	}

}
