package com.util.ai.screenbot.bookie.core.gui.elements.parsing;

import com.util.ai.screenbot.bookie.core.gui.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.strings.StringComparator;

public class VBBrowsingStatusElementParser implements VBScreenElementParser<VBBrowsingStatusElement> {
	
	private static final String DONE = "Done";

	@Override
	public VBBrowsingStatusElement parse(String input) throws ScreenElementParseException {
		final boolean isDone = StringComparator.consideredEqual(input, DONE);
		return new VBBrowsingStatusElement(isDone);
	}

}
