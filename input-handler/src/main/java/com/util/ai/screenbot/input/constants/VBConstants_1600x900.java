package com.util.ai.screenbot.input.constants;

public class VBConstants_1600x900 extends AbstractVBConstants {

	private static final Float TOP_BET_UPPER_CORNER_HEIGHT = 0.112f; // 10.6% from top

	private static final Float TOP_BET_LOWER_CORNER_HEIGHT = 0.132f; // 12.6% from top

	private static final Float TOP_BET_CORNER_WIDTH = 0.195f; // 18.45% from left

	private static final Float TOP_BET_MIDDLE_HEIGHT = 0.121f; // 11.2% from top

	private static final Float TOP_BET_MIDDLE_WIDTH = 0.35f; // 30.0% from left

	private static final Float BET_SCREENSHOT_WIDTH = 0.38f; // 34.0% right from left upper corner

	private static final Float BET_SCREENSHOT_HEIGHT = 0.02f; // 2% down from left upper corner

	private static final Float REMOVE_BET_MOUSE_MOVEMENT_HEIGHT = 0.02f; // 2% down from middle of top bet

	private static final Float REMOVE_BET_MOUSE_MOVEMENT_WIDTH = 0.1f; // 1% right from top bet

	private static final Float ODDS_INFO_HEIGHT = 0.17f;

	private static final Float STAKE_INFO_HEIGHT = 0.195f;

	private static final Float VALUE_INFO_HEIGHT = 0.225f;

	private static final Float INFO_WIDTH = 0.0495f;

	private static final Float CANCEL_BUTTON_WIDTH = 0.96f;

	private static final Float CONFIRM_BUTTON_WIDTH = 0.88f;

	private static final Float BROWSER_BUTTONS_HEIGHT = 0.965f;

	private static final Float CONFIRM_OK_BUTTON_WIDTH = 0.6f;

	private static final Float CONFIRM_OK_BUTTON_HEIGHT = 0.6f;

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

	@Override
	public Float getRemoveBetMouseMovementHeight() {

		return REMOVE_BET_MOUSE_MOVEMENT_HEIGHT;
	}

	@Override
	public Float getRemoveBetMouseMovementWidth() {
		return REMOVE_BET_MOUSE_MOVEMENT_WIDTH;
	}

	@Override
	public Float getOddsInfoHeight() {
		return ODDS_INFO_HEIGHT;
	}

	@Override
	public Float getInfoWidth() {
		return INFO_WIDTH;
	}

	@Override
	public Float getStakeInfoHeight() {
		return STAKE_INFO_HEIGHT;
	}

	@Override
	public Float getValueInfoHeight() {
		return VALUE_INFO_HEIGHT;
	}

	@Override
	public Float getBrowserButtonsHeight() {

		return BROWSER_BUTTONS_HEIGHT;
	}

	@Override
	public Float getCancelButtonWidth() {
		return CANCEL_BUTTON_WIDTH;
	}

	@Override
	public Float getConfirmButtonWidth() {
		return CONFIRM_BUTTON_WIDTH;
	}

	@Override
	public Float getConfirmOkButtonWidth() {
		return CONFIRM_OK_BUTTON_WIDTH;
	}

	@Override
	public Float getConfirmOkButtonHeight() {
		return CONFIRM_OK_BUTTON_HEIGHT;
	}

	@Override
	public Float getBetInfoScreenshotWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getBetInfoScreenshotHeight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getBrowsingStatusWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getBrowsingStatusHeight() {
		// TODO Auto-generated method stub
		return null;
	}

}
