package com.util.ai.screenbot.output.tests.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrReadMode;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.image.BWImageProcessor;
import com.util.ai.screenbot.support.testing.time.TimedExecution;

public class OcrTest extends OutputHandlerTestBase {

	@Inject
	private OCR ocr;
	
	@Inject
	private BWImageProcessor imageProcessor;

	private static BufferedImage bImage;

	@BeforeClass
	public static void setup() throws IOException {
		bImage = ImageIO.read(new File("./external/res/zelenilo.jpg"));
	}

	@Test
	@Ignore
	@TimedExecution
	public void ocrFromImagePath_SingleFile_Test() throws IOException {
		final BufferedImage inputFile = 
				ImageIO.read(new File("./external/primjeri/value/t1.png"));
		
		final String textual = ocr.doOcr(inputFile, OcrReadMode.DIGITS);
		System.out.println(textual);
		
		BufferedImage inputAfter = imageProcessor.process(inputFile, Boolean.FALSE);

		final String textualAfter = ocr.doOcr(inputAfter, OcrReadMode.DIGITS);
		System.out.println(textualAfter);
	}

	@Test
	@Ignore
	@TimedExecution
	public void ocrFromBufferedImage_SingleFile_Test() throws IOException {
		final String textual = ocr.doOcr(bImage, OcrReadMode.DIGITS);
		System.out.println(textual);
	}

	@Test
	@Ignore
	public void interpretMultipleImagesTest() throws IOException {
		String imagePath = "./external/res/t1.png";
		String textual = ocr.doOcr(imagePath, OcrReadMode.DIGITS);
		System.out.println(textual);

		imagePath = "./external/res/test2.jpg";
		textual = ocr.doOcr(imagePath, OcrReadMode.DIGITS);
		System.out.println(textual);
	}
}
