package com.util.ai.screenbot.main.automata;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.email.EmailSender;

public class VBStateMachineImpl {
	
    private static final Logger log = LoggerFactory.getLogger(VBStateMachineImpl.class);
	
	/** A period used for checking whether the new bet has occurred, in ms. */
	private static final int BET_CHECK_PERIOD = 2_000;
	
	private final InputHandler in;
	
	private final OutputHandler out;
	
	@SuppressWarnings("unused")
	private final EmailSender email;
	
	public VBStateMachineImpl(InputHandler in, OutputHandler out, EmailSender email) {
		this.in = Objects.requireNonNull(in);
		this.out = Objects.requireNonNull(out);
		this.email = Objects.requireNonNull(email);
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
		
		try {
			final BufferedImage oddsInputImage = in.getOddsInputImage();
			final BufferedImage placeBetImage = in.getPlaceBetImage();
			
			final VBOddsInputElement oddsInput = out.readOddsInput(oddsInputImage);
			final VBPlaceBetElement placeBet = out.readPlaceBet(placeBetImage);
			
			final double oddsLeft = Integer.parseInt(oddsInput.getOdds().trim());
			final double oddsRight = Integer.parseInt(placeBet.getOdds().trim());
			
			if (oddsRight >= oddsLeft) {
				// place bet logic
			} else {
				// remove bets logic
			}
		} catch (VBElementInterpretationException e) {
			// TODO: handle exception
			// TODO: send email
		}	
		
		logBet();
	}

	public void parseBet() throws InterruptedException {
		log.debug("Enter state: PARSE_BET ...");
		
		try {
			final BufferedImage image = in.getSingleBetImage();
			final VBSingleBetElement element = out.readSingleBet(image);
			placeBet(element);
		} catch (VBElementInterpretationException e) {
			// TODO: handle exception
			log.error("Problem occurred while parsing bet.", e);
			// TODO: send email
			
			cleanBet();
		}
		
	}

	public void idle() throws InterruptedException {
		log.debug("Enter state: IDLE ...");
		
		while (true) {
			Thread.sleep(BET_CHECK_PERIOD);
			
			if (in.isNewBetPresent()) parseBet();
		}
	}

	public void init() {
		// TODO: init
		log.debug("Enter state: INIT ...");
	}

}
