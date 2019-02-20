package com.util.ai.screenbot.main.handlers.output;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.bookie.core.gui.elements.VBBalanceElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBSingleBetElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.main.bookie.Bookie;
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
