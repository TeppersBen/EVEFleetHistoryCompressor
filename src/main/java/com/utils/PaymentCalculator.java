package com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.entities.LogLine;
import com.entities.Player;

public class PaymentCalculator {

	/**
	 * Turns a logline list into how much each player earned<br>
	 * List can look like this:<br><br>
	 * <code>
	 * Ben-658,450.00 ISK-38.54%<br>
	 * Stefan-265,700.00 ISK-15.55%<br>
	 * Ellen-134,410.00 ISK-7.87%<br>
	 * Zoran-650,000.00 ISK-38.04%
	 * </code>
	 * @param list - LogLine List
	 * @return Player List
	 */
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
