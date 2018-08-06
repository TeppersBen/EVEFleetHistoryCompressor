package com.io;

import java.util.ArrayList;
import java.util.List;

import com.entities.LogLine;
import com.entities.Player;
import com.utils.PaymentCalculator;

public class EveLogReader {

	public static List<LogLine> convertLogToList(List<String> list) {
		List<LogLine> logList = new ArrayList<>();
		
		String[] lineSub;
		
		for (String line : list) {
			if (!line.startsWith("Time")) {
				lineSub = line.split("\\t");
				for (int i = 0; i < lineSub.length; i++) {
					lineSub[i] = lineSub[i].trim();
				}
				if (lineSub.length == 5)
					logList.add(new LogLine(lineSub[0], lineSub[1], lineSub[2], Long.parseLong(lineSub[3]), lineSub[4]));
			}
		}
		
		return logList;
	}
	
	public static List<Player> getPlayerStatsOutOfLogFile(List<LogLine> logFile) {
		return PaymentCalculator.getPayments(logFile);
	}
	
	public static List<LogLine> convertFileLocationslistToLogList(List<String> fileLocations) {
		List<LogLine> logs = new ArrayList<>();
		
		for (String file : fileLocations) {
			logs.addAll(convertLogToList(FileReader.getFileContent(file)));
		}
		
		return logs;
	}
}