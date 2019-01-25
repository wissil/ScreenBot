package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBetInfoOddsElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBetInfoOddsElementParser implements VBScreenElementParser<VBBetInfoOddsElement> {

	@Override
	public VBBetInfoOddsElement parse(String input) throws ScreenElementParseException {
		try {
			input = input.trim();
			final double odds = CustomNumberFormat.parseDouble(input);
			return new VBBetInfoOddsElement(odds);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Input %s couldn't be parsed into element.", input));
		}
	}
}
