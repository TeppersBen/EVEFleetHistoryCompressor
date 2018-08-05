package com.handlers;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.entities.DataSet;
import com.entities.LogLine;
import com.entities.Player;
import com.utils.Formatter;
import com.utils.PaymentCalculator;

public class ListHandler {
	
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
	
	public static void fillEVELootListTableUsingLogList(List<LogLine> list, JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		String[] data;
		for (LogLine line : list) {
			data = line.toString().split("#");
			data[4] = Formatter.convertToGroupedNumber(Long.parseLong(data[4]));
			model.addRow(data);
		}

		resizeColumnWidth(table);
	}
	
	public static void fillEVEOveralEarningLootList(List<LogLine> list, JTable table) {
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

		resizeColumnWidth(table);
	}
	
	public static void fillEVEPlayerStatsListUsingPlayerList(List<Player> list, JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] data;
		
		model.setRowCount(0);
		
		for (Player player : list) {
			data = player.toString().split("-");
			model.addRow(data);
		}
		
		resizeColumnWidth(table);
	}
	
	public static void fillEVEPlayerStatsListUsingLogList(List<LogLine> list, JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<Player> players = PaymentCalculator.getPayments(list);
		Object[] data;
		
		model.setRowCount(0);
		
		for (Player player : players) {
			data = player.toString().split("-");
			model.addRow(data);
		}
		
		resizeColumnWidth(table);
	}

	private static void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 30, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
	
	public static void fillEVELootFilesList(List<DataSet> list, JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.setRowCount(0);
		
		for (DataSet data : list) {
			model.addRow(new String[] { data.getFileName() });
		}
		
		resizeColumnWidth(table);
	}
	
}