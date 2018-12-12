package com.util.ai.screenbot.output.tests.interpreters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.logic.VBOutputInterpreter;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class ScreenElementInterpretationTest extends OutputHandlerTestBase {
	
	private static final String TESSDATA_PATH = "./tessdata";
	
	private static final String LANGUAGE = "eng";

	@Inject
	private VBOutputInterpreter interpreter;
	
	private static TessBaseAPI tesseract;
	
	
	@BeforeClass
	public static void setup() throws IOException {
		// init API
		tesseract = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
	}
	
	@Test
	public void singleBetElementTest() throws VBElementInterpretationException, IOException {
		final BufferedImage image = ImageIO.read(new File("./external/res/jakoKvalitetno-kliknuto.png"));
		VBSingleBetElement element = interpreter.interpretSingleBet(tesseract, image);
		System.out.println(element);
	}
	
	@Test
	public void oddsInputElementTest() throws VBElementInterpretationException, IOException {
		final BufferedImage image = ImageIO.read(new File("./external/res/t1.png"));
		VBOddsInputElement element = interpreter.interpretOddsInput(tesseract, image);
		System.out.println(element);
	}
	
	@AfterClass
	public static void after() {
		// close API
		TesseractAPI.destroyTesseract(tesseract);
	}
}
