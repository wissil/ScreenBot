package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBPlaceBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBPlaceBetElementParser 
	implements VBScreenElementParser<VBPlaceBetElement> {

	@Override
	public VBPlaceBetElement parse(String input) throws ScreenElementParseException {
		// input string should only contain a float number
		// no additional text
		final String odds = input.trim();
		
		return new VBPlaceBetElement(odds);
	}

}
