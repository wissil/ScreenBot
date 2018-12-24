package com.util.ai.screenbot.main.automata.states;

public abstract class VBState {

	abstract void onEnter();
	
	abstract void onExit();
	
	abstract void execute();
	
	public void process() {
		onEnter();
		execute();
		onExit();
	}
}
