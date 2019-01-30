package com.util.ai.screenbot.main.automata.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public class InitState extends VBState {
	
	public InitState(InputHandler in, OutputHandler out, EmailSender email) {
		super(in, out, email);
	}

	private static final Logger log = LoggerFactory.getLogger(InitState.class);

	@Override
	void onEnter() {
		log.debug("Entered INIT state!");
	}

	@Override
	void onExit() {
		log.debug("Exiting INIT state...");
	}

	@Override
	void execute() throws InterruptedException, FatalValueBettingException {
		in.openMainWindow();
		new IdleState(in, out, email).process();
	}

}
