package com.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.Settings;
import com.entities.DataSet;
import com.handlers.ContainerHandler;
import com.utils.Logger;

public class FileReader {
	
	public static List<String> getFileContent(String path) {
		List<String> list = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new java.io.FileReader(new File(path)))) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			
			reader.close();
			return list;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static List<String> getAllUnfinishedMiningFleetPayments() {
		return scanFilesAtLocation(Settings.unfinishedFleetPaymentsPath);
	}
	
	public static List<String> getAllFinishedMiningFleetPayments() {
		return scanFilesAtLocation(Settings.finishedFleetPaymentsPath);
	}
	
	private static List<String> scanFilesAtLocation(String path) {
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
		    return paths
		        .filter(Files::isRegularFile)
		        .map(e -> e.toString())
		        .filter(e -> e.contains("Loot - "))
		        .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void transferFleetLogToPaidLocation(String fileName) {
		File source = new File(Settings.unfinishedFleetPaymentsPath + File.separator + fileName + ".txt");
		File target = new File(Settings.finishedFleetPaymentsPath + File.separator + fileName + ".txt");
		try {
		    Files.move(source.toPath(), target.toPath(), StandardCopyOption.ATOMIC_MOVE);
		    Logger.log(FileReader.class, "Moved " + source.toPath() + " to " + target.toPath());
		    DataSet data = ContainerHandler.get(fileName.replaceAll(".txt", ""));
		    data.setFilePath(target.toPath().toString());
		    data.setPaid(true);
		    ContainerHandler.modify(fileName, data);
		    Settings.trigger_file_list_activated = true;
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static synchronized void transferFleetLogToUnpaidLocation(String fileName) {
		File source = new File(Settings.defaultFleetSavingLocationPath + File.separator + fileName);
		File target = new File(Settings.unfinishedFleetPaymentsPath + File.separator + fileName);
		try {
		    Files.move(source.toPath(), target.toPath(), StandardCopyOption.ATOMIC_MOVE);
		    DataSet data = ContainerHandler.get(fileName.replaceAll(".txt", ""));
		    data.setFilePath(target.toPath().toString());
		    ContainerHandler.modify(fileName, data);
		    Settings.trigger_file_list_activated = true;
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public static synchronized void transferFilesToUnfinishedIfPossible() {
		List<String> files = scanFilesAtLocation(Settings.defaultFleetSavingLocationPath);
		if (files.size() > 0) {
			String name = "";
			DataSet data = null;
			for (int i = 0; i < files.size(); i++) {
				name = files.get(i).substring(files.get(i).length() - 30, files.get(i).length()).trim();			
				data = new DataSet(EveLogReader.convertLogToList(getFileContent(files.get(i))));
				data.setFileName(name.replaceAll(".txt", ""));
				data.setFilePath(files.get(i));
				ContainerHandler.put(name.replaceAll(".txt", ""), data);
				
				transferFleetLogToUnpaidLocation(name);
				Logger.log(FileReader.class, "Moved " + name + " to unfinished payments");
			}
			Settings.trigger_file_list_activated = true;
		}
	}
	
	public static List<String> getAllEVELogFiles() {
		List<String> result = new ArrayList<>();
		result.addAll(getAllUnfinishedMiningFleetPayments());
		result.addAll(getAllFinishedMiningFleetPayments());
		return result;
	}

	public static void removeFile(String filePath) {
		File file = new File(filePath);
		try {
			Files.delete(file.toPath());
			Logger.log(FileReader.class, "Removed file at " + file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Settings.trigger_file_list_activated = true;
	}
}