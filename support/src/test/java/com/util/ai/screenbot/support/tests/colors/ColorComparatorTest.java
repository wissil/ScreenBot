package com.util.ai.screenbot.support.tests.colors;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import com.util.ai.screenbot.support.colors.ColorComparator;

public class ColorComparatorTest {

	private static final Color MARATHON_LIGHT_GREEN = new Color(232, 255, 230);

	private static final Color MARATHON_GREEN = new Color(0, 143, 76);

	private static final Color MARATHON_WHITE = new Color(255, 255, 255);
	
	@Test
	public void colorComparisonTest() {
		final Color otherLightGreen = new Color(237, 252, 237);
		final Color otherGreen = new Color(6, 140, 73);
		final Color otherWhite = new Color(250, 251, 249);
		
		final double precision = 0.1;
		
		assertTrue(ColorComparator.areEqualColors(MARATHON_LIGHT_GREEN, otherLightGreen, precision));
		assertTrue(ColorComparator.areEqualColors(MARATHON_GREEN, otherGreen, precision));
		assertTrue(ColorComparator.areEqualColors(MARATHON_WHITE, otherWhite, precision));
	}
}
