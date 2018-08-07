package com.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.entities.LogLine;
import com.entities.Player;
import com.handlers.DatabaseHandler;

public class PaymentCalculatorTest {

	@Test
	public void getPayments() {
		DatabaseHandler.init();
		LogLine log;
		List<LogLine> logs = new ArrayList<>();
		
		log = new LogLine("2018.08.07 15:00", "Ben", "Metal_Scraps", 500, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 15:00", "Ben", "Scordite", 500, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 15:00", "Stefan", "Metal_Scraps", 200, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 15:00", "Stefan", "Veldspar", 500, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 15:00", "Ellen", "Metal_Scraps", 100, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 15:00", "Ellen", "Pyroxeres", 100, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 15:00", "Zoran", "Metal_Scraps", 500, "Group1");
		logs.add(log);
		
		List<Player> players = PaymentCalculator.getPayments(logs);
		
		assertEquals("Ben-658,450.00 ISK-38.54%", players.get(0).toString());
		assertEquals("Stefan-265,700.00 ISK-15.55%", players.get(1).toString());
		assertEquals("Ellen-134,410.00 ISK-7.87%", players.get(2).toString());
		assertEquals("Zoran-650,000.00 ISK-38.04%", players.get(3).toString());
	}
	
}