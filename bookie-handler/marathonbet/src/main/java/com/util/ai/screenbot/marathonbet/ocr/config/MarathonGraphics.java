package com.util.ai.screenbot.marathonbet.ocr.config;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.bookie.core.gui.specific.BookieGraphics;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBalanceGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.bookie.core.gui.specific.VBBookmakerOddsGui;


public class MarathonGraphics implements BookieGraphics {
	
	private static final VBMarathonBalanceOcrConf BALANCE_CONFIG = new VBMarathonBalanceOcrConf();
	
	private static final VBMarathonMinStakeOcrConf MIN_STAKE_CONFIG = new VBMarathonMinStakeOcrConf();
	
	private static final VBMarathonMaxStakeOcrConf MAX_STAKE_CONFIG = new VBMarathonMaxStakeOcrConf();
	
	private static final VBMarathonOddsOcrConf ODDS_CONFIG = new VBMarathonOddsOcrConf();
	
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
