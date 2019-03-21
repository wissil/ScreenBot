package com.util.ai.screenbot.output.elements.ocr.conf.williamhill;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.bookie.BookieGraphics;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBalanceGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;

public class WilliamHillGraphics implements BookieGraphics {
	
	private static final VBWilliamHillBalanceOcrConf BALANCE_CONFIG = new VBWilliamHillBalanceOcrConf();
	
	private static final VBWilliamHillMinStakeOcrConf MIN_STAKE_CONFIG = new VBWilliamHillMinStakeOcrConf();
	
	private static final VBWilliamHillMaxStakeOcrConf MAX_STAKE_CONFIG = new VBWilliamHillMaxStakeOcrConf();
	
	private static final VBWilliamHillOddsOcrConf ODDS_CONFIG = new VBWilliamHillOddsOcrConf();
	
	@Override
	public VBBalanceGui getBalanceGui(BufferedImage image) {
		return new VBBalanceGui(image, BALANCE_CONFIG);
	}

	@Override
	public VBBookmakerMinStakeGui getMinStakeGui(BufferedImage image) {
		return new VBBookmakerMinStakeGui(image, MIN_STAKE_CONFIG);
	}

	@Override
	public VBBookmakerMaxStakeGui getMaxStageGui(BufferedImage image) {
		return new VBBookmakerMaxStakeGui(image, MAX_STAKE_CONFIG);
	}

	@Override
	public VBBookmakerOddsGui getOddsGui(BufferedImage image) {
		return new VBBookmakerOddsGui(image, ODDS_CONFIG);
	}

}
