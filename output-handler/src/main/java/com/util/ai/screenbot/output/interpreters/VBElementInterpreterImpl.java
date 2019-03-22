package com.util.ai.screenbot.output.interpreters;


import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.elements.gui.VBGuiElement;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.OcrImageProcessor;
import com.util.ai.screenbot.output.parsing.VBScreenElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public class VBElementInterpreterImpl<E extends VBScreenElement, G extends VBGuiElement, P extends VBScreenElementParser<E>> 
	implements VBElementInterpreter<E, G> {
	
	private final OCR ocr;
	
	private final P parser;
	
	private final OcrImageProcessor imageProcessor;
			
	public VBElementInterpreterImpl(OCR ocr, P parser, OcrImageProcessor imageProcessor) {
		this.ocr = Objects.requireNonNull(ocr);
		this.parser = Objects.requireNonNull(parser);
		this.imageProcessor = Objects.requireNonNull(imageProcessor);
	}

	@Override
	public E interpret(G gui) throws VBElementInterpretationException {
		final BufferedImage processedImage = imageProcessor.process(gui.getImage(), gui.getConf());
		final String textual = ocr.doOcr(processedImage, gui.getConf().OCR_READ_MODE()).trim();
	
		try {
			return parser.parse(textual);
		} catch (Exception e) {
			throw new VBElementInterpretationException(
					String.format("Couldn't interpret the given image [%s] as element.", textual));
		}
	}
}
