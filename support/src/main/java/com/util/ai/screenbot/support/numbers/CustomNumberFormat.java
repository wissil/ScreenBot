package com.util.ai.screenbot.support.numbers;

public class CustomNumberFormat {
	
	public static double parseDouble(String s) {
		int startIdx = 0;
		for (;!Character.isDigit(s.charAt(startIdx)); startIdx ++);
		
		return Double.parseDouble(s.substring(startIdx).replace(',', '.').replaceAll("\\s",""));
	}
}
