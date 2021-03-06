package com.util.ai.screenbot.main.handlers.output;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public interface OutputHandler {

	VBSingleBetElement readSingleBet(BufferedImage image) 
			throws VBElementInterpretationException;
	
	VBBookmakerOddsElement readBookmakerOdds(BufferedImage image, Bookie bookie)
			throws VBElementInterpretationException;
	
	VBBetInfoElement readBetInfo(BufferedImage image)
			throws VBElementInterpretationException;
	
	VBBrowsingStatusElement readBrowsingStatus(BufferedImage image)
			throws VBElementInterpretationException;
	
	VBBalanceElement readBalance(BufferedImage image, Bookie bookie)
			throws VBElementInterpretationException;
	
	VBBookmakerMinStakeElement readMinStake(BufferedImage image, Bookie bookie)
			throws VBElementInterpretationException;
	
	VBBookmakerMaxStakeElement readMaxStake(BufferedImage image, Bookie bookie)
			throws VBElementInterpretationException;
}
