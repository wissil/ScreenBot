package com.util.ai.screenbot.output.tests.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.elements.gui.general.VBSingleBetGui;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.singleBetInterpreter;


public class VBSingleBetElementInterpreterTest extends OutputHandlerTestBase {
	
	@Test
	public void betInfoValueTest() throws IOException, VBElementInterpretationException {
		final File root = new File("./external/singleBets/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBSingleBetGui gui = new VBSingleBetGui(image);
			final VBSingleBetElement element = singleBetInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
}
