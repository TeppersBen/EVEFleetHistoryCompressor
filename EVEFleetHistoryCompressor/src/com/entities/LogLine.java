package com.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.handlers.DatabaseHandler;
import com.utils.Formatter;
import com.utils.Logger;

public class LogLine {

	private final static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	
	private LocalDateTime logTime;
	private String characterName;
	private String itemType;
	private long quantity;
	private String itemGroup;
	
	public LogLine(String logTime, String characterName, String itemType, long quantity, String itemGroup) {
		this(LocalDateTime.parse(logTime, format), characterName, itemType, quantity, itemGroup);
	}
	
	public LogLine(LocalDateTime logTime, String characterName, String itemType, long quantity, String itemGroup) {
		this.logTime = logTime;
		this.characterName = characterName;
		this.itemType = itemType;
		this.quantity = quantity;
		this.itemGroup = itemGroup;
	}

	public LocalDateTime getLogTime() {
		return logTime;
	}

	public void setLogTime(LocalDateTime logTime) {
		this.logTime = logTime;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
	
	public double getItemPriceEach() {
		String name = itemType.replaceAll(" ", "_");
		name = Formatter.turnLogLineStringIntoDatabaseString(name);
		
		Double result = DatabaseHandler.getHarvestables().get(name);
		
		if (result == null) {
			result = DatabaseHandler.getLootables().get(name);
		}
		
		
		if (result != null && result == 0.0) {
			Logger.addErr(this, "[" + name + "] does not have an estimated market value");
		}
		
		if (result == null) {
			Logger.addErr(this, "[" + name + "] does not exist in your database");
			result = 0.0;
		}
		
		return result;
	}
	
	public double getTotalPrice() {
		return getItemPriceEach() * quantity;
	}
	
	public String getTotalPriceFormatted() {
		return Formatter.convertToReadable(getTotalPrice()) + " ISK";
	}
	
	public String toString() {
		return String.format("%s#%s#%s#%s ISK#%d#%s", logTime.format(format), characterName, itemType, Formatter.convertToReadable(getItemPriceEach()), quantity, itemGroup);
	}
}