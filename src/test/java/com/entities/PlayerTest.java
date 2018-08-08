package com.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player player;
	
	@Before
	public void SetUp() {
		player = new Player("TestDummy", 51722.04, 48.2484);
	}
	
	@Test
	public void get_character_name() {
		assertEquals("TestDummy", player.getCharacterName());
	}
	
	@Test
	public void set_character_name() {
		player.setCharacterName("Test");
		assertEquals("Test", player.getCharacterName());
	}
	
	@Test
	public void get_total_payment() {
		assertEquals(51722.04, player.getTotalPayment(), 0);
	}
	
	@Test
	public void set_total_payment() {
		player.setTotalPayment(50.15);
		assertEquals(50.15, player.getTotalPayment(), 0);
	}
	
	@Test
	public void get_total_payment_formatted() {
		assertEquals("51,722.04 ISK", player.getTotalPaymentFormatted());
	}
	
	@Test
	public void get_percentage() {
		assertEquals(48.2484, player.getPercentage(), 0);
	}
	
	@Test
	public void set_percentage() {
		player.setPercentage(47);
		assertEquals(47, player.getPercentage(), 0);
	}
	
	@Test
	public void get_to_string() {
		assertEquals("TestDummy-51,722.04 ISK-48.25%", player.toString());
	}
	
}