package com.util.ai.screenbot.output.elements.ocr.conf.expert;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.bookie.BookieGraphics;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBalanceGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;

public class ExpertGraphics implements BookieGraphics {
	
	private static final VBExpertBalanceOcrConf BALANCE_CONFIG = new VBExpertBalanceOcrConf();
	
	private static final VBExpertMinStakeOcrConf MIN_STAKE_CONFIG = new VBExpertMinStakeOcrConf();
	
	private static final VBExpertMaxStakeOcrConf MAX_STAKE_CONFIG = new VBExpertMaxStakeOcrConf();
	
	private static final VBExpertOddsOcrConf ODDS_CONFIG = new VBExpertOddsOcrConf();
	
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
