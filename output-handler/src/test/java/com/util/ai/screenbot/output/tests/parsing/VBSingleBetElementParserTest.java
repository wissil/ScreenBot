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

	@Test
	public void parseExampleThreeTest() throws ScreenElementParseException {
		final String s = "2.9% 15 3 hours Chile vs Brazil U1) iH 1.69 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}

	@Test
	public void parseExampleFourTest() throws ScreenElementParseException {
		final String s = "1.6% 7 3hours Zenit St. Petersburg vs Spartak Moscow U1) iH 2.08 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}

	@Test
	public void parseExampleFiveTest() throws ScreenElementParseException {
		final String s = "2.5% 15 1lhour 3min  Kabilye vs AS Ain Mlila U21 U(1.5) 1.70 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}

	@Test
	public void parseExampleSixTest() throws ScreenElementParseException {
		final String s = "2 . 5 % 15 1lhour 3min  Kabilye vs AS Ain Mlila U21 U(1.5) 1.70 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}

	@Test
	public void parseExampleSevenTest() throws ScreenElementParseException {
		final String s = "4.3% 2  3hoursemin Beijing Beikong Fly Dragons vs Beijing U(193.5) 1,93 @ Marathonbet |";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}

	@Test
	public void parseExampleEightTest() throws ScreenElementParseException {
		final String s = "3.5% 2 23 hours 5G Wattenscheid 09 vs Bonner SC Q(2.5) 1.94 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}

	@Test
	public void parseExampleNineTest() throws ScreenElementParseException {
		final String s = "3.5% 2 23 hours 5G Wattenscheid 09 vs Bonner SC aH(2.5) 1.94 @ Marathonbet";
		final VBSingleBetElement element = parser.parse(s);

		System.out.println(element);
	}
}
