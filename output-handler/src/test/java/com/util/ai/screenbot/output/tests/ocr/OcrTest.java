package com.util.ai.screenbot.output.tests.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.testing.time.TimedExecution;

public class OcrTest extends OutputHandlerTestBase {

	private static final String TESSDATA_PATH = "./tessdata";

	private static final String LANGUAGE = "eng";

	@Inject
	private OCR ocr;

	private static TessBaseAPI api;

	private static BufferedImage bImage;

	@BeforeClass
	public static void setup() throws IOException {
		// init API
		api = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
		bImage = ImageIO.read(new File("./external/res/zelenilo.jpg"));
	}

	@Test
	@TimedExecution
	public void ocrFromImagePath_SingleFile_Test() throws IOException {
		final BufferedImage inputFile = 
				ImageIO.read(new File("./external/res/somePh.png"));
		final String textual = ocr.doOcr(api, inputFile);
		System.out.println(textual);
	}

	@Test
	@TimedExecution
	public void ocrFromBufferedImage_SingleFile_Test() throws IOException {
		final String textual = ocr.doOcr(api, bImage);
		System.out.println(textual);
	}

	@Test
	@Ignore
	public void interpretMultipleImagesTest() throws IOException {
		String imagePath = "./external/res/t1.png";
		String textual = ocr.doOcr(api, imagePath);
		System.out.println(textual);

		imagePath = "./external/res/test2.jpg";
		textual = ocr.doOcr(api, imagePath);
		System.out.println(textual);
	}
}
