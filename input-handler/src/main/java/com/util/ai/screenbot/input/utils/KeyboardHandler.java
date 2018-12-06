package com.util.ai.screenbot.input.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import com.util.ai.screenbot.input.constants.InputHandlerConstants;

/**
 * @author mcop
 * 
 *         Used for keyboard actions
 */
public class KeyboardHandler extends AbstractDeviceHandler {

    public void write(String text) {
        // General idea is to set target text to clipboard and then paste it to target location instead of typing every letter
        // on the keyboard
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        paste();
    }

    public void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void pressEscape() {
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public void pressDelete() {
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_DELETE);
    }

    public void pressBackspace() {
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    public void pressTab() {
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    public void copy() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void paste() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(InputHandlerConstants.ACTION_DELAY);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}
