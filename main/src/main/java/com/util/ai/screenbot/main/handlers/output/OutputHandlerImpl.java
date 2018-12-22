package com.util.ai.screenbot.main.handlers.output;

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

public class OutputHandlerImpl implements OutputHandler {
	
	private final TessBaseAPI tesseract;
	
	private final VBSingleBetInterpreter singleBetInterpreter;
	
	private final VBOddsInputInterpreter oddsInputInterpreter;
	
	private final VBPlaceBetInterpreter placeBetInterpreter;
	
	public OutputHandlerImpl(
			VBSingleBetInterpreter singleBetInterpreter,
			VBOddsInputInterpreter oddsInputInterpreter,
			VBPlaceBetInterpreter placeBetInterpreter,
			TessBaseAPI tesseract) {
		this.tesseract = Objects.requireNonNull(tesseract);
		this.singleBetInterpreter = Objects.requireNonNull(singleBetInterpreter);
		this.oddsInputInterpreter = Objects.requireNonNull(oddsInputInterpreter);
		this.placeBetInterpreter = Objects.requireNonNull(placeBetInterpreter);
	}

	@Override
	public VBSingleBetElement readSingleBet(BufferedImage image) 
			throws VBElementInterpretationException {
		return singleBetInterpreter.interpret(tesseract, image);
	}

	@Override
	public VBPlaceBetElement readPlaceBet(BufferedImage image) 
			throws VBElementInterpretationException {
		return placeBetInterpreter.interpret(tesseract, image);
	}

	@Override
	public VBOddsInputElement readOddsInput(BufferedImage image) 
			throws VBElementInterpretationException {
		return oddsInputInterpreter.interpret(tesseract, image);
	}

}
