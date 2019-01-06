package com.util.ai.screenbot.main.handlers.output;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

import static com.util.ai.screenbot.main.elements.VBScreenElementType.*;


public class OutputHandlerImpl implements OutputHandler {
	
	private final TessBaseAPI tesseract;
	
	public OutputHandlerImpl(
			TessBaseAPI tesseract) {
		this.tesseract = Objects.requireNonNull(tesseract);
	}

	@Override
	public VBSingleBetElement readSingleBet(BufferedImage image) 
			throws VBElementInterpretationException {
		return (VBSingleBetElement) SINGLE_BET.getInterpreter().interpret(tesseract, image);
	}

	@Override
	public VBBookmakerOddsElement readBookmakerOdds(BufferedImage image) 
			throws VBElementInterpretationException {
		return (VBBookmakerOddsElement) BOOKMAKER_ODDS.getInterpreter().interpret(tesseract, image);
	}

	@Override
	public VBBetInfoElement readBetInfo(BufferedImage image) 
			throws VBElementInterpretationException {
		return (VBBetInfoElement) BET_INFO.getInterpreter().interpret(tesseract, image);
	}

}
