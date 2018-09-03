package com.swing.nodes;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.Settings;
import com.entities.DataSet;
import com.handlers.DataSetHandler;
import com.handlers.TableHandler;
import com.utils.Table;

public class DataSetNode extends JPanel {
	
	private static final long serialVersionUID = -4576591025283717970L;

	private Table lootTable;
	private Table playerTable;
	
	private DataSet dataset;
	
	private boolean isOverall;
	private boolean isActive;
	
	public DataSetNode(DataSet dataset) {
		this(dataset, false);
	}
	
	public DataSetNode(DataSet dataset, boolean isOverall) {
		super(new BorderLayout());
		this.isOverall = isOverall;
		setDataset(dataset);
		initComponents();
		layoutComponents();
	}
	
	private void initComponents() {
		if (isOverall) {
			lootTable = new Table(TableHandler.getLootModel("Overall"));
			playerTable = new Table(TableHandler.getPlayerStatModel("Overall"));
		} else {
			if (dataset == null) {
				lootTable = new Table(Settings.Regular_Loot_Table_Header_Names);
				playerTable = new Table(Settings.Player_Table_Header_Names);
			} else {
				lootTable = new Table(TableHandler.getLootModel(dataset.getFileName()));
				playerTable = new Table(TableHandler.getPlayerStatModel(dataset.getFileName()));
			}
		}
		
		lootTable.resizeColumnWidth();
		playerTable.resizeColumnWidth();
	}
	
	private void layoutComponents() {
		if (!isOverall) {
			if (dataset != null) {
				setBorder(BorderFactory.createTitledBorder(dataset.getFileName()));
			} else {
				setBorder(BorderFactory.createTitledBorder("No file selected yet."));
			}
		}
		add(lootTable.getTableWithScrollBar(), BorderLayout.CENTER);
		add(playerTable.getTableWithScrollBar(), BorderLayout.EAST);
	}

	private void refreshTable() {
		if (dataset == null) return;
		if (dataset.getFileName().isEmpty()) return;
		lootTable.setModel(TableHandler.getLootModel(dataset.getFileName()));
		playerTable.setModel(TableHandler.getPlayerStatModel(dataset.getFileName()));
	}
	
	public void updateView(String name) {
		setDataset(DataSetHandler.get(name));
		refreshTable();
		if (!isOverall) {
			if (dataset != null) {
				setBorder(BorderFactory.createTitledBorder(dataset.getFileName()));
			} else {
				setBorder(BorderFactory.createTitledBorder("No file selected yet."));
			}
		}
	}
	
	public DataSet getDataset() {
		return dataset;
	}

	public void setDataset(DataSet dataset) {
		this.dataset = dataset;
	}

	public void clear() {
		lootTable.resetTable();
		playerTable.resetTable();
		setBorder(BorderFactory.createTitledBorder("No file selected yet."));
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}