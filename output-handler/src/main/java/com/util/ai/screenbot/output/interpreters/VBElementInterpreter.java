package com.util.ai.screenbot.output.interpreters;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.elements.gui.VBGuiElement;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public interface VBElementInterpreter<E extends VBScreenElement, G extends VBGuiElement> {

	E interpret(G gui) throws VBElementInterpretationException;
}
