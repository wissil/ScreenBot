package com.util.ai.screenbot.main.automata.states;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.main.bookie.UnknownBookieException;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.email.EmailSender;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class PlaceBetState extends VBState {

	private static final Logger log = LoggerFactory.getLogger(PlaceBetState.class);

	private static final int BET_BROWSER_LOAD_TIMEOUT_SEC = 30;

	private static final int BET_BROWSER_LOAD_PERIOD_MS = 1_000;

	private final VBSingleBetElement element;

	public PlaceBetState(InputHandler in, OutputHandler out, EmailSender email, VBSingleBetElement element) {
		super(in, out, email);
		this.element = Objects.requireNonNull(element);
	}

	@Override
	void onEnter() {
		log.debug("Entered PLACE_BET state!");
	}

	@Override
	void onExit() {
		log.debug("Exiting PLACE_BET state...");
	}

	@Override
	void execute() throws InterruptedException, FatalValueBettingException {
		// 1) click bet on top
		in.clickBetOnTopEvent();

		// 2) go to betting browser
		in.openBettingBrowserWindow();
		Thread.sleep(1000);

		// 3) wait for betting browser to load
		waitForBettingBrowserToLoad();

		// 4) parse from the element
		final String participants = element.getParticipants();
		final Bookie bookie = parseBookie(element.getBookie(), participants);

		try {
			in.intializeBookieBot(bookie);
			in.checkBettingSlip(bookie);
			in.clickNeutralArea(bookie);

			Thread.sleep(500);

			final BufferedImage oddsInputImage = in.getOddsInputImage();
			final BufferedImage placeBetImage = in.getBookmakerOddsImage(bookie);

			final VBBetInfoElement oddsInput = out.readBetInfo(oddsInputImage);
			final VBBookmakerOddsElement placeBet = out.readBookmakerOdds(placeBetImage, bookie);
			final VBBalanceElement balanceElement = out.readBalance(in.getBalanceStakeImage(bookie), bookie);
			final VBBookmakerMaxStakeElement maxStakeElement = out.readMaxStake(in.getMaxStakeImage(bookie), bookie);
			final VBBookmakerMinStakeElement minStakeElement = out.readMinStake(in.getMinStakeImage(bookie), bookie);

			final double oddsLeft = oddsInput.getOdds();
			final double oddsRight = CustomNumberFormat.parseDouble(placeBet.getOdds());

			final double stake = oddsInput.getStake();

			if (isBetPlacable(oddsRight, oddsLeft, stake, bookie, balanceElement, maxStakeElement, minStakeElement)) {
				// 1) place bet
				// in.placeBet(bookie, stake);
				in.placeBet(bookie, 1); // FIXME - stake hardcoded to 1
				Thread.sleep(500);

				// 2) click OK on the betting browser
				in.clickOKAtBettingBrowser();
				Thread.sleep(500);

				// 3) log bet
				new LogBetState(in, out, email).process();

				// 4) go to main window
				in.openMainWindow();
			} else {
				log.warn("Bet could not be placed!");
				new CleanBetState(in, out, email, participants, bookie, true).process();
			}
		} catch (VBElementInterpretationException e) {
			// TODO: handle exception
			sendEmail();
			new CleanBetState(in, out, email, participants, true).process();
		} catch (BetNotFoundException e) {
			new CleanBetState(in, out, email, participants, false).process();
		} catch (BetSlipException e) {
			new CleanBetState(in, out, email, participants, bookie, true).process();
		} catch (Exception e) {
			// any other exception
			log.error("Unknown exception has occurred.", e);
			log.error("Shutting down ...");
			sendEmail();
			System.exit(-1);
		}
	}

	private void waitForBettingBrowserToLoad() throws InterruptedException {
		try {
			int waitingTime = 0;
			while (true) {
				final BufferedImage browsingStatusImage = in.getBrowsingStatusImage();
				if (out.readBrowsingStatus(browsingStatusImage).isDone()) {
					log.debug("Betting browser successfully loaded! - first check");
					Thread.sleep(2000);

					// added second check because of the false true that sometimes happens
					final BufferedImage browsingStatusImage2 = in.getBrowsingStatusImage();
					if (out.readBrowsingStatus(browsingStatusImage2).isDone()) {
						break;
					}
				}

				if (waitingTime == BET_BROWSER_LOAD_TIMEOUT_SEC) {
					log.warn(String.format("Betting browser didn't load in %d seconds.", BET_BROWSER_LOAD_TIMEOUT_SEC));
					log.warn("Timeout!");
					// clean bets
				}

				Thread.sleep(BET_BROWSER_LOAD_PERIOD_MS);
				waitingTime += 1;
			}
		} catch (VBElementInterpretationException e) {
			log.error("Couldn't interpret browsing status image.", e);
			sendEmail();
		}
	}

	private Bookie parseBookie(String bookieName, String participants)
			throws InterruptedException, FatalValueBettingException {
		try {
			return Bookie.fromString(bookieName);
		} catch (UnknownBookieException e) {
			log.error("Bookmaker couldn't be recognized.", e);
			sendEmail();
			new CleanBetState(in, out, email, participants, false).process();
		}

		return null;
	}

	private boolean isBetPlacable(double oddsRight, double oddsLeft, double stake, Bookie bookie,
			VBBalanceElement balanceElement, VBBookmakerMaxStakeElement maxStakeElement,
			VBBookmakerMinStakeElement minStakeElement) {
		return (oddsRight >= oddsLeft) && in.isBetPlaceable(bookie, stake, balanceElement.getBalance(),
				maxStakeElement.getStake(), minStakeElement.getStake());
	}

}
