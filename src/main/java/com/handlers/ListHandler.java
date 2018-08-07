package com.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.entities.DataSet;
import com.entities.LogLine;
import com.entities.Player;
import com.utils.Formatter;
import com.utils.PaymentCalculator;
import com.utils.Table;

public class ListHandler {
	
	/**
	 * Turns a massive logline list with multiple lines of same charactername and items collected into
	 * a small logline list where those lines are merged together into one logline
	 * @param list - LogLine List
	 * @return merged LogLine List
	 */
	public static List<LogLine> compressMassiveLogList(List<LogLine> list) {
		List<LogLine> compressedList = new ArrayList<>();
		int length = list.size();
		int index = 0;
		LogLine tempLine;
		boolean exists = false;
		while (index != length) {
			tempLine = list.get(index);
			
			for (int i = 0; i < compressedList.size(); i++) {
				if (tempLine.getCharacterName().equalsIgnoreCase(compressedList.get(i).getCharacterName())
						&& tempLine.getItemType().equalsIgnoreCase(compressedList.get(i).getItemType())) {
						exists = true;
					}
			}
			
			if (!exists) {
				for (int i = 0; i < length; i++) {
					if (tempLine.getCharacterName().equalsIgnoreCase(list.get(i).getCharacterName())
						&& tempLine.getItemType().equalsIgnoreCase(list.get(i).getItemType())
						&& index != i) {
						tempLine.setQuantity(tempLine.getQuantity() + list.get(i).getQuantity());
					}
				}
				compressedList.add(tempLine);
			}
			
			exists = false;
			index++;
		}
		
		return compressedList;
	}
	
	/**
	 * Fills the table with the values stored within the passed list<br>
	 * This method should only be used for filling normal loot tables
	 * @param list - LogLine List
	 * @param table - Table
	 */
	public static void fillEVELootListTableUsingLogList(List<LogLine> list, Table table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		String[] data;
		for (LogLine line : list) {
			data = line.toString().split("#");
			data[4] = Formatter.convertToGroupedNumber(Long.parseLong(data[4]));
			model.addRow(data);
		}

		table.resizeColumnWidth();
	}
	
	/**
	 * Fills the table with the values stored within the passed list<br>
	 * This method should only be used for a massive lootList table
	 * @param list - LogLine List.
	 * @param table - Table
	 */
	public static void fillEVEOveralEarningLootList(List<LogLine> list, Table table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		String[] data;
		String total;
		for (LogLine line : list) {
			data = line.toString().split("#");
			total = Formatter.multiplyTwoStringsIntoReadable(data[3].replaceAll(" ISK", ""), data[4]);
			data[4] = Formatter.convertToGroupedNumber(Long.parseLong(data[4]));
			model.addRow(
				new String[] { data[1], data[2], data[4], data[3], total + " ISK" }
			);
		}

		table.resizeColumnWidth();
	}
	
	/**
	 * Fills the table with the values stored within the passed list<br>
	 * This method is used to fill playerstat tables
	 * @param list - Player List
	 * @param table - Table
	 */
	public static void fillEVEPlayerStatsListUsingPlayerList(List<Player> list, Table table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] data;
		
		model.setRowCount(0);
		
		for (Player player : list) {
			data = player.toString().split("-");
			model.addRow(data);
		}
		
		table.resizeColumnWidth();
	}
	
	/**
	 * Fills the table with the values stored within the passed list<br>
	 * this method should only be used if you want to fill the table using logLine List
	 * @param list - LogLine List
	 * @param table - Table
	 */
	public static void fillEVEPlayerStatsListUsingLogList(List<LogLine> list, Table table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<Player> players = PaymentCalculator.getPayments(list);
		Object[] data;
		
		model.setRowCount(0);
		
		for (Player player : players) {
			data = player.toString().split("-");
			model.addRow(data);
		}
		
		table.resizeColumnWidth();
	}
	
	/**
	 * Fills the table with the values stored within the passed list<br>
	 * this method is used to fill a file table
	 * @param list - DataSet List
	 * @param table - Table
	 */
	public static void fillEVELootFilesList(List<DataSet> list, Table table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.setRowCount(0);
		
		for (DataSet data : list) {
			model.addRow(new String[] { data.getFileName() });
		}
		
		table.resizeColumnWidth();
	}
	
}