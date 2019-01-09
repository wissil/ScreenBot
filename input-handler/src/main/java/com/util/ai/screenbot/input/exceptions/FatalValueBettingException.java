package com.util.ai.screenbot.input.exceptions;

public class FatalValueBettingException extends Exception {

	private static final long serialVersionUID = 1L;

	public FatalValueBettingException(String message) {
		super(message);
	}

	public FatalValueBettingException(String message, Throwable t) {
		super(message, t);
	}

}
