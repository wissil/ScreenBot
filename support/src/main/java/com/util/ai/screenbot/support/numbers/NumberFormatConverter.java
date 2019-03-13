package com.util.ai.screenbot.support.numbers;

public class NumberFormatConverter {

	public static double convertFromFractionToDecimal(double numerator, double denominator) {
		return 1. + (numerator / denominator);
	}
}
