package com.util.ai.screenbot.main.reports;

public class BetReport {
	
	private final String bookmaker;
	
	private final double value;
	
	private final double oddsLimit;
	
	private final double oddsActual;
	
	private final double stake;
	
	private final double balance;
	
	private final double max;
	
	private final double min;
	
	private final boolean shouldPlaceBet;
	
	public BetReport(String bookmaker, double value, double oddsLimit, 
			double oddsActual, double stake, double balance,
			double max, double min, boolean shouldPlaceBet) {
		this.bookmaker = bookmaker;
		this.value = value;
		this.oddsLimit = oddsLimit;
		this.oddsActual = oddsActual;
		this.stake = stake;
		this.balance = balance;
		this.max = max;
		this.min = min;
		this.shouldPlaceBet = shouldPlaceBet;
	}

	public String getBookmaker() {
		return bookmaker;
	}

	public double getValue() {
		return value;
	}

	public double getOddsLimit() {
		return oddsLimit;
	}

	public double getOddsActual() {
		return oddsActual;
	}

	public double getStake() {
		return stake;
	}

	public double getBalance() {
		return balance;
	}

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}

	public boolean isShouldPlaceBet() {
		return shouldPlaceBet;
	}


	@Override
	public String toString() {
		return BetReportGenerator.generateReport(bookmaker, value, 
				oddsLimit, oddsActual, stake, balance, 
				max, min, shouldPlaceBet);
	}
}
