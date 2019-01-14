package com.util.ai.screenbot.output.interpreters;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBalanceGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBetInfoGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBrowsingStatusGui;
import com.util.ai.screenbot.output.elements.gui.general.VBSingleBetGui;

public class VBElementInterpreterProvider {

	@Inject
	private static VBElementInterpreter<VBSingleBetElement, VBSingleBetGui> singleBetInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBookmakerOddsElement, ? super VBBookmakerOddsGui> bookmakerOddsInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBetInfoElement, VBBetInfoGui> betInfoInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBrowsingStatusElement, VBBrowsingStatusGui> browsingStatusInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBalanceElement, ? super VBBalanceGui> balanceInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBookmakerMinStakeElement, ? super VBBookmakerMinStakeGui> bookmakerMinStakeInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBookmakerMaxStakeElement, ? super VBBookmakerMaxStakeGui> bookmakerMaxStakeInterpreter;

	
	public static VBElementInterpreter<VBSingleBetElement, VBSingleBetGui> singleBetInterpreter() {
		return singleBetInterpreter;
	}

	public static VBElementInterpreter<VBBookmakerOddsElement, ? super VBBookmakerOddsGui> bookmakerOddsInterpreter() {
		return bookmakerOddsInterpreter;
	}

	public static VBElementInterpreter<VBBetInfoElement, VBBetInfoGui> betInfoInterpreter() {
		return betInfoInterpreter;
	}

	public static VBElementInterpreter<VBBrowsingStatusElement, VBBrowsingStatusGui> browsingStatusInterpreter() {
		return browsingStatusInterpreter;
	}

	public static VBElementInterpreter<VBBalanceElement, ? super VBBalanceGui> balanceInterpreter() {
		return balanceInterpreter;
	}

	public static VBElementInterpreter<VBBookmakerMinStakeElement, ? super VBBookmakerMinStakeGui> bookmakerMinStakeInterpreter() {
		return bookmakerMinStakeInterpreter;
	}

	public static VBElementInterpreter<VBBookmakerMaxStakeElement, ? super VBBookmakerMaxStakeGui> bookmakerMaxStakeInterpreter() {
		return bookmakerMaxStakeInterpreter;
	}
}
