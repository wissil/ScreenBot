package com.util.ai.screenbot.support.numbers;

public class CustomNumberFormat {
	
	public static double parseDouble(String s) {
		return Double.parseDouble(s.replace(',', '.'));
	}
}
