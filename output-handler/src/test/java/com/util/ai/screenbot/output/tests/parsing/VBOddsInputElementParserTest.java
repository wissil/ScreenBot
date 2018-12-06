package com.util.ai.screenbot.output.tests.parsing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.parsing.VBOddsInputElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class VBOddsInputElementParserTest extends OutputHandlerTestBase {

	private static final String input = 
			"2.03\n" + 
			"2\n" + 
			"4.78%";
	
	@Inject
	private VBOddsInputElementParser parser;
	
	@Test
	public void testOddsInputElementParsing() throws ScreenElementParseException {
		final VBOddsInputElement element = parser.parse(input);
		
		assertEquals("4.78", element.getValue());
		assertEquals("2", element.getStake());
		assertEquals("2.03", element.getOdds());
	}
}
