package com.util.ai.screenbot.output.parsing;

import java.util.Arrays;

import com.util.ai.screenbot.output.elements.VBOddsInputElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBOddsInputElementParser 
	implements VBScreenElementParser<VBOddsInputElement> {
	
	private static final int NUM_ELEMENTS = 3;

	@Override
	public VBOddsInputElement parse(String input) throws ScreenElementParseException {
		final String[] elements = input.split(System.lineSeparator());
		
		if (elements.length != NUM_ELEMENTS) {
			throw new ScreenElementParseException(
					String.format("Illegal number of elements: %d in %s. Expected: %d.", 
							elements.length, Arrays.toString(elements), NUM_ELEMENTS));
		}
		
		final String odds = elements[0];
		final String stake = elements[1];
		String value = elements[2];
		
		if (!value.contains("%")) {
			throw new ScreenElementParseException(
					String.format("Value element %s didn't contain percentage symbol [%].", value));
		}
		
		// remove the % symbol
		value = value.substring(0, value.length()-1);
		
		return new VBOddsInputElement(odds, stake, value);
	}

}
