package com.util.ai.screenbot.bookie.core.gui.elements.parsing;

import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;

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
