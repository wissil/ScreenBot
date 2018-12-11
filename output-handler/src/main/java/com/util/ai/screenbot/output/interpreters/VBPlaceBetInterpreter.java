package com.util.ai.screenbot.output.interpreters;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.parsing.VBPlaceBetElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.constants.VBScreenElementType;

public class VBPlaceBetInterpreter extends AbstractVBElementInterpreter<VBPlaceBetElement> {
	
	private final VBPlaceBetElementParser parser;

	public VBPlaceBetInterpreter() {
		this.parser = new VBPlaceBetElementParser();
	}

	@Override
	public VBPlaceBetElement interpret(TessBaseAPI tesseract, BufferedImage image)
			throws VBElementInterpretationException {
		try {
			final String textual = ocr.doOcr(tesseract, image);
			return parser.parse(textual);
		} catch (Exception e) {
			throw new VBElementInterpretationException(
					String.format("Couldn't interpret the given image as element type %s.", 
							VBScreenElementType.VB_PLACE_BET), e);
		}
	}

}
