package com.util.ai.screenbot.ocr.config;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.ocr.OCR;
import com.util.ai.screenbot.ocr.OcrImageProcessor;
import com.util.ai.screenbot.ocr.OcrImageProcessorImpl;

import net.sourceforge.tess4j.TessAPI;
import net.sourceforge.tess4j.Tesseract;

public class OcrHandlerModule extends AbstractModule {
	
	private static final String TESSDATA_PATH = "../../ocr-handler/tessdata";

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
	OcrImageProcessor ocrImageProcessor() {
		return new OcrImageProcessorImpl();
	}
}
