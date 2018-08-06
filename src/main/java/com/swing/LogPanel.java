package com.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.handlers.ContainerHandler;
import com.handlers.ListHandler;
import com.handlers.ThreadHandler;
import com.listeners.ButtonListener;
import com.utils.Logger;
import com.utils.Table;

public class LogPanel extends JPanel {
	
	private static final long serialVersionUID = -8678598204342703638L;
	
	private Table fileTable;
	private DataSetPanel datasetPanel;
	
	private JButton button_RemoveFile;
	private JButton button_SetPaid;
	
	private boolean isPaid;
	private int selectedIndex = -1;
	
	public LogPanel(boolean isPaid) {
		super(new BorderLayout());
		setPaid(isPaid);
		setDatasetPanel(new DataSetPanel(null));
		initComponents();
		layoutComponents();
		initListeners();
		fillLootFilesList();
		
		ThreadHandler.file_refresher(this).start();
		
	}
	
	private void initComponents() {
		fileTable = new Table("Files");
		if (!isPaid()) {
			button_RemoveFile = new JButton("Remove History File");
			button_SetPaid = new JButton("Set Paid");
		}
	}
	
	private void layoutComponents() {
		JScrollPane scroll = fileTable.getTableWithScrollBar();
		scroll.setPreferredSize(new Dimension(156, 0));
		add(scroll, BorderLayout.WEST);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new BorderLayout());
		
		if (!isPaid()) {
			buttons.add(button_RemoveFile, BorderLayout.CENTER);
			buttons.add(button_SetPaid, BorderLayout.WEST);
		}
		
		panel.add(datasetPanel, BorderLayout.CENTER);
		if (!isPaid())
			panel.add(buttons, BorderLayout.SOUTH);
		
		add(panel, BorderLayout.CENTER);
	}
	
	private void initListeners() {
		fileTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if ((!event.getValueIsAdjusting() && fileTable.getSelectedRow() != -1)) {
					if (fileTable.getSelectedRow() == selectedIndex)
						return;
					
					selectedIndex = fileTable.getSelectedRow();
					String name = fileTable.getValueAt(selectedIndex, 0).toString();
					datasetPanel.updateView(name);
					Logger.log(this, "View window changed to " + name);
				}
			}
		});
		
		if (!isPaid()) {
			button_SetPaid.addActionListener(
				ButtonListener.get_set_paid_action_listener(this)
			);
			
			button_RemoveFile.addActionListener(
				ButtonListener.get_remove_loot_history_file(this)
			);
		}
		
	}
	
	public void updateAll() {
		fillLootFilesList();
		fileTable.changeSelection(getSelectedIndex(), 0, true, false);
	}
	
	public void fillLootFilesList() {
		if (isPaid()) 
			ListHandler.fillEVELootFilesList(ContainerHandler.getPaidDataSets(), fileTable);
		else 
			ListHandler.fillEVELootFilesList(ContainerHandler.getUnpaidDataSets(), fileTable);
	}

	public DataSetPanel getDatasetPanel() {
		return datasetPanel;
	}

	public void setDatasetPanel(DataSetPanel datasetPanel) {
		this.datasetPanel = datasetPanel;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	public Table getTable() {
		return fileTable;
	}

	public void resetSelectedIndex() {
		selectedIndex = -1;		
	}
	
}