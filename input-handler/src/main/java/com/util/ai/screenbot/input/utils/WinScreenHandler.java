package com.util.ai.screenbot.input.utils;

import java.awt.Rectangle;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.util.ai.screenbot.input.config.JNAConfig;
import com.util.ai.screenbot.input.exceptions.DeviceHandlerException;

public class WinScreenHandler extends ScreenHandler {

    @Override
    public Rectangle getRect() {
        HWND hwnd = JNAConfig.User32DLL.GetForegroundWindow();
        if (hwnd == null) {
            throw new DeviceHandlerException("Can not determine foreground window");
        }

        RECT rect = new RECT();
        boolean success = JNAConfig.User32DLL.GetWindowRect(hwnd, rect);
        if (!success) {
            throw new DeviceHandlerException("Can not determine foreground window frame");
        }
        return rect.toRectangle();
    }

    @Override
    public String getCurrentWindowName() {

        final int MAX_TITLE_LENGTH = 1024;
        char[] buffer = new char[MAX_TITLE_LENGTH * 2];

        JNAConfig.User32DLL.GetWindowTextW(JNAConfig.User32DLL.GetForegroundWindow(), buffer, MAX_TITLE_LENGTH);

        return Native.toString(buffer);
    }
}
