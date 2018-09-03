package com.handlers;

import java.util.List;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

import com.Settings;
import com.entities.DataSet;
import com.entities.LogLine;
import com.entities.Player;
import com.utils.Formatter;
import com.utils.Logger;

public class TableHandler {

	private static TreeMap<String, DefaultTableModel[]> container = new TreeMap<>();
	
	public static void init() {
		DataSetHandler.getPaidDataSets().forEach(e -> {
			addIfPossible(e.getFileName(), 
						  new DefaultTableModel[] { 
								  createRegularLootTableModel(e.getLootList()),
								  createPlayerTableModel(e.getPlayerList())
						  });
		});
		
		DataSetHandler.getUnpaidDataSets().forEach(e -> {
			addIfPossible(e.getFileName(), 
					  new DefaultTableModel[] { 
							  createRegularLootTableModel(e.getLootList()),
							  createPlayerTableModel(e.getPlayerList())
					  });
		});
		
		DataSet dataset = DataSetHandler.get("Overall");
		addIfPossible("Overall",
				      new DefaultTableModel[] { 
				    		  createOverallLootTableModel(dataset.getLootList()),
							  createPlayerTableModel(dataset.getPlayerList())
				      });
	}
	
	public static DefaultTableModel[] get(String key) {
		Logger.log(TableHandler.class.getSimpleName(), key + " has been requested");
		return container.get(key);
	}
	
	public static DefaultTableModel getLootModel(String key) {
		Logger.log(TableHandler.class.getSimpleName(), key + " has been requested for LootList Model");
		return container.get(key)[0];
	}
	
	public static DefaultTableModel getPlayerStatModel(String key) {
		Logger.log(TableHandler.class.getSimpleName(), key + " has been requested for PlayerStat Model");
		return container.get(key)[1];
	}
	
	public static void remove(String key) {
		container.remove(key);
		Logger.log(TableHandler.class.getSimpleName(), key + " has been removed from the container");
	}
	
	public static void modify(String key, DefaultTableModel[] model) {
		container.replace(key, model);
		Logger.log(TableHandler.class.getSimpleName(), key + " was modified, new DefaultTableModel[]=" + model);
	}
	
	public static void modifyLootModel(String key, DefaultTableModel model) {
		DefaultTableModel[] models = get(key);
		models[0] = model;
		modify(key, models);
		Logger.log(TableHandler.class.getSimpleName(), key + " was modified, new LootListModel=" + model);
	}
	
	public static void modifyPlayerPayments(String key, DefaultTableModel model) {
		DefaultTableModel[] models = get(key);
		models[1] = model;
		modify(key, models);
		Logger.log(TableHandler.class.getSimpleName(), key + " was modified, new playerPaymentModel=" + model);
	}
	
	public static void addIfPossible(String key, DefaultTableModel[] model) {
		if (!container.containsKey(key)) {
			container.put(key, model);
			Logger.log(TableHandler.class.getSimpleName(), key + " with " + model + " has been added to the container");
		}
	}
	
	private static DefaultTableModel createRegularLootTableModel(List<LogLine> logs) {
		DefaultTableModel model = new DefaultTableModel(new Object[0][0], Settings.Regular_Loot_Table_Header_Names);
		
		String[] data;
		for (LogLine line : logs) {
			data = line.toString().split("#");
			data[4] = Formatter.convertToGroupedNumber(Long.parseLong(data[4]));
			model.addRow(data);
		}
		
		return model;
	}
	
	private static DefaultTableModel createPlayerTableModel(List<Player> players) {
		DefaultTableModel model = new DefaultTableModel(new Object[0][0], Settings.Player_Table_Header_Names);
		Object[] data;
		
		for (Player player : players) {
			data = player.toString().split("-");
			model.addRow(data);
		}
		
		return model;
	}
	
	private static DefaultTableModel createOverallLootTableModel(List<LogLine> logs) {
		DefaultTableModel model = new DefaultTableModel(new Object[0][0], Settings.Overall_Loot_Table_Header_Names);
		
		String[] data;
		String total;
		for (LogLine line : logs) {
			data = line.toString().split("#");
			total = Formatter.multiplyTwoStringsIntoReadable(data[3].replaceAll(" ISK", ""), data[4]);
			data[4] = Formatter.convertToGroupedNumber(Long.parseLong(data[4]));
			model.addRow(
				new String[] { data[1], data[2], data[4], data[3], total + " ISK" }
			);
		}
		
		return model;
	}
	
}