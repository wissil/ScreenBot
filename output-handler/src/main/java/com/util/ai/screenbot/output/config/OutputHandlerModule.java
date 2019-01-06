package com.util.ai.screenbot.output.config;


import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreter;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreterImpl;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.support.image.BWImageProcessor;
import com.util.ai.screenbot.support.image.ImageProcessor;
import com.util.ai.screenbot.output.parsing.VBBalanceElementParser;
import com.util.ai.screenbot.output.parsing.VBBetInfoElementParser;
import com.util.ai.screenbot.output.parsing.VBBookmakerOddsElementParser;
import com.util.ai.screenbot.output.parsing.VBBookmakerStakeElementParser;
import com.util.ai.screenbot.output.parsing.VBBrowsingStatusElementParser;

public class OutputHandlerModule extends AbstractModule {
	
    @Override
    protected void configure() {
        requestStaticInjection(VBElementInterpreterProvider.class);
    }
	
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
	VBBetInfoElementParser oddsInputParser() {
		return new VBBetInfoElementParser();
	}
	
	@Provides
	@Singleton
	VBBookmakerOddsElementParser placeBetParser() {
		return new VBBookmakerOddsElementParser();
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBetInfoElement> betInfoInterpreter(
			OCR ocr, 
			VBBetInfoElementParser parser, 
			BWImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBetInfoElement, VBBetInfoElementParser>
		(ocr, parser, imageProcessor, Boolean.FALSE);
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBSingleBetElement> singleBetInterpreter(
			OCR ocr, 
			VBSingleBetElementParser parser, 
			BWImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBSingleBetElement, VBSingleBetElementParser>
		(ocr, parser, imageProcessor, Boolean.TRUE);
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBrowsingStatusElement> browsingStatusInterpreter(
			OCR ocr, 
			VBBrowsingStatusElementParser parser,
			BWImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBrowsingStatusElement, VBBrowsingStatusElementParser>
		(ocr, parser, imageProcessor, Boolean.FALSE);
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBookmakerOddsElement> bookmakerOddsInterpreter(
			OCR ocr, 
			VBBookmakerOddsElementParser parser,
			BWImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBookmakerOddsElement, VBBookmakerOddsElementParser>
		(ocr, parser, imageProcessor, Boolean.FALSE);
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBalanceElement> balanceInterpreter(
			OCR ocr, 
			VBBalanceElementParser parser,
			BWImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBalanceElement, VBBalanceElementParser>
		(ocr, parser, imageProcessor, Boolean.TRUE);
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBookmakerStakeElement> bookmakerStakeInterpreter(
			OCR ocr, 
			VBBookmakerStakeElementParser parser,
			BWImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBookmakerStakeElement, VBBookmakerStakeElementParser>
		(ocr, parser, imageProcessor, Boolean.TRUE);
	}
	
	@Provides
	@Singleton
	ImageProcessor imageProcessor() {
		return new BWImageProcessor();
	}
}
