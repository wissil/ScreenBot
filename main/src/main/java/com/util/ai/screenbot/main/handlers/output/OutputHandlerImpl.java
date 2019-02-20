package com.util.ai.screenbot.main.handlers.output;

import static com.util.ai.screenbot.bookie.core.gui.elements.VBScreenElementType.*;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.bookie.core.gui.common.VBBetInfoGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBBrowsingStatusGui;
import com.util.ai.screenbot.bookie.core.gui.common.VBSingleBetGui;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBalanceElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBSingleBetElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBalanceGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerOddsGui;
import com.util.ai.screenbot.main.bookie.Bookie;


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
		final VBBookmakerOddsGui gui = bookie.getGraphics().getOddsGui(image);
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

	@Override
	public VBBalanceElement readBalance(BufferedImage image, Bookie bookie) throws VBElementInterpretationException {
		final VBBalanceGui gui = bookie.getGraphics().getBalanceGui(image);
		return (VBBalanceElement) BALANCE.getInterpreter().interpret(gui);
	}

	@Override
	public VBBookmakerMinStakeElement readMinStake(BufferedImage image, Bookie bookie) throws VBElementInterpretationException {
		final VBBookmakerMinStakeGui gui = bookie.getGraphics().getMinStakeGui(image);
		return (VBBookmakerMinStakeElement) BOOKMAKER_MIN_STAKE.getInterpreter().interpret(gui);
	}

	@Override
	public VBBookmakerMaxStakeElement readMaxStake(BufferedImage image, Bookie bookie) throws VBElementInterpretationException {
		final VBBookmakerMaxStakeGui gui = bookie.getGraphics().getMaxStageGui(image);
		return (VBBookmakerMaxStakeElement) BOOKMAKER_MAX_STAKE.getInterpreter().interpret(gui);
	}

}
