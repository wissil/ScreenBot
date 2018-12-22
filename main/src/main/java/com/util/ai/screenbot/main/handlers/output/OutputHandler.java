package com.util.ai.screenbot.main.handlers.output;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public interface OutputHandler {

	VBSingleBetElement readSingleBet(BufferedImage image) 
			throws VBElementInterpretationException;
	
	VBPlaceBetElement readPlaceBet(BufferedImage image)
			throws VBElementInterpretationException;
	
	VBOddsInputElement readOddsInput(BufferedImage image)
			throws VBElementInterpretationException;
}
