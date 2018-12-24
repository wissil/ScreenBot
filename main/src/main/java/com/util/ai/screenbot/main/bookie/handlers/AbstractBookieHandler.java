package com.util.ai.screenbot.main.bookie.handlers;

import java.util.Objects;

import com.util.ai.screenbot.main.handlers.input.InputHandler;

public abstract class AbstractBookieHandler implements BookieHandler {

	 final InputHandler in;
	 
	 public AbstractBookieHandler(InputHandler in) {
		 this.in = Objects.requireNonNull(in);
	}
}
