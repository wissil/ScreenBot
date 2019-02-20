package com.util.ai.screenbot.bookie.core.gui.elements.parsing;


import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBetInfoStakeElementParser implements VBScreenElementParser<VBBetInfoStakeElement> {

	@Override
	public VBBetInfoStakeElement parse(String input) throws ScreenElementParseException {
		try {
			input = input.trim();
			final double stake = CustomNumberFormat.parseDouble(input);
			return new VBBetInfoStakeElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Input %s couldn't be parsed into element.", input));
		}
	}
}
