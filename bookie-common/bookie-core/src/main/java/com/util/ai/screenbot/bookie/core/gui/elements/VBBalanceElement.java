package com.util.ai.screenbot.bookie.core.gui.elements;

public class VBBalanceElement implements VBScreenElement {
	
	private final double balance;
	
	public VBBalanceElement(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "VBBalanceElement [balance=" + balance + "]";
	}

}
