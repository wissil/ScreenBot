package com.util.ai.screenbot.main.automata.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public class IdleState extends VBState {

	private static final Logger log = LoggerFactory.getLogger(IdleState.class);
	
	/** A period used for checking whether the new bet has occurred, in ms. */
	private static final int BET_CHECK_PERIOD = 2_000;

	public IdleState(InputHandler in, OutputHandler out, EmailSender email) {
		super(in, out, email);
	}

	@Override
	void onEnter() {
		log.debug("Entered IDLE state!");
	}

	@Override
	void onExit() {
		log.debug("Exiting IDLE state...");
	}

	@Override
	void execute() throws InterruptedException, FatalVBException {
		while (true) {
			Thread.sleep(BET_CHECK_PERIOD);

			if (in.isNewBetPresent()) {
				// go to parse bet
				new ParseBetState(in, out, email).process();
			}
		}		
	}

}
