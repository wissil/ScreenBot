package com.util.ai.screenbot.main.automata.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.utils.SikuliUtils;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

import static com.util.ai.screenbot.input.constants.VBGuiConstants.SINGLE_BET_HEADER;

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
		if (SikuliUtils.waitForElementToAppearBelow(SINGLE_BET_HEADER, 20, Double.POSITIVE_INFINITY)) {
			new ParseBetState(in, out, email).process();
		}
	}

}
