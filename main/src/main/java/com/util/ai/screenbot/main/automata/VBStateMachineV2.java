package com.util.ai.screenbot.main.automata;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.automata.states.InitState;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public class VBStateMachineV2 implements VBStateMachine {
	
	private static final Logger log = LoggerFactory.getLogger(VBStateMachineV2.class);
	
	private final InputHandler in;

	private final OutputHandler out;

	private final EmailSender email;
	
	public VBStateMachineV2(InputHandler in, OutputHandler out, EmailSender email) {
		this.in = Objects.requireNonNull(in);
		this.out = Objects.requireNonNull(out);
		this.email = Objects.requireNonNull(email);
	}

	@Override
	public void run() throws InterruptedException {
		try {
			new InitState(in, out, email).process();
		} catch (FatalValueBettingException e) {
			log.error("Fatal exception has occurred.", e);
			log.info("Shutting down ...");
			System.exit(-1);
		}
	}

}
