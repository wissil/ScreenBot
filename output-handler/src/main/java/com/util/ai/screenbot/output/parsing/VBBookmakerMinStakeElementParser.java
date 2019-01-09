package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBookmakerStakeMinElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBookmakerMinStakeElementParser implements VBScreenElementParser<VBBookmakerStakeMinElement> {

	@Override
	public VBBookmakerStakeMinElement parse(String input) throws ScreenElementParseException {
		try {
			final double stake = Double.parseDouble(input);
			return new VBBookmakerStakeMinElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
