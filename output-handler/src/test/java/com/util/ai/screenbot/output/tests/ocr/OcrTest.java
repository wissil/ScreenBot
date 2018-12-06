package com.util.ai.screenbot.output.tests.ocr;

import java.io.IOException;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class OcrTest extends OutputHandlerTestBase {
	
	private static final String TESSDATA_PATH = "./tessdata";
	
	private static final String LANGUAGE = "eng";

	@Inject
	private OCR ocr;
	
	private static TessBaseAPI api;
	
	@BeforeClass
	public static void setup() {
		// init API
		api = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
	}
	
	@Test
	public void interpretSingleImageTest() throws IOException {
		final String imagePath = "./external/res/zelenilo.jpg";
		final String textual = ocr.doOcr(api, imagePath);
		System.out.println(textual);
	}
	
	@Test
	public void interpretMultipleImagesTest() throws IOException {
		String imagePath = "./external/res/t1.png";
		String textual = ocr.doOcr(api, imagePath);
		System.out.println(textual);
		
		imagePath = "./external/res/test2.jpg";
		textual = ocr.doOcr(api, imagePath);
		System.out.println(textual);
	}
	
	@AfterClass
	public static void after() {
		// close API
		TesseractAPI.destroyTesseract(api);
	}
}
