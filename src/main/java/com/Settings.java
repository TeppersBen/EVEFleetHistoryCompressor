package com;

import java.io.File;

import com.io.ConfigurationReader;

public class Settings {

	public static final String CONFIGURATION_USER_SETTINGS_FILE = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "EVE_FLEET_HISTORY" + File.separator + "config.properties";
	public static final String APP_NAME = "EVE Fleet History Compressor";
	public static final String ERR_LOG_LOCATION = System.getProperty("user.home") + "\\Desktop\\EVE_ERROR_LOG.txt";
	
	public static final double File_Transporter_EVE_to_Unfinished_Thread_Speed = 1; //1sec
	
	public static volatile boolean trigger_file_list_activated;
	public static volatile boolean trigger_overall_view;
	
	public static String unfinishedFleetPaymentsPath = ConfigurationReader.getUserKey("unfinished_fleet_payments");
	public static String finishedFleetPaymentsPath = ConfigurationReader.getUserKey("finished_fleet_payments");
	public static String defaultFleetSavingLocationPath = ConfigurationReader.getUserKey("default_eve_fleet_history");
	
	public static final String database_Harvestables_File_Name = "Harvestables";
	public static final String database_Lootables_File_Name = "Lootables";
	
}