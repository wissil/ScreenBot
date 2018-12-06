package com.util.ai.screenbot.output.ocr.exceptions;

public class OCRException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OCRException(String message) {
		super(message);
	}
	
	public OCRException(String message, Throwable t) {
		super(message, t);
	}
}
