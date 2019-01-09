package com.util.ai.screenbot.output.tests.ocr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrReadMode;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.image.BWImageProcessor;

public class BetInfoValueOcrTest extends OutputHandlerTestBase {

	@Inject
	private OCR ocr;
	
	@Inject
	private BWImageProcessor imageProcessor;
	
	@Test
	@Ignore
	public void ocrTest() throws Exception {
		final File root = new File("./external/value/");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			image = imageProcessor.process(image, Boolean.FALSE);

			String result = ocr.doOcr(image, OcrReadMode.DIGITS);
			System.out.println(result);
		}
	}
}
