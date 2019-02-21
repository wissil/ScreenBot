package com.util.ai.screenbot.bookie.core.ocr;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.bookie.core.gui.ocr.config.VBSingleBetOcrConf;
import com.util.ai.screenbot.bookie.core.test.config.BookieCoreTestBase;
import com.util.ai.screenbot.ocr.OCR;
import com.util.ai.screenbot.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.ocr.OcrImageProcessor;

public class SingleBetOcrTest extends BookieCoreTestBase {

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
		final File root = new File("../external/singleBets/");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			image = imageProcessor.process(image, conf);

			String result = ocr.doOcr(image, conf.OCR_READ_MODE());
			System.out.println(result);
		}
	}
}
