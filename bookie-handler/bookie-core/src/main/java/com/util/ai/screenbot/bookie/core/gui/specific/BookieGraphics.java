package com.util.ai.screenbot.bookie.core.gui.specific;

import java.awt.image.BufferedImage;


public interface BookieGraphics {

	VBBalanceGui getBalanceGui(BufferedImage image);
	
	VBBookmakerMinStakeGui getMinStakeGui(BufferedImage image);
	
	VBBookmakerMaxStakeGui getMaxStageGui(BufferedImage image);
	
	VBBookmakerOddsGui getOddsGui(BufferedImage image);
}
