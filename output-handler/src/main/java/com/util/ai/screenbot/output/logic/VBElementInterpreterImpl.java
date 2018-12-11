package com.util.ai.screenbot.output.logic;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.constants.VBScreenElementType;

public class VBElementInterpreterImpl implements VBElementInterpreter {
	
	private final OCR ocr;
	
	private final VBScreenElementParserProvider factory;
	
	public VBElementInterpreterImpl(OCR ocr, VBScreenElementParserProvider factory) {
		this.ocr = Objects.requireNonNull(ocr);
		this.factory = Objects.requireNonNull(factory);
	}

	@Override
	public VBScreenElement interpretOddsInputImage(TessBaseAPI tesseract, BufferedImage image,
			VBScreenElementType type) throws VBElementInterpretationException {		
		try {
			final String textual = ocr.doOcr(tesseract, image);
			return factory.provideParser(type).parse(textual);
		} catch (Exception e) {
			throw new VBElementInterpretationException(
					String.format("Couldn't interpret the given image as element type %s.", type), e);
		}
	}

}
