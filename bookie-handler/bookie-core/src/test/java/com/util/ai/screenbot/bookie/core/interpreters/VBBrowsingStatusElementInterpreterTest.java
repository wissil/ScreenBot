package com.util.ai.screenbot.bookie.core.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.bookie.core.gui.common.VBBrowsingStatusGui;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.bookie.core.test.config.BookieCoreTestBase;

import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.browsingStatusInterpreter;


public class VBBrowsingStatusElementInterpreterTest extends BookieCoreTestBase {
	
	@Test
	public void betInfoTest() throws IOException, VBElementInterpretationException {
		final File root = new File("../external/browsingStatus/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBrowsingStatusGui gui = new VBBrowsingStatusGui(image);
			final VBBrowsingStatusElement element = browsingStatusInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
}
