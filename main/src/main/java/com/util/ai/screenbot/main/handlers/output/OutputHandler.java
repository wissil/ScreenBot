package com.util.ai.screenbot.main.handlers.output;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public interface OutputHandler {

	VBSingleBetElement readSingleBet(BufferedImage image) 
			throws VBElementInterpretationException;
	
	VBBookmakerOddsElement readBookmakerOdds(BufferedImage image)
			throws VBElementInterpretationException;
	
	VBBetInfoElement readBetInfo(BufferedImage image)
			throws VBElementInterpretationException;
}
