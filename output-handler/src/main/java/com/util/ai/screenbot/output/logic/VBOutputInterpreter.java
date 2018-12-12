package com.util.ai.screenbot.output.logic;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.image.ImageProcessor;

public abstract class VBOutputInterpreter {
	
	private final ImageProcessor processor;
	
	public VBOutputInterpreter(ImageProcessor processor) {
		this.processor = Objects.requireNonNull(processor);
	}
	
	private BufferedImage preprocess(BufferedImage image, boolean negative) {
		return processor.process(image, negative);
	}
	
	public VBOddsInputElement interpretOddsInput(TessBaseAPI tesseract, BufferedImage image) 
			throws VBElementInterpretationException {
		final BufferedImage processedImage = preprocess(image, Boolean.FALSE);
		return _interpretOddsInput(tesseract, processedImage);
	}
	
	public VBPlaceBetElement interpretPlaceBet(TessBaseAPI tesseract, BufferedImage image) 
			throws VBElementInterpretationException {
		final BufferedImage processedImage = preprocess(image, Boolean.FALSE);
		return _interpretPlaceBet(tesseract, processedImage);
	}
	
	public VBSingleBetElement interpretSingleBet(TessBaseAPI tesseract, BufferedImage image) 
			throws VBElementInterpretationException {
		final BufferedImage processedImage = preprocess(image, Boolean.TRUE);
		return _interpretSingleBet(tesseract, processedImage);
	}

	abstract VBOddsInputElement _interpretOddsInput(TessBaseAPI tesseract, BufferedImage image) 
			throws VBElementInterpretationException;
	
	abstract VBPlaceBetElement _interpretPlaceBet(TessBaseAPI tesseract, BufferedImage image) 
			throws VBElementInterpretationException;
	
	abstract VBSingleBetElement _interpretSingleBet(TessBaseAPI tesseract, BufferedImage image)
			throws VBElementInterpretationException;
}
