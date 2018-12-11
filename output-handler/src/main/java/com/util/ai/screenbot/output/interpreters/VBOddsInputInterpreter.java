package com.util.ai.screenbot.output.interpreters;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBOddsInputElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.constants.VBScreenElementType;

public class VBOddsInputInterpreter extends AbstractVBElementInterpreter<VBOddsInputElement, VBOddsInputElementParser> {

	public VBOddsInputInterpreter(OCR ocr, VBOddsInputElementParser parser) {
		super(ocr, parser);
	}

	@Override
	public VBOddsInputElement interpret(TessBaseAPI tesseract, BufferedImage image)
			throws VBElementInterpretationException {
		try {
			final String textual = ocr.doOcr(tesseract, image);
			return parser.parse(textual);
		} catch (Exception e) {
			throw new VBElementInterpretationException(
					String.format("Couldn't interpret the given image as element type %s.", 
							VBScreenElementType.VB_ODDS_INPUT), e);
		}
	}

}
