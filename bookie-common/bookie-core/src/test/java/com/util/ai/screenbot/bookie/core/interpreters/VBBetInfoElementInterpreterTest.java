package com.util.ai.screenbot.bookie.core.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.bookie.core.gui.common.VBBetInfoGui;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.bookie.core.test.config.BookieCoreTestBase;

import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.betInfoInterpreter;


public class VBBetInfoElementInterpreterTest extends BookieCoreTestBase {
	
	@Test
	public void betInfoTest() throws IOException, VBElementInterpretationException {
		final File root = new File("../external/betInfo/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBetInfoGui gui = new VBBetInfoGui(image);
			final VBBetInfoElement element = betInfoInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
}
