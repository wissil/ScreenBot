package com.util.ai.screenbot.output.tests.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.gui.general.VBBrowsingStatusGui;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.browsingStatusInterpreter;


public class VBBrowsingStatusElementInterpreterTest extends OutputHandlerTestBase {
	
	@Test
	public void betInfoTest() throws IOException, VBElementInterpretationException {
		final File root = new File("./external/browsingStatus/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBrowsingStatusGui gui = new VBBrowsingStatusGui(image);
			final VBBrowsingStatusElement element = browsingStatusInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
}
