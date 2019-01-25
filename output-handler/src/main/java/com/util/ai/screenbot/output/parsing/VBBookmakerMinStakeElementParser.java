package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBookmakerMinStakeElementParser implements VBScreenElementParser<VBBookmakerMinStakeElement> {

	@Override
	public VBBookmakerMinStakeElement parse(String input) throws ScreenElementParseException {
		try {
			final double stake = CustomNumberFormat.parseDouble(input);
			return new VBBookmakerMinStakeElement(stake);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
