package com.util.ai.screenbot.output.logic;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.constants.VBScreenElementType;

public interface VBElementInterpreter {

	VBScreenElement interpretOddsInputImage(TessBaseAPI tesseract, 
			BufferedImage image, VBScreenElementType type) throws VBElementInterpretationException;
}
