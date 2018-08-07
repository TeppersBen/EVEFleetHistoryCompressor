package com.entities;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class LogLineTest {

	private LogLine log;
	
	@Before
	public void setUp() {
		log = new LogLine("2050.11.05 14:50", "Stefan Zola", "Scordite", 7, "Harvestable");
	}
	
	@Test
	public void create_LogLine_using_string_as_logTime() {
		log = new LogLine("2018.08.07 01:49", "Ben Teppers", "Metal Scraps", 2, "P2W");
		assertEquals("2018.08.07 01:49#Ben Teppers#Metal Scraps#0,00 ISK#2#P2W", log.toString());
	}
	
	@Test
	public void create_LogLine_using_LocalDateTime_as_logTime() {
		LocalDateTime date = LocalDateTime.of(2018, 8, 7, 01, 49);
		log = new LogLine(date, "Ben Teppers", "PLEX", 1520, "P2W");
		assertEquals("2018.08.07 01:49#Ben Teppers#PLEX#0,00 ISK#1520#P2W", log.toString());
	}
}