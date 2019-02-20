package com.util.ai.screenbot.input.exceptions;

public class FatalVBException extends Exception {

	private static final long serialVersionUID = 1L;

	public FatalVBException(String message) {
		super(message);
	}

	public FatalVBException(String message, Throwable t) {
		super(message, t);
	}

}
