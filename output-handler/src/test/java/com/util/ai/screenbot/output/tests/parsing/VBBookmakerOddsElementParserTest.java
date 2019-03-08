package com.util.ai.screenbot.output.tests.parsing;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import com.util.ai.screenbot.output.parsing.VBBookmakerOddsElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class VBBookmakerOddsElementParserTest extends OutputHandlerTestBase {
	
	private static final double EPS = 10E-5;

	@Inject
	private VBBookmakerOddsElementParser parser;
	
	@Test
	public void parseDecimalTest() throws ScreenElementParseException {
		final String s1 = "1,345";
		final String s2 = "1.345";
		final String s3 = " 1, 34  5 ";
		
		final double expected = 1.345;
		
		assertEquals(expected, parser.parse(s1).getOdds(), EPS);
		assertEquals(expected, parser.parse(s2).getOdds(), EPS);
		assertEquals(expected, parser.parse(s3).getOdds(), EPS);
	}
	
	@Test
	public void parseFractionTest() throws ScreenElementParseException {
		final String s1 = "17/20";
		final String s2 = " 17  /  20  ";
		
		final double expected = 1.85;
		
		assertEquals(expected, parser.parse(s1).getOdds(), EPS);
		assertEquals(expected, parser.parse(s2).getOdds(), EPS);
	}
}
