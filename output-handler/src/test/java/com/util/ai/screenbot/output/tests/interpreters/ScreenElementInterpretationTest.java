package com.util.ai.screenbot.output.tests.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.junit.BeforeClass;
import org.junit.Test;

import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.*;


public class ScreenElementInterpretationTest extends OutputHandlerTestBase {
	
	private static final String TESSDATA_PATH = "./tessdata";
	
	private static final String LANGUAGE = "eng";
	
	private static TessBaseAPI tesseract;
	
	
	@BeforeClass
	public static void setup() throws IOException {
		// init API
		tesseract = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
	}
	
	@Test
	public void singleBetElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/singleBets/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBSingleBetElement element = singleBetInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
	
	@Test
	public void bookmakerOddsElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/bookmakerOdds/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBookmakerOddsElement element = bookmakerOddsInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
	
	@Test
	public void betInfoElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/betInfo/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBetInfoElement element = betInfoInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
	
	@Test
	public void browsingStatusElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/browsingStatus/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBrowsingStatusElement element = browsingStatusInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
	
	@Test
	public void balanceElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/balance/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBalanceElement element = balanceInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
	
	@Test
	public void maxStakeElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/maxStake/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBookmakerStakeElement element = bookmakerStakeInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
	
	@Test
	public void minStakeElementTest() throws VBElementInterpretationException, IOException {
		final File root = new File("./external/primjeri/minStake/");
		
		for (File f : root.listFiles()) {
			final BufferedImage image = ImageIO.read(f);
			final VBBookmakerStakeElement element = bookmakerStakeInterpreter().interpret(tesseract, image);
			System.out.println(element);
		}
	}
}
