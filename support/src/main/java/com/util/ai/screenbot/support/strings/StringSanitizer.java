package com.util.ai.screenbot.support.strings;

import java.util.Objects;

public class StringSanitizer {

	private final StringBuilder sb;

	private StringSanitizer(StringBuilder sb) {
		this.sb = Objects.requireNonNull(sb);
	}

	public static StringSanitizer forString(String s) {
		return new StringSanitizer(new StringBuilder(Objects.requireNonNull(s)));
	}

	public StringSanitizer fromAllWhitespaces() {
		checkString(sb);
		int j = 0;
		for(int i=0, n = sb.length(); i<n; i++) {
			if (!Character.isWhitespace(sb.charAt(i))) {
				sb.setCharAt(j++, sb.charAt(i));
			}
		}
		sb.delete(j, sb.length());
		return this;
	}

	public StringSanitizer fromAllNonNumeric() {
		checkString(sb);
		int j = 0;
		for(int i=0, n = sb.length(); i<n; i++) {
			final char c = sb.charAt(i);
			if (Character.isDigit(c) || isDecimalSeparator(c)) {
				sb.setCharAt(j++, sb.charAt(i));
			}
		}
		sb.delete(j, sb.length());
		
		// remove potential separators from the end of the string
		int i = sb.length();
		for (; i >= 0 && isDecimalSeparator(sb.charAt(i-1)); i--);
		sb.delete(i, sb.length());
		
		return this;
	}

	public String sanitize() {
		return sb.toString();
	}

	private static void checkString(StringBuilder sb) {
		if (sb == null) {
			throw new IllegalArgumentException("String is null. Method forString should be called first.");
		}
	}

	private static boolean isDecimalSeparator(char c) {
		return c == '.' || c == ',';
	}
}
