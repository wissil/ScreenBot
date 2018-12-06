package com.util.ai.screenbot.output.tests.ocr;

import java.io.IOException;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class OcrTest extends OutputHandlerTestBase {

	@Inject
	private OCR ocr;
	
	private TessBaseAPI api;
	
	@Before
	public void setup() {
		// init API
		this.api = TesseractAPI.getTesseract();
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
		TesseractAPI.destroyTesseract();
	}
}
