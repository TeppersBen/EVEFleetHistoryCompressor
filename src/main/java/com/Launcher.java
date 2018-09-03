package com;

import javax.swing.SwingUtilities;

import com.handlers.DataSetHandler;
import com.handlers.DatabaseHandler;
import com.handlers.TableHandler;
import com.handlers.ThreadHandler;
import com.swing.SwingFrame;
import com.utils.Logger;

public class Launcher {

	public static void main(String[] args) {
		DatabaseHandler.init();
		DataSetHandler.init();
		TableHandler.init();

		SwingUtilities.invokeLater(() -> {
			new SwingFrame();
			Logger.printErr();
		});

		ThreadHandler
				.get_original_eve_history_log_location_checker(Settings.File_Transporter_EVE_to_Unfinished_Thread_Speed)
				.start();
	}

}