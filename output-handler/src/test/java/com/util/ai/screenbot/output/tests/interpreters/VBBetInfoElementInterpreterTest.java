package com.util.ai.screenbot.output.tests.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.gui.general.VBBetInfoGui;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.betInfoInterpreter;


public class VBBetInfoElementInterpreterTest extends OutputHandlerTestBase {
	
	@Test
	public void betInfoTest() throws IOException, VBElementInterpretationException {
		final File root = new File("./external/betInfo/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBetInfoGui gui = new VBBetInfoGui(image);
			final VBBetInfoElement element = betInfoInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
}
