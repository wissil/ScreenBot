package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBookmakerStakeElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBookmakerStakeElementParser implements VBScreenElementParser<VBBookmakerStakeElement> {

	@Override
	public VBBookmakerStakeElement parse(String input) throws ScreenElementParseException {
		try {
			final double stake = Double.parseDouble(input);
			return new VBBookmakerStakeElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
