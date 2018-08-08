package com.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.Settings;
import com.entities.DataSet;
import com.io.EveLogReader;
import com.io.FileReader;
import com.utils.Logger;

public class ContainerHandler {

	private static Map<String, DataSet> dataSets = new TreeMap<>();

	public static void init() {
		DataSet dataSet;
		String fileName;
		String[] pathParts;

		List<String> unpaid = FileReader.getAllUnfinishedMiningFleetPayments();
		for (String line : unpaid) {
			dataSet = new DataSet();
			dataSet.setPaid(false);
			dataSet.setLootList(EveLogReader.convertLogToList(FileReader.getFileContent(line)));
			dataSet.generatePlayerStats();
			pathParts = line.replaceAll("/", "\\\\").split("\\\\");
			fileName = pathParts[pathParts.length - 1].replaceAll(".txt", "");
			dataSet.setFileName(fileName);
			dataSet.setFilePath(line);
			dataSets.put(fileName, dataSet);
			Logger.log(ContainerHandler.class, fileName + " with " + dataSet + " has been added to the container");
		}

		List<String> paid = FileReader.getAllFinishedMiningFleetPayments();
		for (String line : paid) {
			dataSet = new DataSet();
			dataSet.setPaid(true);
			dataSet.setLootList(EveLogReader.convertLogToList(FileReader.getFileContent(line)));
			dataSet.generatePlayerStats();
			pathParts = line.replaceAll("/", "\\\\").split("\\\\");
			fileName = pathParts[pathParts.length - 1].replaceAll(".txt", "");
			dataSet.setFileName(fileName);
			dataSet.setFilePath(line);
			dataSets.put(fileName, dataSet);
			Logger.log(ContainerHandler.class, fileName + " with " + dataSet + " has been added to the container");
		}

		createMassiveDataSet();
	}

	private static void createMassiveDataSet() {
		DataSet dataSet = new DataSet(ListHandler.compressMassiveLogList(
				EveLogReader.convertFileLocationslistToLogList(FileReader.getAllEVELogFiles())));
		dataSet.setFileName("");
		dataSet.setPaid(true);
		dataSets.put("Overall", dataSet);
	}

	public static void updateMassiveDataSet() {
		DataSet dataSet = new DataSet(ListHandler.compressMassiveLogList(
				EveLogReader.convertFileLocationslistToLogList(FileReader.getAllEVELogFiles())));
		
		dataSet.setFileName("");
		dataSet.setPaid(true);
		
		if (dataSet.getLootList().size() == dataSets.get("Overall").getLootList().size())
			return;
		
		Settings.trigger_overall_view = true;
		
		modify("Overall", dataSet);
	}

	public static void remove(Object fileName) {
		dataSets.remove(fileName);
		Logger.log(ContainerHandler.class, fileName + " has been removed from the container");
	}

	public static void put(String fileName, DataSet dataSet) {
		dataSets.put(fileName, dataSet);
		Logger.log(ContainerHandler.class, fileName + " with " + dataSet + " has been added to the container");
	}

	public static DataSet get(Object fileName) {
		Logger.log(ContainerHandler.class, fileName + " has been requested.");
		return dataSets.get(fileName);
	}

	public static void modify(String key, DataSet dataset) {
		dataSets.replace(key, dataset);
		Logger.log(ContainerHandler.class, key + " was modified, new object[]=" + dataset);
	}

	public static List<DataSet> getUnpaidDataSets() {
		List<DataSet> data = new ArrayList<>();
		for (DataSet el : dataSets.values()) {
			if (!el.isPaid() && !el.getFileName().equalsIgnoreCase(""))
				data.add(el);
		}
		return data;
	}

	public static List<DataSet> getPaidDataSets() {
		List<DataSet> data = new ArrayList<>();
		for (DataSet el : dataSets.values()) {
			if (el.isPaid() && !el.getFileName().equalsIgnoreCase(""))
				data.add(el);
		}
		return data;
	}
}