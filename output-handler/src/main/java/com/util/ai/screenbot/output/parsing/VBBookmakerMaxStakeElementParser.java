package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBookmakerStakeMaxElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBookmakerMaxStakeElementParser implements VBScreenElementParser<VBBookmakerStakeMaxElement> {

	@Override
	public VBBookmakerStakeMaxElement parse(String input) throws ScreenElementParseException {
		try {
			final double stake = Double.parseDouble(input);
			return new VBBookmakerStakeMaxElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
