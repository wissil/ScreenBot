package com.util.ai.screenbot.output.elements.gui.bookie;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.bookie.VBBalanceGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;

public interface BookieGraphics {

	VBBalanceGui getBalanceGui(BufferedImage image);
	
	VBBookmakerMinStakeGui getMinStakeGui(BufferedImage image);
	
	VBBookmakerMaxStakeGui getMaxStageGui(BufferedImage image);
	
	VBBookmakerOddsGui getOddsGui(BufferedImage image);
}
