package com.util.ai.screenbot.bookie.core.parsing;


import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.VBBookmakerMinStakeElementParser;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.bookie.core.test.config.BookieCoreTestBase;

public class VBMinStakeElementParserTest extends BookieCoreTestBase {

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
