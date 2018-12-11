package com.util.ai.screenbot.output.constants;

import java.util.Objects;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreter;
import com.util.ai.screenbot.output.interpreters.VBOddsInputInterpreter;
import com.util.ai.screenbot.output.interpreters.VBPlaceBetInterpreter;
import com.util.ai.screenbot.output.interpreters.VBSingleBetInterpreter;

public enum VBElementType {

	VB_ODDS_INPUT(new VBOddsInputInterpreter()),
	
	VB_PLACE_BET(new VBPlaceBetInterpreter()),
	
	VB_SINGLE_BET(new VBSingleBetInterpreter());
	
	final VBElementInterpreter<? extends VBScreenElement> interpreter;
	
	VBElementType(VBElementInterpreter<? extends VBScreenElement> interpreter) {
		this.interpreter = Objects.requireNonNull(interpreter);
	}
	
	public VBElementInterpreter<? extends VBScreenElement> getInterpreter() {
		return interpreter;
	}
}
