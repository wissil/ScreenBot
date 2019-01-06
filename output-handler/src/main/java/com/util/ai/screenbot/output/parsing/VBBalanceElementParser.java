package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBalanceElementParser implements VBScreenElementParser<VBBalanceElement> {

	@Override
	public VBBalanceElement parse(String input) throws ScreenElementParseException {
		try {
			final double balance = Double.parseDouble(input);
			return new VBBalanceElement(balance);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
