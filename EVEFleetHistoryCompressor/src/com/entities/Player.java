package com.entities;

import com.utils.Formatter;

public class Player {

	private String characterName;
	private double totalPayment;
	private double percentage;
	
	public Player(String characterName, double totalPayment, double percentage) {
		this.characterName = characterName;
		this.totalPayment = totalPayment;
		this.setPercentage(percentage);
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	public double getTotalPayment() {
		return totalPayment;
	}
	
	public String getTotalPaymentFormatted() {
		return Formatter.convertToReadable(getTotalPayment()) + " ISK";
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}
	
	public String toString() {
		return characterName + "-" + getTotalPaymentFormatted() + "-" + Formatter.formatPercentage(percentage);
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
}