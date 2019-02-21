package com.util.ai.screenbot.input.exceptions;

public class GuiElementNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuiElementNotFoundException(String message) {
		super(message);
	}

	public GuiElementNotFoundException(String message, Throwable t) {
		super(message, t);
	}

}
