package com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.entities.LogLine;
import com.entities.Player;

public class PaymentCalculator {

	public static List<Player> getPayments(List<LogLine> list) {
		List<Player> characters = new ArrayList<>();		
		Object[] uniqueNames;
		List<LogLine> mined = new ArrayList<>();
		double payment;
		Player character;
		
		uniqueNames = list.stream().map(e -> e.getCharacterName())
					 .distinct().toArray();
		
		for (Object el : uniqueNames) {
			final String name = (String) el;
			
			mined = list.stream().filter(e -> e.getCharacterName().equalsIgnoreCase(name)).collect(Collectors.toList());
			
			payment = mined.stream().mapToDouble(e -> e.getTotalPrice()).sum();
			
			character = new Player(name, payment, 0);
			characters.add(character);
		}
		
		double totalValue = 0;
		for (Player player : characters) {
			totalValue += player.getTotalPayment();
		}
		
		for (Player player : characters) {
			player.setPercentage(player.getTotalPayment() / totalValue * 100);
		}
		
		return characters;
	}
	
}
