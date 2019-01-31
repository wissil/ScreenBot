package com.util.ai.screenbot.support.tests.numbers;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.util.ai.screenbot.support.numbers.CustomNumberFormat;
import com.util.ai.screenbot.support.tests.config.SupportTestBase;

public class CustomNumberFormatTest extends SupportTestBase {
	
	private static double PRECISION = 10e-5;
	
	@Test
	public void numberFormatTest() {		
		final String s1 = ". 0.20";
		final String s2 = "0.20";
		final String s3 = "0,20";
		final String s4 = " , 0.2";
		
		final double expected = 0.2;
		
		assertEquals(expected, CustomNumberFormat.parseDouble(s1), PRECISION);
		assertEquals(expected, CustomNumberFormat.parseDouble(s2), PRECISION);
		assertEquals(expected, CustomNumberFormat.parseDouble(s3), PRECISION);
		assertEquals(expected, CustomNumberFormat.parseDouble(s4), PRECISION);
	}
}
