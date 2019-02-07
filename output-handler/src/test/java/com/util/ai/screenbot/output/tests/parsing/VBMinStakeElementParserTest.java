package com.util.ai.screenbot.output.tests.parsing;


import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.parsing.VBBookmakerMinStakeElementParser;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;

public class VBMinStakeElementParserTest extends OutputHandlerTestBase {

	@Inject
	private VBBookmakerMinStakeElementParser parser;
	
	
	@Test
	public void parseExampleOneTest() throws ScreenElementParseException {
		final String s = ") 0.20";
		final VBBookmakerMinStakeElement element = parser.parse(s);
		
		System.out.println(element);
	}
	
	@Test
	public void parseExampleTwoTest() throws ScreenElementParseException {
		final String s = " 0. 2 0";
		final VBBookmakerMinStakeElement element = parser.parse(s);
		
		System.out.println(element);
	}
}
