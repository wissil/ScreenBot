package com.util.ai.screenbot.input.handlers;

import java.awt.Rectangle;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.input.config.InputHandlerTestBase;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.model.ActiveWindow;
import com.util.ai.screenbot.support.platform.Platform;
import com.util.ai.screenbot.support.testing.platform.IgnoreOnPlatform;

public class ScreenHandlerTest extends InputHandlerTestBase {

	@Inject
	private ScreenHandler screenHandler;

	@Test
	@IgnoreOnPlatform(value = Platform.MAC)
	public void testGetActiveWindow_Windows() throws Exception {
		final ActiveWindow activeWindow = screenHandler.getActiveWindow();
		
		final String name = activeWindow.getName();
		final Rectangle bounds = activeWindow.getBounds();

		Assert.assertNotNull(name);
		Assert.assertTrue(name.startsWith("screenBot"));
		System.out.println("Window name: " + name);
		
		Assert.assertNotNull(bounds);
		System.out.println("Window bounds: " + bounds);
	}

	@Test
	@Ignore
	@IgnoreOnPlatform(value = Platform.WINDOWS)
	public void testGetActiveWindow_Mac() throws Exception {		
		final ActiveWindow activeWindow = screenHandler.getActiveWindow();
		
		final String name = activeWindow.getName();
		final Rectangle bounds = activeWindow.getBounds();

		Assert.assertNotNull(name);
		Assert.assertTrue(name.startsWith("eclipse"));
		System.out.println("Window name: " + name);
		
		Assert.assertNotNull(bounds);
		System.out.println("Window bounds: " + bounds);
	}

}
