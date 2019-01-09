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
	public void betInfoImageTest() throws IOException {
		final File f = new File("./external/primjeri_in/singleBets/r1.png");
		final BufferedImage in = ImageIO.read(f);
		final BufferedImage out = processor.process(in, Boolean.TRUE);
		ImageIO.write(out, "png", new File("./external/primjeri_out/singleBets/" + f.getName()));
	}
}
