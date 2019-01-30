package com.util.ai.screenbot.main.automata;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.utils.DiskUtils;
import com.util.ai.screenbot.input.utils.SystemUtils;
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

public class VBStateMachineImpl implements VBStateMachine {

	private static final Logger log = LoggerFactory.getLogger(VBStateMachineImpl.class);

	private static final String LOG_FILE_PATH = "../logs/main/daily/main.log";

	/** A period used for checking whether the new bet has occurred, in ms. */
	private static final int BET_CHECK_PERIOD = 2_000;

	private static final int BET_BROWSER_LOAD_PERIOD = 1_000;

	private static final int BET_BROWSER_LOAD_TIMEOUT = 30;

	private final InputHandler in;

	private final OutputHandler out;

	private final EmailSender email;

	public VBStateMachineImpl(InputHandler in, OutputHandler out, EmailSender email) {
		this.in = Objects.requireNonNull(in);
		this.out = Objects.requireNonNull(out);
		this.email = Objects.requireNonNull(email);
	}

	@Override
	public void run() throws InterruptedException {
		init();
	}

	public void cleanBet() throws InterruptedException {
		log.debug("Enter state: CLEAN_BET ...");
		idle();
	}

	public void logBet() throws InterruptedException {
		log.debug("Enter state: LOG_BET ...");
		DiskUtils.logBetToFile(SystemUtils.getClipboardContents());
		idle();
	}

	public void placeBet(VBSingleBetElement element) throws InterruptedException, VBElementInterpretationException {
		log.debug("Enter state: PLACE_BET ...");

		in.clickBetOnTopEvent();

		// 1) go to betting browser
		in.openBettingBrowserWindow();

		Thread.sleep(1000);

		// 2) wait while betting browser loading is done
		int waitingTime = 0;
		while (true) {
			// take photo of Done area
			// OCR photo
			// parse photo
			// if Done
			final BufferedImage browsingStatusImage = in.getBrowsingStatusImage();
			if (out.readBrowsingStatus(browsingStatusImage).isDone()) {
				log.debug("Betting browser successfully loaded! - first check");
				Thread.sleep(500);
				// Added second check because of the false true that sometimes happen
				final BufferedImage browsingStatusImage2 = in.getBrowsingStatusImage();
				if (out.readBrowsingStatus(browsingStatusImage2).isDone()) {
					break;
				}
			}

			if (waitingTime == BET_BROWSER_LOAD_TIMEOUT) {
				log.warn(String.format("Betting browser didn't load in %d seconds.", BET_BROWSER_LOAD_TIMEOUT));
				log.warn("Timeout!");
				// clean bets
			}

			Thread.sleep(BET_BROWSER_LOAD_PERIOD);
			waitingTime += 1;
		}

		Bookie bookie = null;

		// 3) process bet
		log.debug("Processing betting browser screen ...");
		try {
			bookie = Bookie.fromString(element.getBookie());
			in.intializeBookieBot(bookie);
			in.checkBettingSlip(bookie);
			in.clickNeutralArea(bookie);

			Thread.sleep(500);

			final BufferedImage oddsInputImage = in.getOddsInputImage();
			final BufferedImage placeBetImage = in.getBookmakerOddsImage(bookie);

			final VBBetInfoElement oddsInput = out.readBetInfo(oddsInputImage);
			final VBBookmakerOddsElement placeBet = out.readBookmakerOdds(placeBetImage, bookie);
			log.debug("Bet odds: " + placeBet.getOdds().trim());

			final VBBalanceElement balanceElement = out.readBalance(in.getBalanceStakeImage(bookie), bookie);
			final VBBookmakerMaxStakeElement maxStakeElement = out.readMaxStake(in.getMaxStakeImage(bookie), bookie);
			final VBBookmakerMinStakeElement minStakeElement = out.readMinStake(in.getMinStakeImage(bookie), bookie);

			final double oddsLeft = oddsInput.getOdds();
			final double oddsRight = Double.parseDouble(placeBet.getOdds().trim());

			final double stake = oddsInput.getStake();

			if (oddsRight >= oddsLeft && in.isBetPlaceable(bookie, stake, balanceElement.getBalance(),
					maxStakeElement.getStake(), minStakeElement.getStake())) {
				// place bet logic

				// 1) kladionica.placeBet()
				in.placeBet(bookie, stake);

				Thread.sleep(500);

				// 2) click OK on the betting browser
				in.clickOKAtBettingBrowser();

				Thread.sleep(500);

				logBet();

				// 3) log bet
				in.openMainWindow();
			} else {
				log.debug("Bet not plecable");
				// clean bets logic

				// 1) kladionica.removeBet()
				in.removeBet(bookie);
				log.debug("Bet removed from bet slip");

				Thread.sleep(1000);

				// 2) click Cancel
				in.clickCancelAtBettingBrowser();

				Thread.sleep(1000);

				// 3) go to main screen
				in.openMainWindow();

				Thread.sleep(500);

				// click and remove all
				in.removeAllBetsFromTopBetEvent();
				idle();
			}
		} catch (VBElementInterpretationException e) {
			log.error("Interpretation failed.", e);
			email.send(LOG_FILE_PATH);

			// 1) kladionica.removeBet()
			try {
				in.removeBet(bookie);
			} catch (FatalValueBettingException e1) {
				log.error("Can not continue", e);
				email.send(LOG_FILE_PATH);
				System.exit(-1);
			}
			Thread.sleep(1000);
			// 2) click Cancel
			in.clickCancelAtBettingBrowser();
			Thread.sleep(1000);
			// remove top bet --> idle()
			in.openMainWindow();
			Thread.sleep(500);
			in.removeAllBetsFromTopBetEvent();
			idle();
		} catch (UnknownBookieException e) {
			log.error("Bookie not known.", e);

			// 2) click Cancel
			in.clickCancelAtBettingBrowser();
			Thread.sleep(1000);
			// remove top bet --> idle()
			in.openMainWindow();
			Thread.sleep(500);
			in.removeAllBetsFromTopBetEvent();
			idle();
		} catch (BetNotFoundException | BetSlipException e) {
			log.error("Bet does not exists in bookmater", e);

			// 2) click Cancel
			in.clickCancelAtBettingBrowser();
			Thread.sleep(1000);
			// remove top bet --> idle()
			in.openMainWindow();
			Thread.sleep(500);
			in.removeAllBetsFromTopBetEvent();
			idle();
		} catch (Exception e) {
			// any other exception
			log.error("Unknown exception has occurred.", e);
			email.send(LOG_FILE_PATH);
			System.exit(-1);
		}
	}

	public void parseBet() throws InterruptedException {
		log.debug("Enter state: PARSE_BET ...");

		try {
			final BufferedImage image = in.getSingleBetImage();
			final VBSingleBetElement element = out.readSingleBet(image);
			placeBet(element);
		} catch (VBElementInterpretationException e) {
			log.error("Problem occurred while parsing bet.", e);
			email.send(LOG_FILE_PATH);

			// remove top bet --> idle()
			in.removeTopBet();
			idle();
		}
	}

	public void idle() throws InterruptedException {
		log.debug("Enter state: IDLE ...");

		while (true) {
			Thread.sleep(BET_CHECK_PERIOD);

			if (in.isNewBetPresent())
				parseBet();
		}
	}

	public void init() throws InterruptedException {
		log.debug("Enter state: INIT ...");
		in.openMainWindow();
		idle();
	}

}
