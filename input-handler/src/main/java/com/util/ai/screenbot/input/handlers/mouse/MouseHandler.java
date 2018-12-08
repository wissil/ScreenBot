package com.util.ai.screenbot.input.handlers.mouse;

import java.awt.event.InputEvent;

import com.util.ai.screenbot.input.constants.InputHandlerConstants;
import com.util.ai.screenbot.input.handlers.AbstractDeviceHandler;

/**
 * @author mcop
 * 
 *         Used for mouse movement and mouse clicks
 */
public class MouseHandler extends AbstractDeviceHandler {

    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
    }

    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
    }

    /**
     * Moves mouse cursor on the screen
     * 
     * @param x
     * @param y
     */
    public void moveMouse(Integer x, Integer y) {
        robot.mouseMove(x, y);
    }

}
