package com.util.ai.screenbot.support.numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomNumberFormat {
	
	private static final String ANY_WHITESPACE_REGEX = "\\s+";
	
	private static final String EMPTY_STRING = "";
	
	private static final char DOT = '.';
	
	private static final char COMMA = ',';
	
	private static final Set<Character> SEPARATORS = new HashSet<>(Arrays.asList(DOT, COMMA));

	public static double parseDouble(String s) {
		// replace all commas with the dots
		// remove all white-spaces
		s = s.replace(COMMA, DOT).replaceAll(ANY_WHITESPACE_REGEX, EMPTY_STRING);
		
		// ignore all whitespace occurrences before the number starts
		int startIdx = -1;
		while (isSeparator(s.charAt(++startIdx)));
		s = s.substring(startIdx);
		
		// get positions of separators
		final Map<Integer, Character> separatorPositions = new HashMap<>();		
		for (int i=startIdx, n=s.length(); i<n; i++) {
			final char c = s.charAt(i);
			if (isSeparator(c)) separatorPositions.put(i, c);
		}
		
		// if there are exactly two, number represents a number greater than 1,000
		// remove the first dot (previously replaced the comma)		
		if (separatorPositions.size() == 2) {
			s = s.replaceFirst("\\.", EMPTY_STRING);
		}
				
		// if there were more than 2 separators left, parseDouble will throw
		return Double.parseDouble(s);
	}
	
	private static boolean isSeparator(char c) {
		return SEPARATORS.contains(c);
	}
}
