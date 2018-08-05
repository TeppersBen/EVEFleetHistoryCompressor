package com.utils;

import java.text.DecimalFormat;

public class Formatter {

	public static String convertToReadable(double amount) {
		return new DecimalFormat("###,###,###,##0.00").format(amount).toString();
	}

	public static String convertToGroupedNumber(long parseLong) {
		return new DecimalFormat("###,###,###,###,###,##0").format(parseLong).toString();
	}
	
	public static String multiplyTwoStringsIntoReadable(String a, String b) {
		double value_a = Double.parseDouble(a.replaceAll(",", ""));
		double value_b = Double.parseDouble(b.replaceAll(",", ""));
		return convertToReadable(value_a * value_b);
	}
	
	public static String turnLogLineStringIntoDatabaseString(String name) {
		String[] data = name.split("_");
		String result = "";
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].substring(0, 1).toUpperCase() + data[i].substring(1).toLowerCase();
			result += data[i] + ((i == data.length-1) ? "" : "_");
		}
		return result;
	}

	public static String formatPercentage(double percentage) {
		return new DecimalFormat("#0.##").format(percentage).toString() + "%";
	}
}