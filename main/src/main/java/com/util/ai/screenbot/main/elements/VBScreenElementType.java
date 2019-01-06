package com.util.ai.screenbot.main.elements;

import java.util.Objects;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreter;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.betInfoInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.bookmakerOddsInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.singleBetInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.browsingStatusInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.balanceInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.bookmakerStakeInterpreter;


public enum VBScreenElementType {
	
	BOOKMAKER_STAKE(bookmakerStakeInterpreter()),
	
	BROWSING_STATUS(browsingStatusInterpreter()),
	
	BALANCE(balanceInterpreter()),

	SINGLE_BET(singleBetInterpreter()),
	
	BOOKMAKER_ODDS(bookmakerOddsInterpreter()),
	
	BET_INFO(betInfoInterpreter());
	
	final VBElementInterpreter<? extends VBScreenElement> interpreter;
	
	private VBScreenElementType(VBElementInterpreter<? extends VBScreenElement> interpreter) {
		this.interpreter = Objects.requireNonNull(interpreter);
	}
	
	public VBElementInterpreter<? extends VBScreenElement> getInterpreter() {
		return interpreter;
	}
}
