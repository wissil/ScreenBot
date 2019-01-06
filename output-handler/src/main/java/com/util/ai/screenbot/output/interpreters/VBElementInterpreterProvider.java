package com.util.ai.screenbot.output.interpreters;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;

public class VBElementInterpreterProvider {

	@Inject
	private static VBElementInterpreter<VBSingleBetElement> singleBetInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBookmakerOddsElement> bookmakerOddsInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBetInfoElement> betInfoInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBrowsingStatusElement> browsingStatusInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBalanceElement> balanceInterpreter;
	
	@Inject
	private static VBElementInterpreter<VBBookmakerStakeElement> bookmakerStakeInterpreter;
	

	public static VBElementInterpreter<VBSingleBetElement> singleBetInterpreter() {
		return singleBetInterpreter;
	}

	public static VBElementInterpreter<VBBookmakerOddsElement> bookmakerOddsInterpreter() {
		return bookmakerOddsInterpreter;
	}

	public static VBElementInterpreter<VBBetInfoElement> betInfoInterpreter() {
		return betInfoInterpreter;
	}
	
	public static VBElementInterpreter<VBBrowsingStatusElement> browsingStatusInterpreter() {
		return browsingStatusInterpreter;
	}
	
	public static VBElementInterpreter<VBBalanceElement> balanceInterpreter() {
		return balanceInterpreter;
	}
	
	public static VBElementInterpreter<VBBookmakerStakeElement> bookmakerStakeInterpreter() {
		return bookmakerStakeInterpreter;
	}
}
