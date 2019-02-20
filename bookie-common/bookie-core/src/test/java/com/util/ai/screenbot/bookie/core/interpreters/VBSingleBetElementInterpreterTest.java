package com.util.ai.screenbot.bookie.core.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.bookie.core.gui.common.VBSingleBetGui;
import com.util.ai.screenbot.bookie.core.gui.elements.VBSingleBetElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.bookie.core.test.config.BookieCoreTestBase;

import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.singleBetInterpreter;


public class VBSingleBetElementInterpreterTest extends BookieCoreTestBase {
	
	@Test
	public void singleBetTest() throws IOException, VBElementInterpretationException {
		final File root = new File("../external/singleBets/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBSingleBetGui gui = new VBSingleBetGui(image);
			final VBSingleBetElement element = singleBetInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
}
