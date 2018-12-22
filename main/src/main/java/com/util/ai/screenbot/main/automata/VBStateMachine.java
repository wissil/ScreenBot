package com.util.ai.screenbot.main.automata;

import java.util.Objects;

import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;

public abstract class VBStateMachine {
		
	private static enum VBState {
		INIT, IDLE, PARSE_BET, PLACE_BET, LOG_BET, CLEAN_BET
	}
	
//	INIT --> IDLE
//	IDLE --> (bet exists : true) --> PROCESS_BET
//	IDLE --> (bet exists: false) --> IDLE
//	PROCESS_BET --> (parse_element : success) --> PLACE_BET
//	PLACE_BET --> LOG_BET
//	PROCESS_BET --> (parse_element: fail) --> CLEAN_BET
//	CLEAN_BET --> IDLE
//	LOG_BET --> IDLE
	
    InputHandler in;
    
    OutputHandler out;

	private VBState currentState;
	
	public VBStateMachine(InputHandler in, OutputHandler out) {
		this.in = Objects.requireNonNull(in);
		this.out = Objects.requireNonNull(out);
		this.currentState = VBState.INIT;
	}
	
	public void run() throws InterruptedException {
		while (true) {
			switch (currentState) {
			case INIT: {
				init();
				goTo(VBState.IDLE);
			} break;
			
			case IDLE: {
				final boolean betExists = idle();
				if (betExists) goTo(VBState.PARSE_BET);
			} break;
			
			case PARSE_BET: {
				final boolean parseSuccess = parseBet();
				if (parseSuccess) goTo(VBState.PLACE_BET);
				else goTo(VBState.CLEAN_BET);
			} break;
			
			case PLACE_BET: {
				placeBet();
				goTo(VBState.LOG_BET);
			} break;
				
			case LOG_BET: {
				logBet();
				goTo(VBState.IDLE);
			} break;
			
			case CLEAN_BET: {
				cleanBet();
				goTo(VBState.IDLE);
			} break;

			default:
				break;
			}
		}
	}
	
	abstract void cleanBet() throws InterruptedException;

	abstract void logBet() throws InterruptedException;

	public abstract void placeBet() throws InterruptedException;

	abstract boolean parseBet() throws InterruptedException;

	abstract boolean idle() throws InterruptedException;

	abstract void init() throws InterruptedException;

	private void goTo(VBState newState) {
		this.currentState = newState;
	}
}
