package com.util.ai.screenbot.main.automata;

import com.util.ai.screenbot.input.exceptions.FatalVBException;

public interface VBStateMachine {

	void run() throws InterruptedException, FatalVBException;
}
