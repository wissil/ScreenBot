package com.util.ai.screenbot.output.tests.parsing;


import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class VBSingleBetElementParserTest extends OutputHandlerTestBase {

	@Inject
	private VBSingleBetElementParser parser;
	
	
	@Test
	public void parseExampleOneTest() throws ScreenElementParseException {
		final String s = "4,3% 15 42min Izhstal Izhevsk vs Sokol Krasnoyarsk (4.5) 2.27 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);
		
		System.out.println(element);
	}
	
	@Test
	public void parseExampleTwoTest() throws ScreenElementParseException {
		final String s = "1.5% 8 42min Izhstal Izhevsk vs Sokol Krasnoyarsk (5) 1.84 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);
		
		System.out.println(element);
	}
}
