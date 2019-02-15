package com.util.ai.screenbot.main.reports;

public class BetReportGenerator {

	private static final String REPORT_HEADER = "================================ BET REPORT =================================";

	public static String generateReport(String bookmaker, double value, double oddsLimit, double oddsActual,
			double stake, double balance, double max, double min, boolean shouldPlaceBet) {
		final int len = REPORT_HEADER.length();
		final StringBuilder sb = new StringBuilder();

		sb.append(System.lineSeparator());
		sb.append(REPORT_HEADER);
		sb.append(System.lineSeparator());
		sb.append(generateSingleParameter(sb, "Bookmaker", bookmaker, len));
		sb.append(generateEmptyLine(sb, len));
		sb.append(generateSingleParameter(sb, "Minimum odds required", String.valueOf(oddsLimit), len));
		sb.append(generateSingleParameter(sb, "Actual bookmaker odds", String.valueOf(oddsActual), len));
		sb.append(generateEmptyLine(sb, len));
		sb.append(generateSingleParameter(sb, "Recommended stake", String.valueOf(stake), len));
		sb.append(generateSingleParameter(sb, "Bookmaker min stake", String.valueOf(min), len));
		sb.append(generateSingleParameter(sb, "Bookmaker max stake", String.valueOf(max), len));
		sb.append(generateEmptyLine(sb, len));
		sb.append(generateSingleParameter(sb, "Bookmaker current balance", String.valueOf(balance), len));
		sb.append(generateEmptyLine(sb, len));
		sb.append(generateSingleParameter(sb, "Value", String.valueOf(value), len));
		sb.append(generateSingleParameter(sb, "Should place bet", String.valueOf(shouldPlaceBet), len));
		sb.append(generateFooter(sb, len));

		return sb.toString();
	}

	private static StringBuilder generateSingleParameter(StringBuilder sb, String paramKey, String paramValue,
			int len) {
		sb = new StringBuilder();
		sb.append("| ");
		sb.append(paramKey);
		sb.append(": ");
		sb.append(paramValue);

		for (int i = sb.length(); i < len - 1; i++)
			sb.append(" ");
		sb.append("|");
		sb.append(System.lineSeparator());

		return sb;
	}

	private static StringBuilder generateEmptyLine(StringBuilder sb, int len) {
		sb = new StringBuilder();
		sb.append("| ");

		for (int i = sb.length(); i < len - 1; i++)
			sb.append(" ");
		sb.append("|");
		sb.append(System.lineSeparator());

		return sb;
	}

	private static StringBuilder generateFooter(StringBuilder sb, int len) {
		sb = new StringBuilder();

		for (int i = sb.length(); i < len; i++)
			sb.append("=");
		sb.append(System.lineSeparator());

		return sb;
	}

}
