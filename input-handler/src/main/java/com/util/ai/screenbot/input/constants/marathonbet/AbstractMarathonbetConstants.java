package com.util.ai.screenbot.input.constants.marathonbet;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractMarathonbetConstants {

	private static final Color MARATHON_LIGHT_GREEN = new Color(232, 255, 230);

	private static final Color MARATHON_GREEN = new Color(0, 143, 76);

	private static final Color MARATHON_WHITE = new Color(255, 255, 255);

	private static final List<Color> MARATHON_RED = Arrays.asList(new Color(223, 33, 41), new Color(203, 33, 41));

	public abstract Float getBettingSlipHeight();

	public abstract Float getBettingSlipWidth();

	public abstract Float getRemoveAllButtonWidth();

	public abstract Float getBetButtonWidth();

	public abstract Float getBettingButtonsHeight();

	public abstract Float getBetInputStakeWidth();

	public abstract Float getBetInputOddsWidth();

	public abstract Float getBetInputStakeHeight();

	public abstract Float getBetInputOddsHeight();

	public abstract Float getOddsScreenshotWidth();

	public abstract Float getOddsScreenshotHeight();

	public abstract Float getNeutralClickWidth();

	public abstract Float getNeutralClickHeight();

	public abstract Float getMaxStakeWidth();

	public abstract Float getMinStakeWidth();

	public abstract Float getMinMaxStakeHeight();

	public abstract Float getMinMaxStakeScreenshotWidth();

	public abstract Float getMinMaxStakeScreenshotHeight();

	public abstract Float getBalanceWidth();

	public abstract Float getBalanceHeight();

	public abstract Float getBalanceScreenshotWidth();

	public abstract Float getBalanceScreenshotHeight();

	public abstract Integer getDeviation();

	public Color getMarathonbetGreen() {
		return MARATHON_GREEN;
	}

	public Color getMarathonbetLightGreen() {
		return MARATHON_LIGHT_GREEN;
	}

	public Color getMarathonbetWhite() {
		return MARATHON_WHITE;
	}

	public List<Color> getMarathonbetRed() {
		return MARATHON_RED;
	}
}
