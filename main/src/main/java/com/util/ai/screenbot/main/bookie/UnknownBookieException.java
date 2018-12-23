package com.util.ai.screenbot.main.bookie;

public class UnknownBookieException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnknownBookieException(String message) {
		super(message);
	}

	public UnknownBookieException(String message, Throwable t) {
		super(message, t);
	}
}
