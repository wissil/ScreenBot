package com.util.ai.screenbot.main.automata.states;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.email.EmailSender;

import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

public class CleanBetState extends VBState {
	
	private static final Logger log = LoggerFactory.getLogger(CleanBetState.class);
	
	private static final Bookie NO_BOOKIE = null;
	
	private final String participants;
	
	private final Bookie bookie;
	
	public CleanBetState(InputHandler in, OutputHandler out, EmailSender email, 
			String participants, Bookie bookie) {
		super(in, out, email);
		this.participants = Objects.requireNonNull(participants);
		this.bookie = bookie;
	}
	
	public CleanBetState(InputHandler in, OutputHandler out, EmailSender email,
			String participants) {
		this(in, out, email, participants, NO_BOOKIE);
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
	void execute() throws InterruptedException, FatalValueBettingException {
		if (!bookie.equals(NO_BOOKIE)) {
			// remove from the betting slip
			log.debug("Bookmaker was interpeted! Removing bet from the bookmaker betting slip ...");
			in.removeBet(bookie);
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

		Thread.sleep(500);

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
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			sendEmail();
		}
		
		new IdleState(in, out, email).process();
	}

}
