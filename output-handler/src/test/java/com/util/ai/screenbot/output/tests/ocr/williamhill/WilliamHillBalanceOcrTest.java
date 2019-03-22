package com.util.ai.screenbot.output.tests.ocr.williamhill;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.ocr.conf.williamhill.VBWilliamHillBalanceOcrConf;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.output.ocr.OcrImageProcessor;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class WilliamHillBalanceOcrTest extends OutputHandlerTestBase {

	@Inject
	private OCR ocr;

	@Inject
	private OcrImageProcessor imageProcessor;
	
	private static OcrImageProcessingConf conf;
	
	@BeforeClass
	public static void setup() {
		conf = new VBWilliamHillBalanceOcrConf();
	}

	@Test
	public void ocrTest() throws Exception {
		final File root = new File("./external/williamhill/balance");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			image = imageProcessor.process(image, conf);
			
			ImageIO.write(image, "png", new File("./out/williamhill/balance/out_" + f.getName()));

			String result = ocr.doOcr(image, conf.OCR_READ_MODE());
			System.out.println(result);
		}
	}
}
