package com.util.ai.screenbot.main.automata.states;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.main.bookie.UnknownBookieException;
import com.util.ai.screenbot.main.eval.BetEvaluator;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.main.reports.BetReport;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.email.EmailSender;

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

		// 3) parse from the element
		final String participants = element.getParticipants();
		final Bookie bookie = parseBookie(element.getBookie(), participants);

		try {
			in.waitForBettingBrowserToLoad();
			log.debug("Betting browser successfully loaded!");

			in.checkBettingSlip(bookie);

			log.debug("Betting slip successfully checked!");

			final VBBetInfoElement betInfo = out.readBetInfo(in.getOddsInputImage());
			final VBBookmakerOddsElement bookmakerOdds = out.readBookmakerOdds(in.getBookmakerOddsImage(bookie),
					bookie);
			final VBBalanceElement balanceElement = out.readBalance(in.getBalanceStakeImage(bookie), bookie);

			final double oddsLimit = betInfo.getOdds();
			final double oddsActual = bookmakerOdds.getOdds();
			final double balance = balanceElement.getBalance();
			final double value = betInfo.getValue();

			final double stake = 0.2;

			log.debug("All the data successfully parsed!");
			log.debug("Checking if the bet could be placed!");

			final boolean shouldPlaceBet = BetEvaluator.shouldPlaceBet(oddsLimit, oddsActual, stake, balance);

			if (shouldPlaceBet) {
				log.debug("Bet can be placable!");

				// 1) place bet
				// in.placeBet(bookie, stake);
				log.debug("Placing the bet ...");
				in.placeBet(bookie, stake);
				log.debug("Bet successfully placed!");

				// 2) click OK on the betting browser

				log.debug("Clicking OK on the betting browser!");
				in.clickOKAtBettingBrowser();
				log.debug("OK clicked!");

				// 3) log bet
				new LogBetState(in, out, email).process();

				// 4) go to main window
				log.debug("Opening main window ...");
				in.openMainWindow();
				log.debug("Main window opened!");
			} else {
				log.info("Bet could not be placed!");
				new CleanBetState(in, out, email, participants, bookie, true).process();
			}
			final BetReport report = new BetReport(bookie.toString(), value, oddsLimit, oddsActual, stake, balance, 0,
					0, shouldPlaceBet);

			log.debug("Generating the report ...");
			log.info(report.toString());

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

		new IdleState(in, out, email).process();
	}

	private Bookie parseBookie(String bookieName, String participants) throws InterruptedException, FatalVBException {
		try {
			return Bookie.fromString(bookieName);
		} catch (UnknownBookieException e) {
			log.error("Bookmaker couldn't be recognized.", e);
			sendEmail();
			new CleanBetState(in, out, email, participants, true).process();
		}

		return null;
	}
}
