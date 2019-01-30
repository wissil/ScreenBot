package com.util.ai.screenbot.input.exceptions;

public class BetNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BetNotFoundException(String message) {
		super(message);
	}

	public BetNotFoundException(String message, Throwable t) {
		super(message, t);
	}

}
