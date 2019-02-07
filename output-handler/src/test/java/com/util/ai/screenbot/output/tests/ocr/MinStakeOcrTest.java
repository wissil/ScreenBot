package com.util.ai.screenbot.output.tests.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.ocr.conf.marathon.VBMarathonMinStakeOcrConf;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.output.ocr.OcrImageProcessor;
import com.util.ai.screenbot.output.ocr.OcrReadMode;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class MinStakeOcrTest extends OutputHandlerTestBase {

	@Inject
	private OCR ocr;

	@Inject
	private OcrImageProcessor imageProcessor;
	
	private static OcrImageProcessingConf conf;
	
	@BeforeClass
	public static void setup() {
		conf = new VBMarathonMinStakeOcrConf();
	}

	@Test
	public void ocrTest() throws Exception {
		final File root = new File("./external/minStake/");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			image = imageProcessor.process(image, conf);

			String result = ocr.doOcr(image, OcrReadMode.ENGLISH);
			double d = CustomNumberFormat.parseDouble(result);
			System.out.println(f.getName() + ": " + result + ", d: " + d);
		}
	}

	@Test
	public void betInfoImageTest() throws IOException {
		final File f = new File("./external/minStake/latest.png");
		final BufferedImage in = ImageIO.read(f);
		final BufferedImage out = imageProcessor.process(in, conf);
		ImageIO.write(out, "png", new File("./external/out/" + f.getName()));
		String result = ocr.doOcr(out, conf.OCR_READ_MODE());
		System.out.println(result);
	}
}
