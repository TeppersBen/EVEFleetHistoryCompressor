package com.handlers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DatabaseHandlerTest {

	@Test
	public void get_item_price() {
		DatabaseHandler.init();
		
		assertEquals(1300.00, DatabaseHandler.getLootables().get("Metal_Scraps"), 0);
		assertEquals(16.9, DatabaseHandler.getHarvestables().get("Scordite"), 0);
	}
	
}
