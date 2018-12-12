package com.util.ai.screenbot.output.logic;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.interpreters.VBOddsInputInterpreter;
import com.util.ai.screenbot.output.interpreters.VBPlaceBetInterpreter;
import com.util.ai.screenbot.output.interpreters.VBSingleBetInterpreter;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.support.image.ImageProcessor;

public class VBOutputInterpreterImpl extends VBOutputInterpreter {
	
	private final VBOddsInputInterpreter oddsInputInterpreter;
	
	private final VBPlaceBetInterpreter placeBetInterpreter;
	
	private final VBSingleBetInterpreter singleBetInterpreter;
	
	public VBOutputInterpreterImpl(
			ImageProcessor processor,
			VBOddsInputInterpreter oddsInputInterpreter,
			VBPlaceBetInterpreter placeBetInterpreter,
			VBSingleBetInterpreter singleBetInterpreter) {
		super(processor);
		this.oddsInputInterpreter = Objects.requireNonNull(oddsInputInterpreter);
		this.placeBetInterpreter = Objects.requireNonNull(placeBetInterpreter);
		this.singleBetInterpreter = Objects.requireNonNull(singleBetInterpreter);
	}

	@Override
	protected VBOddsInputElement _interpretOddsInput(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException {
		return oddsInputInterpreter.interpret(tesseract, image);
	}

	@Override
	protected VBPlaceBetElement _interpretPlaceBet(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException {
		return placeBetInterpreter.interpret(tesseract, image);
	}

	@Override
	protected VBSingleBetElement _interpretSingleBet(TessBaseAPI tesseract, BufferedImage image) throws VBElementInterpretationException {
		return singleBetInterpreter.interpret(tesseract, image);
	}

}
