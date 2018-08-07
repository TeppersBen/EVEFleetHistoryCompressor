package com.utils;

import java.text.DecimalFormat;

public class Formatter {

	/**
	 * Turns a double value into a readable format<br>
	 * i.e.:<br><code>
	 * 5172.01 >> 5,172.01<br>
	 * 19874.0 >> 19,874.00<br>
	 * 15.1578 >> 15.16
	 * </code>
	 * @param amount - double
	 * @return formatted String with decimals
	 */
	public static String convertToReadable(double amount) {
		return new DecimalFormat("###,###,###,##0.00").format(amount).toString();
	}

	/**
	 * Turns a long value into a readable format<br>
	 * i.e.:<br>
	 * <code>
	 * 16845212 >> 16,845,212<br>
	 * 1984 >> 1,984<br>
	 * 489541 >> 489,541
	 * </code>
	 * @param amount - long
	 * @return formatted String without decimals
	 */
	public static String convertToGroupedNumber(long amount) {
		return new DecimalFormat("###,###,###,###,###,##0").format(amount).toString();
	}
	
	/**
	 * Multiplies two strings into one readable formatted string
	 * @param a - String
	 * @param b - String
	 * @return formatted String with decimals
	 */
	public static String multiplyTwoStringsIntoReadable(String a, String b) {
		double value_a = Double.parseDouble(a.replaceAll(",", ""));
		double value_b = Double.parseDouble(b.replaceAll(",", ""));
		return convertToReadable(value_a * value_b);
	}
	
	/**
	 * Makes sure that the string always suits the string within the database file
	 * @param name - String
	 * @return String which matches those in the database file
	 */
	public static String turnLogLineStringIntoDatabaseString(String name) {
		String[] data = name.split("_");
		String result = "";
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].substring(0, 1).toUpperCase() + data[i].substring(1).toLowerCase();
			result += data[i] + ((i == data.length-1) ? "" : "_");
		}
		return result;
	}

	/**
	 * Turns a double value into a percentage format
	 * @param percentage - String
	 * @return percentage in string format
	 */
	public static String formatPercentage(double percentage) {
		return new DecimalFormat("#0.##").format(percentage).toString() + "%";
	}
}