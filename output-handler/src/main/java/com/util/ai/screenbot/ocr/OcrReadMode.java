package com.util.ai.screenbot.ocr;

import java.util.Objects;

public enum OcrReadMode {

	ENGLISH("eng"),
	
	DIGITS("digits");
	
	final String language;
	
	OcrReadMode(String language) {
		this.language = Objects.requireNonNull(language);
	}
	
	public String getLanguage() {
		return language;
	}
}
