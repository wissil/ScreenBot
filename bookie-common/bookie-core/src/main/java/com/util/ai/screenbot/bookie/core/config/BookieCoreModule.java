package com.util.ai.screenbot.bookie.core.config;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.bookie.core.gui.common.VBBetInfoGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBBrowsingStatusGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBSingleBetGui;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBalanceElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBSingleBetElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreter;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterImpl;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBalanceElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBetInfoElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBetInfoValueElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBookmakerMaxStakeElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBookmakerMinStakeElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBookmakerOddsElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBrowsingStatusElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBalanceGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerOddsGui;
import com.util.ai.screenbot.ocr.OCR;
import com.util.ai.screenbot.ocr.OcrImageProcessor;

public class BookieCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		requestStaticInjection(VBElementInterpreterProvider.class);
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

	@Provides
	@Singleton
	VBBetInfoValueElementParser betInfoValueParser() {
		return new VBBetInfoValueElementParser();
	}

	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBetInfoElement, VBBetInfoGui> betInfoInterpreter(
			OCR ocr, 
			VBBetInfoElementParser parser, 
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBetInfoElement, VBBetInfoGui, VBBetInfoElementParser>
		(ocr, parser, imageProcessor);
	}

	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBSingleBetElement, VBSingleBetGui> singleBetInterpreter(
			OCR ocr, 
			VBSingleBetElementParser parser, 
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBSingleBetElement, VBSingleBetGui, VBSingleBetElementParser>
		(ocr, parser, imageProcessor);
	}

	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBrowsingStatusElement, VBBrowsingStatusGui> browsingStatusInterpreter(
			OCR ocr, 
			VBBrowsingStatusElementParser parser,
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBrowsingStatusElement, VBBrowsingStatusGui, VBBrowsingStatusElementParser>
		(ocr, parser, imageProcessor);
	}

	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBookmakerOddsElement, ? super VBBookmakerOddsGui> bookmakerOddsInterpreter(
			OCR ocr, 
			VBBookmakerOddsElementParser parser,
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBookmakerOddsElement, VBBookmakerOddsGui, VBBookmakerOddsElementParser>
		(ocr, parser, imageProcessor);
	}

	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBalanceElement, ? super VBBalanceGui> balanceInterpreter(
			OCR ocr, 
			VBBalanceElementParser parser,
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBalanceElement, VBBalanceGui, VBBalanceElementParser>
		(ocr, parser, imageProcessor);
	}

	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBookmakerMinStakeElement, ? super VBBookmakerMinStakeGui> bookmakerMinStakeInterpreter(
			OCR ocr, 
			VBBookmakerMinStakeElementParser parser,
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBookmakerMinStakeElement, VBBookmakerMinStakeGui, VBBookmakerMinStakeElementParser>
		(ocr, parser, imageProcessor);
	}
	
	@Inject
	@Provides
	@Singleton
	VBElementInterpreter<VBBookmakerMaxStakeElement, ? super VBBookmakerMaxStakeGui> bookmakerMaxStakeInterpreter(
			OCR ocr, 
			VBBookmakerMaxStakeElementParser parser,
			OcrImageProcessor imageProcessor) {
		return new VBElementInterpreterImpl<VBBookmakerMaxStakeElement, VBBookmakerMaxStakeGui, VBBookmakerMaxStakeElementParser>
		(ocr, parser, imageProcessor);
	}
}
