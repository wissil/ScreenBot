package com.util.ai.screenbot.main.automata;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;

public interface VBStateMachine {

	void run() throws InterruptedException, FatalValueBettingException;
}
