package com.util.ai.screenbot.output.tests.parsing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.parsing.VBBetInfoElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class VBOddsInputElementParserTest extends OutputHandlerTestBase {

	private static final String input = 
			"2.03\n" + 
			"2\n" + 
			"4.78%";
	
	@Inject
	private VBBetInfoElementParser parser;
	
	@Test
	public void testOddsInputElementParsing() throws ScreenElementParseException {
		final VBBetInfoElement element = parser.parse(input);
		
		assertEquals("4.78", element.getValue());
		assertEquals("2", element.getStake());
		assertEquals("2.03", element.getOdds());
	}
}
