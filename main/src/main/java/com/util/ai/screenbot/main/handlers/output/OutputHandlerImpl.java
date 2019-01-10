package com.util.ai.screenbot.main.handlers.output;

import static com.util.ai.screenbot.output.elements.types.VBScreenElementType.*;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.main.bookie.Bookie;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBetInfoGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBrowsingStatusGui;
import com.util.ai.screenbot.output.elements.gui.general.VBSingleBetGui;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;


public class OutputHandlerImpl implements OutputHandler {

	@Override
	public VBSingleBetElement readSingleBet(BufferedImage image) 
			throws VBElementInterpretationException {
		final VBSingleBetGui gui = new VBSingleBetGui(image);
		return (VBSingleBetElement) SINGLE_BET.getInterpreter().interpret(gui);
	}

	@Override
	public VBBookmakerOddsElement readBookmakerOdds(BufferedImage image, Bookie bookie) 
			throws VBElementInterpretationException {
		final VBBookmakerOddsGui gui = new VBBookmakerOddsGui(image, bookie.getOcrConfig().getOddsOcrConfig());
		return (VBBookmakerOddsElement) BOOKMAKER_ODDS.getInterpreter().interpret(gui);
	}

	@Override
	public VBBetInfoElement readBetInfo(BufferedImage image) 
			throws VBElementInterpretationException {
		final VBBetInfoGui gui = new VBBetInfoGui(image);
		return (VBBetInfoElement) BET_INFO.getInterpreter().interpret(gui);
	}

	@Override
	public VBBrowsingStatusElement readBrowsingStatus(BufferedImage image) 
			throws VBElementInterpretationException {
		final VBBrowsingStatusGui gui = new VBBrowsingStatusGui(image);
		return (VBBrowsingStatusElement) BROWSING_STATUS.getInterpreter().interpret(gui);
	}

}
