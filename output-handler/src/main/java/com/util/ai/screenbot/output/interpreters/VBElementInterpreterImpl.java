package com.util.ai.screenbot.output.interpreters;


import java.awt.image.BufferedImage;
import java.util.Objects;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBScreenElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.image.BWImageProcessor;

public class VBElementInterpreterImpl<E extends VBScreenElement, P extends VBScreenElementParser<E>> 
	implements VBElementInterpreter<E> {
	
	private final OCR ocr;
	
	private final P parser;
	
	private final BWImageProcessor imageProcessor;
	
	private final boolean negative;
		
	public VBElementInterpreterImpl(OCR ocr, P parser, BWImageProcessor imageProcessor, boolean negative) {
		this.ocr = Objects.requireNonNull(ocr);
		this.parser = Objects.requireNonNull(parser);
		this.imageProcessor = Objects.requireNonNull(imageProcessor);
		this.negative = negative;
	}

	@Override
	public E interpret(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException {
		try {
			final BufferedImage processedImage = imageProcessor.process(image, negative);
			final String textual = ocr.doOcr(tesseract, processedImage);
			return parser.parse(textual);
		} catch (Exception e) {
			throw new VBElementInterpretationException(
					String.format("Couldn't interpret the given image as element.", e));
		}
	}
}
