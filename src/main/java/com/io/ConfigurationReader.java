package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.Settings;

public class ConfigurationReader {

	private static void createDefaultProperties() {
		try {
			File configFile = new File(Settings.CONFIGURATION_USER_SETTINGS_FILE.substring(0, Settings.CONFIGURATION_USER_SETTINGS_FILE.length()-17), "config.properties");
			configFile.getParentFile().mkdirs();
			Properties config = new Properties();			

			config.setProperty("unfinished_fleet_payments", "");
			config.setProperty("finished_fleet_payments", "");
			config.setProperty("default_eve_fleet_history", "");
			
			FileOutputStream fos = new FileOutputStream(configFile);
			config.store(fos, "user settings");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getKey(String file, String key) {
		try {
			File configFile = new File(file);
			if (!configFile.exists()) 
				createDefaultProperties();

			FileInputStream fis = new FileInputStream(configFile);
			Properties config = new Properties();
			config.load(fis);
			fis.close();
			return config.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}
	
	public static String getUserKey(String key) {
		return getKey(Settings.CONFIGURATION_USER_SETTINGS_FILE, key);
	}
	
	public static void modifyUserKey(String key, String value) {
		modifyKey(Settings.CONFIGURATION_USER_SETTINGS_FILE, key, value);
	}
	
	private static void modifyKey(String file, String key, String value) {
		try {
			File configFile = new File(file);
			Properties config = new Properties();

			FileInputStream fis = new FileInputStream(configFile);
			config.load(fis);
			fis.close();

			config.setProperty(key, value);

			FileOutputStream fos = new FileOutputStream(configFile);
			config.store(fos, "user settings");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}