package com.util.ai.screenbot.output.tests.parsing;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.sourceforge.tess4j.Tesseract;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.image.BWImageProcessor;

public class VBSingleBetElementParserTest extends OutputHandlerTestBase {

	@Inject
	private VBSingleBetElementParser parser;
	
	@Inject
	private Tesseract tesseract;

	@Inject
	private BWImageProcessor processor;

	@Test
	public void ocrTest() throws Exception {
		final File root = new File("./external/singleBets/");

		for (File f : root.listFiles()) {
			BufferedImage textImage = ImageIO.read(f);
			textImage = processor.process(textImage, Boolean.TRUE);

			ImageIO.write(textImage, "png", new File("./external/out/singleBets/" + f.getName()));

			String result = tesseract.doOCR(textImage);
			System.out.println(result);
		}
	}
}
