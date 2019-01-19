package com.util.ai.screenbot.input.exceptions;

public class NoBetFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBetFoundException(String message) {
		super(message);
	}

	public NoBetFoundException(String message, Throwable t) {
		super(message, t);
	}

}
