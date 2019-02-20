package com.util.ai.screenbot.bookie.common.gui;

import java.awt.image.BufferedImage;

public interface BookieGraphics {

	VBBalanceGui getBalanceGui(BufferedImage image);
	
	VBBookmakerMinStakeGui getMinStakeGui(BufferedImage image);
	
	VBBookmakerMaxStakeGui getMaxStageGui(BufferedImage image);
	
	VBBookmakerOddsGui getOddsGui(BufferedImage image);
}
