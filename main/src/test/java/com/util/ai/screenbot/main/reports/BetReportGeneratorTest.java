package com.util.ai.screenbot.main.reports;

import org.junit.Test;

public class BetReportGeneratorTest {
	
//	String bookmaker,
//	double value, double oddsLimit, double oddsActual,
//	double stake, double balance, double max, double min,
//	boolean shouldPlaceBet

	@Test
	public void testRandomReport() {
		final String report = BetReportGenerator.generateReport("Marathonbet", 0, 0, 0, 0, 0, 0, 0, false);
		System.out.println(report);
	}
}
