package com.util.ai.screenbot.marathonbet.interpret;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.util.ai.screenbot.bookie.core.gui.common.VBBetInfoGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBBrowsingStatusGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBSingleBetGui;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBalanceElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBSingleBetElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBalanceGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerOddsGui;
import com.util.ai.screenbot.marathon.test.config.MarathonTestBase;
import com.util.ai.screenbot.marathonbet.ocr.config.VBMarathonBalanceOcrConf;
import com.util.ai.screenbot.marathonbet.ocr.config.VBMarathonMaxStakeOcrConf;
import com.util.ai.screenbot.marathonbet.ocr.config.VBMarathonMinStakeOcrConf;
import com.util.ai.screenbot.marathonbet.ocr.config.VBMarathonOddsOcrConf;

import static com.util.ai.screenbot.bookie.core.gui.elements.interpret.VBElementInterpreterProvider.*;


public class ScreenElementInterpretationTest extends MarathonTestBase {
	
	@Test
	public void singleBetElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/singleBets/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBSingleBetGui gui = new VBSingleBetGui(image);
			final VBSingleBetElement element = singleBetInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
	
	@Test
	public void bookmakerOddsElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/bookmakerOdds/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBookmakerOddsGui gui = new VBBookmakerOddsGui(image, new VBMarathonOddsOcrConf());
			final VBBookmakerOddsElement element = bookmakerOddsInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
	
	@Test
	public void betInfoElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/betInfo/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBetInfoGui gui = new VBBetInfoGui(image);
			final VBBetInfoElement element = betInfoInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
	
	@Test
	public void browsingStatusElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/browsingStatus/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBrowsingStatusGui gui = new VBBrowsingStatusGui(image);
			final VBBrowsingStatusElement element = browsingStatusInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
	
	@Test
	public void balanceElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/balance/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBalanceGui gui = new VBBalanceGui(image, new VBMarathonBalanceOcrConf());
			final VBBalanceElement element = balanceInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
	
	@Test
	public void maxStakeElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/maxStake/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBookmakerMaxStakeGui gui = new VBBookmakerMaxStakeGui(image, new VBMarathonMaxStakeOcrConf());
			final VBBookmakerMaxStakeElement element = bookmakerMaxStakeInterpreter().interpret(gui);
			System.out.println(element);
		}
	}
	
	@Test
	public void minStakeElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("../external/minStake/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBookmakerMinStakeGui gui = new VBBookmakerMinStakeGui(image, new VBMarathonMinStakeOcrConf());
			final VBBookmakerMinStakeElement element = bookmakerMinStakeInterpreter().interpret(gui);
			System.out.println(f.getName() + ": " + element);
		}
	}
}
