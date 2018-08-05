package com.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

import com.Settings;

public class DatabaseHandler {

	private static TreeMap<String, Double> harvestables = new TreeMap<>();
	private static TreeMap<String, Double> lootables = new TreeMap<>();
	
	public static void init() {
		setHarvestables(convertDataToMap(Settings.database_Harvestables_File_Name));
		setLootables(convertDataToMap(Settings.database_Lootables_File_Name));
	}
	
	private static TreeMap<String, Double> convertDataToMap(String fileName) {
		TreeMap<String, Double> map = new TreeMap<>();
		try (InputStream is = DatabaseHandler.class.getResourceAsStream("/resources/" + fileName);
			 InputStreamReader isr = new InputStreamReader(is);
			 BufferedReader br = new BufferedReader(isr);) {
			
		    String line;
		    String[] data;
			String estPrice;
		    while ((line = br.readLine()) != null) 
		    {
		    	data = line.split("-");
				estPrice = data[1].trim().replaceAll(",", "");
				map.put(data[0].trim(), Double.parseDouble(estPrice));
		    }
		    
		    br.close();
		    isr.close();
		    is.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return map;
	}

	public static TreeMap<String, Double> getHarvestables() {
		return harvestables;
	}

	public static void setHarvestables(TreeMap<String, Double> harvestables) {
		DatabaseHandler.harvestables = harvestables;
	}

	public static TreeMap<String, Double> getLootables() {
		return lootables;
	}

	public static void setLootables(TreeMap<String, Double> lootables) {
		DatabaseHandler.lootables = lootables;
	}
}