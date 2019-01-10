package com.util.ai.screenbot.output.tests.ocr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.ocr.conf.general.VBSingleBetOcrConf;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.output.ocr.OcrImageProcessor;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class SingleBetOcrTest extends OutputHandlerTestBase {

	@Inject
	private OCR ocr;

	@Inject
	private OcrImageProcessor imageProcessor;
	
	private static OcrImageProcessingConf conf;
	
	@BeforeClass
	public static void setup() {
		conf = new VBSingleBetOcrConf();
	}

	@Test
	public void ocrTest() throws Exception {
		final File root = new File("./external/singleBets/");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			image = imageProcessor.process(image, conf);

			String result = ocr.doOcr(image, conf.OCR_READ_MODE());
			System.out.println(result);
		}
	}
}
