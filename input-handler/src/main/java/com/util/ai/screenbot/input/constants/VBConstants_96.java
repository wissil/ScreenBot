package com.util.ai.screenbot.input.constants;

public class VBConstants_96 extends AbstractVBConstants {

    private static final Float TOP_BET_UPPER_CORNER_HEIGHT = 0.112f; // 10.6% from top

    private static final Float TOP_BET_LOWER_CORNER_HEIGHT = 0.132f; // 12.6% from top

    private static final Float TOP_BET_CORNER_WIDTH = 0.1845f; // 18.45% from left

    private static final Float TOP_BET_MIDDLE_HEIGHT = 0.121f; // 11.2% from top

    private static final Float TOP_BET_MIDDLE_WIDTH = 0.35f; // 30.0% from left

    private static final Float BET_SCREENSHOT_WIDTH = 0.34f; // 34.0% right from left upper corner

    private static final Float BET_SCREENSHOT_HEIGHT = 0.02f; // 2% down from left upper corner

    @Override
    public Float getTopBetLowerCornerHeight() {

        return TOP_BET_LOWER_CORNER_HEIGHT;
    }

    @Override
    public Float getTopBetUpperCornerHeight() {

        return TOP_BET_UPPER_CORNER_HEIGHT;
    }

    @Override
    public Float getTopBetCornerWidth() {

        return TOP_BET_CORNER_WIDTH;
    }

    @Override
    public Float getTopBetMiddleHeight() {

        return TOP_BET_MIDDLE_HEIGHT;
    }

    @Override
    public Float getTopBetMiddleWidth() {

        return TOP_BET_MIDDLE_WIDTH;
    }

    @Override
    public Float getBetScreenshotWidth() {

        return BET_SCREENSHOT_WIDTH;
    }

    @Override
    public Float getBetScreenshotHeight() {

        return BET_SCREENSHOT_HEIGHT;
    }
}
