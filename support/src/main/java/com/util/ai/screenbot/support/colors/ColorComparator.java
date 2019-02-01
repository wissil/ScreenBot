package com.util.ai.screenbot.support.colors;

import java.awt.Color;

public class ColorComparator {
	
	private static final int MAX_VALUE = 255 / 2;
	
	private static final double PRECISION_TRESHOLD = 10E-5;
	
	public static boolean areEqualColors(Color c1, Color c2, double precision) {
		final int valueTresh = (int) Math.round(precision * MAX_VALUE);
		
		return 
				areEqualPixels(c1.getRed(), c2.getRed(), precision, valueTresh) &&
				areEqualPixels(c1.getGreen(), c2.getGreen(), precision, valueTresh) &&
				areEqualPixels(c1.getBlue(), c2.getBlue(), precision, valueTresh);
	}
	
	private static boolean areEqualPixels(int p1, int p2, double precision, int valueTresh) {
		if (p1 < valueTresh) p1 = valueTresh;
		if (p2 < valueTresh) p2 = valueTresh;
		
		final double minBound = (double) p1 - (double) p1 * precision;
		final double maxBound = (double) p1 + (double) p1 * precision;
			
		return areExact((double) p1, (double) p2) || (isGreaterThan((double) p2, minBound)) && isLowerThan((double) p2, maxBound);
	}
	
	private static boolean isLowerThan(double d1, double d2) {
		return (d1 - d2) < PRECISION_TRESHOLD;
	}
	
	private static boolean isGreaterThan(double d1, double d2) {
		return (d1 - d2) > PRECISION_TRESHOLD;
	}
	
	private static boolean areExact(double d1, double d2) {
		return Math.abs(d1 - d2) < PRECISION_TRESHOLD;
	}
}
