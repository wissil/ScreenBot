package com.util.ai.screenbot.main.automata.states;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
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
	void execute() throws InterruptedException, FatalVBException {
		// 1) click bet on top
		in.clickBetOnTopEvent();

		// 2) go to betting browser
		in.openBettingBrowserWindow();
		Thread.sleep(1000);

		// 3) parse from the element
		final String participants = element.getParticipants();
		final Bookie bookie = parseBookie(element.getBookie(), participants);

		try {
			in.waitForBettingBrowserToLoad();
			log.debug("Betting browser successfully loaded!");
			
			in.checkBettingSlip(bookie);

			log.debug("Betting slip successfully checked!");

			Thread.sleep(500);

			final BufferedImage oddsInputImage = in.getOddsInputImage();
			final BufferedImage oddsImage = in.getBookmakerOddsImage(bookie);

			final VBBetInfoElement oddsInput = out.readBetInfo(oddsInputImage);
			final VBBookmakerOddsElement placeBet = out.readBookmakerOdds(oddsImage, bookie);
			final VBBalanceElement balanceElement = out.readBalance(in.getBalanceStakeImage(bookie), bookie);

			final VBBookmakerMaxStakeElement maxStakeElement = out.readMaxStake(in.getMaxStakeImage(bookie), bookie);
			final VBBookmakerMinStakeElement minStakeElement = out.readMinStake(in.getMinStakeImage(bookie), bookie);

			final double oddsLeft = oddsInput.getOdds();
			final double oddsRight = CustomNumberFormat.parseDouble(placeBet.getOdds());

			double stake = oddsInput.getStake();
			stake = 0.5;// FIXEME - testing purposes

			log.debug("All the data successfully parsed!");
			log.debug("Checking if the bet could be placed!");
			if (isBetPlacable(oddsRight, oddsLeft, stake, bookie, balanceElement, maxStakeElement, minStakeElement)) {
				log.debug("Bet can be placable!");

				// 1) place bet
				// in.placeBet(bookie, stake);
				log.debug("Placing the bet ...");
				in.placeBet(bookie, stake);
				Thread.sleep(500);
				log.debug("Bet successfully placed!");

				// 2) click OK on the betting browser

				log.debug("Clicking OK on the betting browser!");
				in.clickOKAtBettingBrowser();
				log.debug("OK clicked!");
				Thread.sleep(500);

				// 3) log bet
				new LogBetState(in, out, email).process();

				// 4) go to main window
				log.debug("Opening main window ...");
				in.openMainWindow();
				log.debug("Main window opened!");
			} else {
				log.warn("Bet could not be placed!");
				new CleanBetState(in, out, email, participants, bookie, true).process();
			}
		} catch (VBElementInterpretationException e) {
			// TODO: handle exception
			log.error("Element not interpreted!", e);
			sendEmail();
			new CleanBetState(in, out, email, participants, bookie, true).process();
		} catch (BetNotFoundException e) {
			log.warn("Bet not found!", e);
			new CleanBetState(in, out, email, participants, bookie, true).process();
		} catch (InvalidBetSlipException e) {
			log.warn("Bet slip error!", e);
			new CleanBetState(in, out, email, participants, bookie, true).process();
		} catch (BettingBrowserTimeoutException e) {
			log.warn("Betting browser timeout!", e);
			new CleanBetState(in, out, email, participants, bookie, true).process();
		} catch (Exception e) {
			// any other exception
			log.error("Unknown exception has occurred.", e);
			log.error("Shutting down ...");
			sendEmail();
			System.exit(-1);
		}
	}

	private Bookie parseBookie(String bookieName, String participants) throws InterruptedException, FatalVBException {
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
			VBBookmakerMinStakeElement minStakeElement) throws FatalVBException {
		final double EPS = 10E-3;
		final boolean equal = Math.abs(oddsRight - oddsLeft) < EPS;

		return (equal || oddsRight > oddsLeft) && in.isBetPlaceable(bookie, stake, balanceElement.getBalance(),
				maxStakeElement.getStake(), minStakeElement.getStake());
	}

}
