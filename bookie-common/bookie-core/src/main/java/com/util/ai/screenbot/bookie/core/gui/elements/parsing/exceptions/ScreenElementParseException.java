package com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions;

public class ScreenElementParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScreenElementParseException(String message) {
		super(message);
	}
	
	public ScreenElementParseException(String message, Throwable t) {
		super(message, t);
	}
}
