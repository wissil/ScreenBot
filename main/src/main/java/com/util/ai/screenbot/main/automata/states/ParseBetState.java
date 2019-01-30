package com.util.ai.screenbot.main.automata.states;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.email.EmailSender;

public class ParseBetState extends VBState {
	
	private static final Logger log = LoggerFactory.getLogger(ParseBetState.class);

	public ParseBetState(InputHandler in, OutputHandler out, EmailSender email) {
		super(in, out, email);
	}

	@Override
	void onEnter() {
		log.debug("Entered PARSE state!");
	}

	@Override
	void onExit() {
		log.debug("Exiting PARSE state...");
	}

	@Override
	void execute() throws InterruptedException, FatalValueBettingException {
		log.debug("Enter state: PARSE_BET ...");

		try {
			final BufferedImage image = in.getSingleBetImage();
			final VBSingleBetElement element = out.readSingleBet(image);
			new PlaceBetState(in, out, email, element).process();
		} catch (VBElementInterpretationException e) {
			log.error("Problem occurred while parsing bet.", e);
			sendEmail();

			// remove top bet --> idle()
			in.removeTopBet();
			new IdleState(in, out, email).process();
		}		
	}

}
