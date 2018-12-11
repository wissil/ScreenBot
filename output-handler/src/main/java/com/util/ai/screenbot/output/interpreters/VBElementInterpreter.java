package com.util.ai.screenbot.output.interpreters;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public interface VBElementInterpreter<E extends VBScreenElement> {

	E interpret(TessBaseAPI tesseract, 
			BufferedImage image) throws VBElementInterpretationException;
}
