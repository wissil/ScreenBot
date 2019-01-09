package com.util.ai.screenbot.support.numbers;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CustomNumberFormat {
	
	private static final NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);

	public static double parseDouble(String s) throws ParseException {
		final Number number = format.parse(s);
		return number.doubleValue();
	}
}
