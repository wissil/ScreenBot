package com.util.ai.screenbot.support.platform;

public class PlatformResolver {

	public static Platform resolveCurrentPlatform() {
		if (isCurrentPlatformWin()) return Platform.WINDOWS;
		if (isCurrentPlatformMac()) return Platform.MAC;
		throw new IllegalStateException("Couldn't resolve current platform.");
	}
	
	private static boolean isCurrentPlatformMac() {
		return System.getProperty("os.name").trim().toLowerCase().startsWith("mac");
	}
	
	private static boolean isCurrentPlatformWin() {
		return System.getProperty("os.name").trim().toLowerCase().startsWith("windows");
	}
}
