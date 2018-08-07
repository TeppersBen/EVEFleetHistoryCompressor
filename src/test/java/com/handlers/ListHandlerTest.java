package com.handlers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.entities.LogLine;

public class ListHandlerTest {

	@Test
	public void compress_massive_logline_list() {
		LogLine log;
		List<LogLine> logs = new ArrayList<>();
		
		log = new LogLine("2018.08.07 05:00", "Ben", "Item1", 50, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.08 05:00", "Ben", "Item1", 50, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.07 05:00", "Stefan", "Item1", 84, "Group1");
		logs.add(log);
		log = new LogLine("2018.08.08 05:00", "Stefan", "Item1", 50, "Group1");
		logs.add(log);
		
		logs = ListHandler.compressMassiveLogList(logs);
		
		assertEquals("2018.08.07 05:00#Ben#Item1#0.00 ISK#100#Group1", logs.get(0).toString());
		assertEquals("2018.08.07 05:00#Stefan#Item1#0.00 ISK#134#Group1", logs.get(1).toString());
	}
	
}