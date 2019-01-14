package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBookmakerMaxStakeElementParser implements VBScreenElementParser<VBBookmakerMaxStakeElement> {

	@Override
	public VBBookmakerMaxStakeElement parse(String input) throws ScreenElementParseException {
		try {
			final double stake = Double.parseDouble(input);
			return new VBBookmakerMaxStakeElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
