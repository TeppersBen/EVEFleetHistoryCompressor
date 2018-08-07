package com.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FormatterTest {

	@Test
	public void convert_to_readable() {
		assertEquals("157,024.02", Formatter.convertToReadable(157024.0158));
		assertEquals("100,158,299.00", Formatter.convertToReadable(100158299));
	}
	
	@Test
	public void convert_to_groupnumber() {
		assertEquals("187,189,321,127,845", Formatter.convertToGroupedNumber(187189321127845l));
		assertEquals("189,572,157", Formatter.convertToGroupedNumber(189572157l));
	}
	
	@Test
	public void multiply_two_strings_into_readable() {
		assertEquals("100,000.00", Formatter.multiplyTwoStringsIntoReadable("100", "1000"));
		assertEquals("50.00", Formatter.multiplyTwoStringsIntoReadable("25", "2"));
	}
	
	@Test
	public void turn_logline_string_into_database_string() {
		assertEquals("Metal_Scraps", Formatter.turnLogLineStringIntoDatabaseString("METal_Scraps"));
	}
	
	@Test
	public void format_percentage() {
		assertEquals("15.87%", Formatter.formatPercentage(15.871));
		assertEquals("18%", Formatter.formatPercentage(18));
	}
	
}