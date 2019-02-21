package com.util.ai.screenbot.support.tests.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.util.ai.screenbot.support.strings.StringSanitizer;

public class StringSanitizerTest {

	@Test
	public void testWhitespaces() {
		final String s1 = "1.73 4";
		
		final String expected = "1.734";
		final String actual = StringSanitizer.forString(s1)
				.fromAllWhitespaces()
				.sanitize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonDigit() {
		final String s1 = "1.73 4  AB1*";
		
		final String expected = "1.7341";
		final String actual = StringSanitizer.forString(s1)
				.fromAllWhitespaces()
				.fromAllNonNumeric()
				.sanitize();
		
		assertEquals(expected, actual);
	}
}
