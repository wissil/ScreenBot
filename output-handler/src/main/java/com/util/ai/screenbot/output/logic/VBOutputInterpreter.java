package com.util.ai.screenbot.output.logic;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public interface VBOutputInterpreter {

	VBOddsInputElement interpretOddsInput(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException;
	
	VBPlaceBetElement interpretPlaceBet(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException;
	
	VBSingleBetElement interpretSingleBet(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException;
}
