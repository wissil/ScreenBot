package com.util.ai.screenbot.main.automata;

import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.main.bookie.UnknownBookieException;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
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
		// TODO: excel table
		log.debug("Enter state: LOG_BET ...");
		idle();
	}

	public void placeBet(VBSingleBetElement element) throws InterruptedException {
		log.debug("Enter state: PLACE_BET ...");

		// 1) go to betting browser
		in.openBettingBrowserWindow();

		// 2) wait while betting browser loading is done
		int waitingTime = 0;
		while (true) {
			// take photo of Done area
			// OCR photo
			// parse photo
			// if Done
			if (in.isBettingBrowserLoaded()) {
				log.debug("Betting browser successfully loaded!");
				break;
			}

			if (waitingTime == BET_BROWSER_LOAD_TIMEOUT) {
				log.warn(String.format("Betting browser didn't load in %d seconds.", BET_BROWSER_LOAD_TIMEOUT));
				log.warn("Timeout!");
				// clean bets
			}

			Thread.sleep(BET_BROWSER_LOAD_PERIOD);
			waitingTime += 1;
		}

		in.clickNeutralArea();

		Bookie bookie = null;

		// 3) process bet
		log.debug("Processing betting browser screen ...");
		try {
			bookie = Bookie.fromString(element.getBookie());

			final BufferedImage oddsInputImage = in.getOddsInputImage();
			final BufferedImage placeBetImage = in.getPlaceBetImage(/* bookie */);

			final VBBetInfoElement oddsInput = out.readBetInfo(oddsInputImage);
			final VBBookmakerOddsElement placeBet = out.readBookmakerOdds(placeBetImage);

			final double oddsLeft = Double.parseDouble(oddsInput.getOdds().trim());
			final double oddsRight = Double.parseDouble(placeBet.getOdds().trim());

			final double stake = Double.parseDouble(oddsInput.getStake().trim());

			if (oddsRight >= oddsLeft && in.isBetPlaceable(bookie, stake)) {
				// place bet logic

				// 1) kladionica.placeBet()
				in.placeBet(bookie, stake);

				// 2) click OK on the betting browser
				in.clickOKAtBettingBrowser();

				// 3) log bet
				in.openMainWindow();
				logBet();
			} else {
				// clean bets logic

				// 1) kladionica.removeBet()
				in.removeBet(bookie);

				// 2) click Cancel
				in.clickCancelAtBettingBrowser();

				// 3) go to main screen
				in.openMainWindow();

				// while (najgornji == taj_isti) makni najgornji
				// click and remove all
				final String participantsToRemove = element.getParticipants();
				while (in.isNewBetPresent()) {
					final BufferedImage image = in.getSingleBetImage();
					final VBSingleBetElement newElement = out.readSingleBet(image);

					final String newParticipants = newElement.getParticipants();
					if (consideredEqual(participantsToRemove, newParticipants)) {
						in.removeTopBet();
					} else {
						break;
					}
				}
				idle();
			}
		} catch (VBElementInterpretationException e) {
			log.error("Interpretation failed.", e);
			email.send(LOG_FILE_PATH);

			// 1) kladionica.removeBet()
			in.removeBet(bookie);

			// 2) click Cancel
			in.clickCancelAtBettingBrowser();

			// remove top bet --> idle()
			in.openMainWindow();
			in.removeTopBet();
			idle();
		} catch (UnknownBookieException e) {
			log.error("Bookie not known.", e);
			// 1) kladionica.removeBet()
			in.removeBet(bookie);

			// 2) click Cancel
			in.clickCancelAtBettingBrowser();

			in.openMainWindow();
			in.removeTopBet();
			idle();
		} catch (Exception e) {
			// any other exception
			log.error("Unknown exception has occurred.", e);
			email.send(LOG_FILE_PATH);
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
		// TODO: init
		log.debug("Enter state: INIT ...");
		idle();
	}

}
