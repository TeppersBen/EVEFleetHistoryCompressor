package com.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.Settings;
import com.io.FileWriter;

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
	
	public static void requestErrPrintExportToFile() {
		if (errList.size() == 0)
			return;
		int press = JOptionPane.showOptionDialog(
				null,
				"Would you like to print the error log?\n"
				+ "There are " + errList.size() + " errors at this time.\n"
				+ "Extract destination: " + Settings.ERR_LOG_LOCATION,
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new Object[] { "Yes", "No" },
				"Yes"
		);
		
		if (press == 0) {
			FileWriter.Write(Settings.ERR_LOG_LOCATION, errList);
		}
	}
}