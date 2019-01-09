package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBetInfoStakeElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBetInfoStakeElementParser implements VBScreenElementParser<VBBetInfoStakeElement> {

	@Override
	public VBBetInfoStakeElement parse(String input) throws ScreenElementParseException {
		try {
			input = input.trim();
			final double stake = Double.parseDouble(input);
			return new VBBetInfoStakeElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Input %s couldn't be parsed into element.", input));
		}
	}
}
