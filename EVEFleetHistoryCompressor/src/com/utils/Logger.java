package com.utils;

import java.util.ArrayList;
import java.util.List;

public class Logger {

	private static List<String> errList = new ArrayList<>();
	
	public static void log(Object o, String message) {
		System.out.println(o.getClass().getSimpleName() + ": " + message + "...");
	}
	
	public static void err(Object o, String message) {
		System.err.println(o.getClass().getSimpleName() + ": " + message + "...");
	}
	
	public static void addErr(Object o, String message) {
		String line = o.getClass().getSimpleName() + ": " + message + "...";
		if (!errList.contains(line))
			errList.add(line);
	}
	
	public static void printErr() {
		String result = "";
		for (String line : errList) {
			result += line + "\n";
		}
		System.err.println("\n############\nErrors: " + errList.size() + "\n############\n" + result);
	}
}