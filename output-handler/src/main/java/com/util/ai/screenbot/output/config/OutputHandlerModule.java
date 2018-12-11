package com.util.ai.screenbot.output.config;


import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.output.parsing.VBOddsInputElementParser;
import com.util.ai.screenbot.output.parsing.VBPlaceBetElementParser;

public class OutputHandlerModule extends AbstractModule {
	
	@Inject
	@Provides
	@Singleton
	OCR ocr() {
		return new OCR();
	}
	
	@Provides
	@Singleton
	VBSingleBetElementParser singleBetParser() {
		return new VBSingleBetElementParser();
	}
	
	@Provides
	@Singleton
	VBOddsInputElementParser oddsInputParser() {
		return new VBOddsInputElementParser();
	}
	
	@Provides
	@Singleton
	VBPlaceBetElementParser placeBetParser() {
		return new VBPlaceBetElementParser();
	}
}
