package com.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.utils.Logger;

public class WindowListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		Logger.requestErrPrintExportToFile();
	}
	
}