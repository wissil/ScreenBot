package com.util.ai.screenbot.support.tests.colors;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.support.colors.ColorDeterminator;

public class ColorDeterminatorTest {

	@Test
	public void sampleTest() throws IOException {
		final File root = new File("./external/singleBets/");

		for (File f : root.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			final Color c = ColorDeterminator.determine(image);
			
			System.out.println(c);
		}
	}
}
