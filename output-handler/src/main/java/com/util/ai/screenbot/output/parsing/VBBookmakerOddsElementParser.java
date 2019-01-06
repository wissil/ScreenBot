package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBookmakerOddsElementParser 
	implements VBScreenElementParser<VBBookmakerOddsElement> {

	@Override
	public VBBookmakerOddsElement parse(String input) throws ScreenElementParseException {
		// input string should only contain a float number
		// no additional text
		final String odds = input.trim();
		
		return new VBBookmakerOddsElement(odds);
	}

}
