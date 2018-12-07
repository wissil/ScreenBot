package com.util.ai.screenbot.output.config;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.LeptonicaFrameConverter;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBItemElementParser;
import com.util.ai.screenbot.output.parsing.VBOddsInputElementParser;
import com.util.ai.screenbot.output.parsing.VBPlaceBetElementParser;

public class OutputHandlerModule extends AbstractModule {
	
	@Provides
	@Singleton
	Java2DFrameConverter javaConverter() {
		return new Java2DFrameConverter();
	}
	
	@Provides
	@Singleton
	LeptonicaFrameConverter leptonicaConverter() {
		return new LeptonicaFrameConverter();
	}
	
	@Inject
	@Provides
	@Singleton
	OCR ocr(Java2DFrameConverter javaConverter, LeptonicaFrameConverter leptonicaConverter) {
		return new OCR(javaConverter, leptonicaConverter);
	}
	
	@Provides
	@Singleton
	VBItemElementParser itemParser() {
		return new VBItemElementParser();
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
