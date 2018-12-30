package com.util.ai.screenbot.input.constants.marathonbet;

import java.awt.Color;

public abstract class AbstractMarathonbetConstants {

	private static final Color MARATHON_LIGHT_GREEN = new Color(232, 255, 230);

	private static final Color MARATHON_GREEN = new Color(0, 143, 76);

	private static final Color MARATHON_RED = new Color(223, 33, 41);

	public abstract Float getBettingSlipHeight();

	public abstract Float getBettingSlipWidth();

	public abstract Float getRemoveAllButtonWidth();

	public abstract Float getBetButtonWidth();

	public abstract Float getBettingButtonsHeight();

	public abstract Float getBetInputStakeWidth();

	public abstract Float getBetInputOddsWidth();

	public abstract Float getBetInputStakeHeight();

	public abstract Float getBetInputOddsHeight();

	public abstract Float getStakeScreenshotWidth();

	public abstract Float getStakeScreenshotHeight();

	public abstract Float getNeutralClickWidth();

	public abstract Float getNeutralClickHeight();

	public Color getMarathonbetGreen() {
		return MARATHON_GREEN;
	}

	public Color getMarathonbetLightGreen() {
		return MARATHON_LIGHT_GREEN;
	}

	public Color getMarathonbetRed() {
		return MARATHON_RED;
	}
}
