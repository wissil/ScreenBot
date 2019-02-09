package com.util.ai.screenbot.main.automata.states;

import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.email.EmailSender;

public abstract class VBState {
	
	private static final String LOG_FILE_PATH = "../logs/main/daily/main.log";
	
	final InputHandler in;
	
	final OutputHandler out;
	
	final EmailSender email;
	
	public VBState(InputHandler in, OutputHandler out, EmailSender email) {
		this.in = Objects.requireNonNull(in);
		this.out = Objects.requireNonNull(out);
		this.email = Objects.requireNonNull(email);
	}
	
	void sendEmail() {
		email.send(LOG_FILE_PATH);
	}

	abstract void onEnter();
	
	abstract void onExit();
	
	abstract void execute() throws InterruptedException, FatalVBException;
	
	public void process() throws InterruptedException, FatalVBException {
		onEnter();
		execute();
		onExit();
	}
}
