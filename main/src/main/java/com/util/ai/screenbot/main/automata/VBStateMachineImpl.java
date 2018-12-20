package com.util.ai.screenbot.main.automata;

import com.util.ai.screenbot.main.automata.states.CleanBetState;
import com.util.ai.screenbot.main.automata.states.IdleState;
import com.util.ai.screenbot.main.automata.states.InitState;
import com.util.ai.screenbot.main.automata.states.LogBetState;
import com.util.ai.screenbot.main.automata.states.ParseBetState;
import com.util.ai.screenbot.main.automata.states.PlaceBetState;

public class VBStateMachineImpl extends VBStateMachine {

	@Override
	public void cleanBet() throws InterruptedException {
		new CleanBetState().process();
	}

	@Override
	public void logBet() throws InterruptedException {
		new LogBetState().process();
	}

	@Override
	public void placeBet() throws InterruptedException {
		new PlaceBetState().process();
	}

	@Override
	public boolean parseBet() throws InterruptedException {
		new ParseBetState().process();
		return false;
	}

	@Override
	public boolean idle() throws InterruptedException {
		new IdleState().process();
		return false;
	}

	@Override
	public void init() throws InterruptedException {
		new InitState().process();
	}

}
