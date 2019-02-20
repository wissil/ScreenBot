package com.util.ai.screenbot.bookie.core.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.bookie.core.gui.ocr.config.VBBetInfoOcrConf;
import com.util.ai.screenbot.bookie.core.test.config.BookieCoreTestBase;
import com.util.ai.screenbot.ocr.OCR;
import com.util.ai.screenbot.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.ocr.OcrImageProcessor;
import com.util.ai.screenbot.ocr.OcrReadMode;

public class BetInfoOcrTest extends BookieCoreTestBase {

	@Inject
	private OCR ocr;

	@Inject
	private OcrImageProcessor imageProcessor;
	
	private static OcrImageProcessingConf conf;
	
	@BeforeClass
	public static void setup() {
		conf = new VBBetInfoOcrConf();
	}

	@Test
	public void ocrTest() throws Exception {
		final File root = new File("../external/betInfo/");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			image = imageProcessor.process(image, conf);

			String result = ocr.doOcr(image, OcrReadMode.DIGITS);
			System.out.println(result);
		}
	}

	@Test
	public void betInfoImageTest() throws IOException {
		final File f = new File("../external/betInfo/e2.png");
		final BufferedImage in = ImageIO.read(f);
		final BufferedImage out = imageProcessor.process(in, conf);
		ImageIO.write(out, "png", new File("./external/out/" + f.getName()));
		String result = ocr.doOcr(out, conf.OCR_READ_MODE());
		System.out.println(result);
	}
}
