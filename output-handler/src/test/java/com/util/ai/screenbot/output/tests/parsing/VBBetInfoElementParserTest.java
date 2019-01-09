package com.util.ai.screenbot.output.tests.parsing;


import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBBetInfoStakeElement;
import com.util.ai.screenbot.output.elements.VBBetInfoValueElement;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrReadMode;
import com.util.ai.screenbot.output.parsing.VBBetInfoStakeElementParser;
import com.util.ai.screenbot.output.parsing.VBBetInfoValueElementParser;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.image.BWImageProcessor;

public class VBBetInfoElementParserTest extends OutputHandlerTestBase {
	
	@Inject
	private OCR ocr;
	
	@Inject
	private BWImageProcessor imageProcessor;
	
	@Inject
	private VBBetInfoValueElementParser parser;
	
	@Inject
	private VBBetInfoStakeElementParser parserStake;
	
	@Test
	@Ignore
	public void testValueElementParsing() throws Exception  {
		final BufferedImage inputFile = 
				ImageIO.read(new File("./external/value/t1.png"));
				
		final BufferedImage inputAfter = 
				imageProcessor.process(inputFile, Boolean.FALSE);
		
		final String textual = ocr.doOcr(inputAfter, OcrReadMode.DIGITS);
		System.out.println(textual);
		
		final VBBetInfoValueElement element = parser.parse(textual);
		System.out.println(element);
	}
	
	@Test
	public void testStakeElementParsing() throws Exception  {
		final BufferedImage inputFile = 
				ImageIO.read(new File("./external/stake/t1.png"));
				
		final BufferedImage inputAfter = 
				imageProcessor.process(inputFile, Boolean.FALSE);
		
		ImageIO.write(inputAfter, "png", new File("./external/stake/out/o.png"));

		
		final String textual = ocr.doOcr(inputAfter, OcrReadMode.DIGITS);
		System.out.println(textual);
		
		final VBBetInfoStakeElement element = parserStake.parse(textual);
		System.out.println(element);
	}
}
