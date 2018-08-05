package com.handlers;

import com.Settings;
import com.io.FileReader;
import com.swing.DataSetPanel;
import com.swing.LogPanel;

public class ThreadHandler {

	public static Thread get_original_eve_history_log_location_checker(double tick_per_second) {
		return new Thread(() -> {
			while (true) {
				
				FileReader.transferFilesToUnfinishedIfPossible();
				ContainerHandler.updateMassiveDataSet();
				
				try {
					Thread.sleep((long)(1000 / tick_per_second));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	public static Thread file_refresher(LogPanel panel) {
		return new Thread(() -> {
			while (true) {
				
				panel.updateAll();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Thread overall_view_refresher(DataSetPanel panel) {
		return new Thread(() -> {
			while (true) {
				
				if (Settings.trigger_overall_view) {
					panel.updateView("Overall");
					Settings.trigger_overall_view = false;
				}
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}