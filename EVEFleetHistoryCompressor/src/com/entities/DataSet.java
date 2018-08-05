package com.entities;

import java.util.List;

import com.io.EveLogReader;

public class DataSet {

	private String fileName;
	private String filePath;
	private boolean isPaid;
	private List<LogLine> lootList;
	private List<Player> playerList;
	
	public DataSet() {}
	
	public DataSet(List<LogLine> lootList) {
		this(lootList, null);
	}
	
	public DataSet(List<LogLine> lootList, List<Player> playerList) {
		setLootList(lootList);
		setPlayerList(playerList);
		if (playerList == null) 
			generatePlayerStats();
	}

	public void generatePlayerStats() {
		setPlayerList(EveLogReader.getPlayerStatsOutOfLogFile(getLootList()));
	}
	
	public List<LogLine> getLootList() {
		return lootList;
	}

	public void setLootList(List<LogLine> lootList) {
		this.lootList = lootList;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}