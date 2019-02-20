package com.util.ai.screenbot.main.automata.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public class LogBetState extends VBState {

	private static final Logger log = LoggerFactory.getLogger(LogBetState.class);

	public LogBetState(InputHandler in, OutputHandler out, EmailSender email) {
		super(in, out, email);
	}

	@Override
	void onEnter() {
		log.debug("Entered LOG_BET state!");

	}

	@Override
	void onExit() {
		log.debug("Exit LOG_BET state!");

	}

	@Override
	void execute() {
		in.logBet();

	}

}
