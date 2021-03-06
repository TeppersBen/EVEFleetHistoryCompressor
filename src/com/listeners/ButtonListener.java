package com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.Settings;
import com.entities.DataSet;
import com.handlers.ContainerHandler;
import com.io.FileReader;
import com.swing.LogPanel;

public class ButtonListener {

	public static ActionListener get_set_paid_action_listener(LogPanel panel) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getSelectedIndex() == -1)
					return;			
				
				int press = JOptionPane.showOptionDialog(
					null,
					"Press 'Yes' if you want to move this log file to the finished payments location",
					"Confirmation",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					new Object[] { "Yes, please", "No" },
					"No"
				);
				
				System.out.println(press);
				
				if (press == 0) {
					DataSet data = ContainerHandler.get(panel.getDatasetPanel().getDataset().getFileName());
					data.setPaid(true);
					ContainerHandler.modify(panel.getDatasetPanel().getDataset().getFileName(), data);
					FileReader.transferFleetLogToPaidLocation(panel.getDatasetPanel().getDataset().getFileName());
					panel.fillLootFilesList();
					panel.resetSelectedIndex();
					panel.getDatasetPanel().clear();
					Settings.trigger_file_list_activated = true;
				}
			}
			
		};
	}
	
	public static ActionListener get_remove_loot_history_file(LogPanel panel) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getSelectedIndex() == -1)
					return;
				
				int press = JOptionPane.showOptionDialog(
						null,
						"Press 'Yes' if you want to remove this log file.",
						"Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						new Object[] { "Yes, please", "No" },
						"No"
				);
				if (press == 0) {
					FileReader.removeFile(panel.getDatasetPanel().getDataset().getFilePath());
					ContainerHandler.remove(panel.getDatasetPanel().getDataset().getFileName());
					panel.resetSelectedIndex();
					panel.getDatasetPanel().clear();
				}
			}
			
		};
	}
	
}