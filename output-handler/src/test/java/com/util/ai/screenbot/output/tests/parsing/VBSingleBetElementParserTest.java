package com.util.ai.screenbot.output.tests.parsing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class VBSingleBetElementParserTest extends OutputHandlerTestBase {

	private static final String input = 
			"6.7% 2 1hour 53min  Tsen Tou vs Neftyanik Almetievsk AH1(1) 2.22@Marathonbet";
	
	@Inject
	private VBSingleBetElementParser parser;
	
	@Test
	public void testItemElementParsing() throws ScreenElementParseException {
		final VBSingleBetElement element = parser.parse(input);
		
		assertEquals("6.7", element.getValue());
		assertEquals("2", element.getStake());
		assertEquals("Marathonbet", element.getBookie());
		assertEquals("2.22", element.getOdds());
	}
}
