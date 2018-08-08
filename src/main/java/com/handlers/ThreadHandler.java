package com.handlers;

import com.Settings;
import com.io.FileReader;
import com.swing.nodes.DataSetNode;
import com.swing.nodes.LogNode;

public class ThreadHandler {

	public volatile static boolean overall_view_refresher;
	public volatile static boolean file_refresher;
	public volatile static boolean get_original_eve_history_log_location_checker;
	
	public static Thread get_original_eve_history_log_location_checker(double tick_per_second) {
		return new Thread(() -> {
			
			get_original_eve_history_log_location_checker = true;
			
			while (get_original_eve_history_log_location_checker) {
				
				FileReader.transferFilesToUnfinishedIfPossible();
				ContainerHandler.updateMassiveDataSet();
				
				try {
					Thread.sleep((long)(1000 / tick_per_second));
				} catch (InterruptedException e) {
					
				}
			}
		});
	}	
	
	public static Thread file_refresher(LogNode panel) {
		return new Thread(() -> {
			
			file_refresher = true;
			
			while (file_refresher) {
				
				panel.updateAll();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
				}
			}
		});
	}
	
	public static Thread overall_view_refresher(DataSetNode panel) {
		return new Thread(() -> {
			
			overall_view_refresher = true;
			
			while (overall_view_refresher) {		
				
				if (Settings.trigger_overall_view) {
					panel.updateView("Overall");
					Settings.trigger_overall_view = false;
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
				}
			}
		});
	}
}