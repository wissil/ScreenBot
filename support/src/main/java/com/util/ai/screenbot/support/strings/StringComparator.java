package com.util.ai.screenbot.support.strings;

import info.debatty.java.stringsimilarity.JaroWinkler;

public class StringComparator {
	
	private static final JaroWinkler jw = new JaroWinkler();
	
	private static final double TRESHOLD = 0.87;

	private StringComparator() {
		// shouldn't be possible to instantiate
	}
	

	/**
	 * Checks whether the two given strings <code>s1</code>
	 * and <code>s2</code> are considered equal.
	 * 
	 * @param s1
	 * @param s2
	 * @return <code>true</code> if the two strings are similar enough to be considered
	 * equal, and <code>false</code> otherwise.
	 */
	public static boolean consideredEqual(String s1, String s2) {
		return jw.similarity(s1, s2) > TRESHOLD;
	}
}
