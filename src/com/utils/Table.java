package com.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.util.regex.PatternSyntaxException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class Table extends JTable {
	
	private static final long serialVersionUID = 4162204050779075227L;
	private boolean isEditable;
	
	private TableRowSorter<DefaultTableModel> sorter;
	
	public Table(String... columnNames) {
		this(new Object[0][0], columnNames);
	}
	
	public Table(Object[][] data, String... columnNames) {
		super(new DefaultTableModel(data, columnNames));
		getTableHeader().setReorderingAllowed(false);
		sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel)getModel());
		setRowSorter(sorter);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return isEditable;
	}
	
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
	
	public void addRow(Object... data) {
		((DefaultTableModel)getModel()).addRow(data);
	}
	
	public void resetTable() {
		((DefaultTableModel)getModel()).setRowCount(0);
	}
	
	public JScrollPane getTableWithScrollBar() {
		JScrollPane pane = new JScrollPane(this);
		pane.setPreferredSize(new Dimension(280, 2));
		return pane;
	}
     
	public void setFilter(String data, int column) {
		RowFilter<DefaultTableModel, Object> filter = null;
		try {
			filter = RowFilter.regexFilter(data, column);
    	} catch (PatternSyntaxException ex) {
    		return;
    	}
    	sorter.setRowFilter(filter);
    }
	
	public void resizeColumnWidth() {
		TableCellRenderer renderer = null;
		Component comp = null;
		int minWidth = 15;
		for (int column = 0; column < getColumnCount(); column++) {
			for (int row = 0; row < getRowCount(); row++) {
				renderer = getCellRenderer(row, column);
				comp = prepareRenderer(renderer, row, column);
				minWidth = Math.max(comp.getPreferredSize().width + 30, minWidth);
			}
			getColumnModel().getColumn(column).setPreferredWidth(minWidth);
		}
	}
}