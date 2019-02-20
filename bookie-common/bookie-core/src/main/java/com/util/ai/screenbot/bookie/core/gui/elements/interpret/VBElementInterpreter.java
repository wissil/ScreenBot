package com.util.ai.screenbot.bookie.core.gui.elements.interpret;

import com.util.ai.screenbot.bookie.core.gui.VBGuiElement;
import com.util.ai.screenbot.bookie.core.gui.elements.VBScreenElement;
import com.util.ai.screenbot.bookie.core.gui.elements.interpret.exceptions.VBElementInterpretationException;

public interface VBElementInterpreter<E extends VBScreenElement, G extends VBGuiElement> {

	E interpret(G gui) throws VBElementInterpretationException;
}
