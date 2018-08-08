package com.swing.nodes;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.entities.DataSet;
import com.handlers.ContainerHandler;
import com.handlers.ListHandler;
import com.handlers.ThreadHandler;
import com.utils.Logger;
import com.utils.Table;

public class DataSetNode extends JPanel {
	
	private static final long serialVersionUID = -4576591025283717970L;

	private Table lootTable;
	private Table playerTable;
	
	private DataSet dataset;
	
	private boolean isOverall;
	
	private Thread thread;
	
	public DataSetNode(DataSet dataset) {
		this(dataset, false);
	}
	
	public DataSetNode(DataSet dataset, boolean isOverall) {
		super(new BorderLayout());
		this.isOverall = isOverall;
		setDataset(dataset);
		initComponents();
		layoutComponents();
		if (dataset != null)
			fillLists();
		if (isOverall) {
			thread = ThreadHandler.overall_view_refresher(this);
			startThreadIfPossible();
		}
	}
	
	private void initComponents() {
		if (isOverall)
			lootTable = new Table("Character", "Item Type", "Quantity", "Est. Market", "Total Market");
		else
			lootTable = new Table("Time", "Character", "Item Type", "Est. Market", "Quantity", "Item Group");
		playerTable = new Table("Character", "Earned", "%");
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
	
	private void fillLists() {
		if (isOverall)
			ListHandler.fillEVEOveralEarningLootList(dataset.getLootList(), lootTable);
		else
			ListHandler.fillEVELootListTableUsingLogList(dataset.getLootList(), lootTable);
		ListHandler.fillEVEPlayerStatsListUsingPlayerList(dataset.getPlayerList(), playerTable);
	}

	public void updateView(String name) {
		setDataset(ContainerHandler.get(name));
		fillLists();
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
	
	public synchronized void shutdownThreadIfPossible() {
		if (getThread() != null && getThread().isAlive()) {
			try {
				Logger.log(this, "Closing Thread");
				ThreadHandler.overall_view_refresher = false;
				getThread().interrupt();
				getThread().join();
				Logger.log(this, "Thread Is Closed");
			} catch (InterruptedException e1) {
				Logger.log(this, "Thread Has Crashed");
			}
		}
	}
	
	public synchronized void startThreadIfPossible() {
		if (getThread() != null && getThread().isAlive())
			return;
		
		Logger.log(this, "Starting Thread");
		
		thread = ThreadHandler.overall_view_refresher(this);
		thread.start();
	}
	
	public Thread getThread() {
		return thread;
	}
	
}