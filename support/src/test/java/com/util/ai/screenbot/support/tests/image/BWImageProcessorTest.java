package com.util.ai.screenbot.support.tests.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.support.image.BWImageProcessor;
import com.util.ai.screenbot.support.tests.config.SupportTestBase;

public class BWImageProcessorTest extends SupportTestBase {

	@Inject
	private BWImageProcessor processor;
	
	@Test
	public void negativeTest() throws IOException {
		BufferedImage image = 
				ImageIO.read(new File("./external/res/price.jpg"));
		
		image = processor.process(image, Boolean.FALSE);

		ImageIO.write(image, "png", new File("./external/output/price_o1.png"));
	}
}
