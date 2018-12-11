package com.util.ai.screenbot.output.interpreters;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.constants.VBScreenElementType;

public class VBSingleBetInterpreter extends AbstractVBElementInterpreter<VBSingleBetElement> {
	
	private final VBSingleBetElementParser parser;

	public VBSingleBetInterpreter() {
		this.parser = new VBSingleBetElementParser();
	}

	@Override
	public VBSingleBetElement interpret(TessBaseAPI tesseract, BufferedImage image)
			throws VBElementInterpretationException {
		try {
			final String textual = ocr.doOcr(tesseract, image);
			return parser.parse(textual);
		} catch (Exception e) {
			throw new VBElementInterpretationException(
					String.format("Couldn't interpret the given image as element type %s.", 
							VBScreenElementType.VB_SINGLE_BET), e);
		}
	}

}
