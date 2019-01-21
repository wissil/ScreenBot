package com.util.ai.screenbot.output.config;


import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBalanceGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBetInfoGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBrowsingStatusGui;
import com.util.ai.screenbot.output.elements.gui.general.VBSingleBetGui;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreter;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreterImpl;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrImageProcessor;
import com.util.ai.screenbot.output.ocr.OcrImageProcessorImpl;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;

import net.sourceforge.tess4j.TessAPI;
import net.sourceforge.tess4j.Tesseract;

import com.util.ai.screenbot.output.parsing.VBBalanceElementParser;
import com.util.ai.screenbot.output.parsing.VBBetInfoElementParser;
import com.util.ai.screenbot.output.parsing.VBBetInfoValueElementParser;
import com.util.ai.screenbot.output.parsing.VBBookmakerMaxStakeElementParser;
import com.util.ai.screenbot.output.parsing.VBBookmakerOddsElementParser;
import com.util.ai.screenbot.output.parsing.VBBookmakerMinStakeElementParser;
import com.util.ai.screenbot.output.parsing.VBBrowsingStatusElementParser;

public class OutputHandlerModule extends AbstractModule {
	
	private static final String TESSDATA_PATH = "../output-handler/tessdata";

	@Override
	protected void configure() {
		requestStaticInjection(VBElementInterpreterProvider.class);
	}

	@Provides
	@Singleton
	Tesseract tesseract() {
		final Tesseract tesseract = new Tesseract();
		tesseract.setDatapath(TESSDATA_PATH);
		tesseract.setOcrEngineMode(TessAPI.TessOcrEngineMode.OEM_DEFAULT);
		
		return tesseract;
	}

	@Inject
	@Provides
	@Singleton
	OCR ocr(Tesseract tesseract) {
		return new OCR(tesseract);
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
	
	@Provides
	@Singleton
	OcrImageProcessor imageProcessor() {
		return new OcrImageProcessorImpl();
	}
}
