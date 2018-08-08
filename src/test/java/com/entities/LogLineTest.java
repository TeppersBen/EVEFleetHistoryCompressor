package com.entities;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.handlers.DatabaseHandler;

public class LogLineTest {

	private LogLine log;
	
	@BeforeClass
	public static void init() {
		DatabaseHandler.init();
	}
	
	@Before
	public void setUp() {
		log = new LogLine("2050.11.05 14:50", "ZappyBoii", "Scordite", 7, "Harvestable");
	}
	
	@Test
	public void create_LogLine_using_string_as_logTime() {
		log = new LogLine("2018.08.07 01:49", "Ben", "Metal_Scraps", 2, "P2W");
		assertEquals("2018.08.07 01:49#Ben#Metal_Scraps#1,300.00 ISK#2#P2W", log.toString());
	}
	
	@Test
	public void create_LogLine_using_LocalDateTime_as_logTime() {
		LocalDateTime date = LocalDateTime.of(2018, 8, 7, 01, 49);
		log = new LogLine(date, "Ben", "Metal_Scraps", 1520, "P2W");
		assertEquals("2018.08.07 01:49#Ben#Metal_Scraps#1,300.00 ISK#1520#P2W", log.toString());
	}
	
	@Test
	public void get_dateTime() {
		assertEquals(LocalDateTime.of(2050, 11, 05, 14, 50), log.getLogTime());
	}
	
	@Test
	public void get_character_name() {
		assertEquals("ZappyBoii", log.getCharacterName());
	}
	
	@Test
	public void set_character_name() {
		log.setCharacterName("ZipZap");
		assertEquals("ZipZap", log.getCharacterName());
	}
	
	@Test
	public void get_item_type() {
		assertEquals("Scordite", log.getItemType());
	}
	
	@Test
	public void set_item_type() {
		log.setItemType("NewItem");
		assertEquals("NewItem", log.getItemType());
	}
	
	@Test
	public void get_item_quantity() {
		assertEquals(7, log.getQuantity());
	}
	
	@Test
	public void set_item_quantity() {
		log.setQuantity(500);
		assertEquals(500, log.getQuantity());
	}
	
	@Test
	public void get_item_group() {
		assertEquals("Harvestable", log.getItemGroup());
	}
	
	@Test
	public void set_item_group() {
		log.setItemGroup("NewGroup");
		assertEquals("NewGroup", log.getItemGroup());
	}
	
	@Test
	public void get_estimated_item_price() {
		assertEquals(16.9, log.getEstimatedItemPrice(), 0);
	}
	
	@Test
	public void get_total_price() {
		assertEquals(118.3, log.getTotalPrice(), 0.2);
	}
	
	@Test
	public void get_total_price_formatted() {
		assertEquals("118.30 ISK", log.getTotalPriceFormatted());
	}
	
	@Test
	public void get_to_string() {
		assertEquals("2050.11.05 14:50#ZappyBoii#Scordite#16.90 ISK#7#Harvestable", log.toString());
	}
}