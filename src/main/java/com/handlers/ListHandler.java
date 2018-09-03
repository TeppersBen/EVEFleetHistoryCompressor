package com.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.entities.DataSet;
import com.entities.LogLine;
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