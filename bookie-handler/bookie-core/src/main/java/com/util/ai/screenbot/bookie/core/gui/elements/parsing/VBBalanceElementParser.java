package com.util.ai.screenbot.bookie.core.gui.elements.parsing;

import com.util.ai.screenbot.bookie.core.gui.elements.VBBalanceElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBalanceElementParser implements VBScreenElementParser<VBBalanceElement> {

	@Override
	public VBBalanceElement parse(String input) throws ScreenElementParseException {
		try {
			final double balance = CustomNumberFormat.parseDouble(input);
			return new VBBalanceElement(balance);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input: %s to Double.", input));
		}
	}

}
