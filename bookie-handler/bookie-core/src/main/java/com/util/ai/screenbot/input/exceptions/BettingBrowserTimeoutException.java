package com.util.ai.screenbot.input.exceptions;

public class BettingBrowserTimeoutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BettingBrowserTimeoutException(String message) {
		super(message);
	}

	public BettingBrowserTimeoutException(String message, Throwable t) {
		super(message, t);
	}

}
