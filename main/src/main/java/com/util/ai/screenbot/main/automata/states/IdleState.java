package com.util.ai.screenbot.main.automata.states;

import static com.util.ai.screenbot.input.constants.VBGuiConstants.BLANK;
import static com.util.ai.screenbot.input.constants.VBGuiConstants.SINGLE_BET_HEADER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.utils.SikuliUtils;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public class IdleState extends VBState {

	private static final Logger log = LoggerFactory.getLogger(IdleState.class);

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

			/*
			 * This will run indefinitely, or as long as the Blank element doesn't vanish
			 * below the Single Bet header.
			 */
			if (SikuliUtils.waitForTargetToVanishBelowBase(SINGLE_BET_HEADER, BLANK, 20, Double.POSITIVE_INFINITY)) {
				new ParseBetState(in, out, email).process();
			}
		}

	}

}
