package com.util.ai.screenbot.output.config;


import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.output.interpreters.VBOddsInputInterpreter;
import com.util.ai.screenbot.output.interpreters.VBPlaceBetInterpreter;
import com.util.ai.screenbot.output.interpreters.VBSingleBetInterpreter;
import com.util.ai.screenbot.output.logic.VBOutputInterpreter;
import com.util.ai.screenbot.output.logic.VBOutputInterpreterImpl;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.support.image.BWImageProcessor;
import com.util.ai.screenbot.support.image.ImageProcessor;
import com.util.ai.screenbot.output.parsing.VBOddsInputElementParser;
import com.util.ai.screenbot.output.parsing.VBPlaceBetElementParser;

public class OutputHandlerModule extends AbstractModule {
	
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
	
	@Inject
	@Provides
	@Singleton
	VBOddsInputInterpreter oddsInputInterpreter(OCR ocr, VBOddsInputElementParser parser) {
		return new VBOddsInputInterpreter(ocr, parser);
	}
	
	@Inject
	@Provides
	@Singleton
	VBPlaceBetInterpreter placeBetInterpreter(OCR ocr, VBPlaceBetElementParser parser) {
		return new VBPlaceBetInterpreter(ocr, parser);
	}
	
	@Inject
	@Provides
	@Singleton
	VBSingleBetInterpreter singleBetInterpreter(OCR ocr, VBSingleBetElementParser parser) {
		return new VBSingleBetInterpreter(ocr, parser);
	}
	
	@Provides
	@Singleton
	ImageProcessor imageProcessor() {
		return new BWImageProcessor();
	}
	
	@Inject
	@Provides
	@Singleton
	VBOutputInterpreter outputInterpreter(
			ImageProcessor processor,
			VBSingleBetInterpreter singleBetInterpreter,
			VBPlaceBetInterpreter placeBetInterpreter,
			VBOddsInputInterpreter oddsInputInterpreter) {
		return new VBOutputInterpreterImpl(processor, oddsInputInterpreter, placeBetInterpreter, singleBetInterpreter);
	}
}
