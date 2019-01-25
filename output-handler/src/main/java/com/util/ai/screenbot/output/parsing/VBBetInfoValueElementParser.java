package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBetInfoValueElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBetInfoValueElementParser implements VBScreenElementParser<VBBetInfoValueElement> {

	@Override
	public VBBetInfoValueElement parse(String input) throws ScreenElementParseException {
		try {
			// remove the '%' if present
			input = input.trim();
			input = input.contains("%") ? input.substring(0, input.length()-1) : input;
			final double value = CustomNumberFormat.parseDouble(input);
			return new VBBetInfoValueElement(value);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Input %s couldn't be parsed into element.", input));
		}
	}
}
