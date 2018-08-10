package com.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.Settings;
import com.io.ConfigurationReader;
import com.swing.nodes.SelectFilePathNode;

public class SettingsPanel extends JPanel {
	
	private static final long serialVersionUID = -4528911195291791234L;
	
	private SelectFilePathNode unfinishedPayments;
	private SelectFilePathNode finishedPayments;
	private SelectFilePathNode eveOriginalLogs;
	
	private JButton apply;
	
	public SettingsPanel() {
		super(new BorderLayout());
		initComponents();
		layoutComponents();
		initListeners();
		
		setPresetSettings();
	}
	
	private void initComponents() {
		unfinishedPayments = new SelectFilePathNode("Unfinished payments", 55);
		finishedPayments = new SelectFilePathNode("Finished payments", 55);
		eveOriginalLogs = new SelectFilePathNode("Original EVE logs", 55);
		
		apply = new JButton("Apply");
	}
	
	private void layoutComponents() {
		
		JPanel pathMerge = new JPanel(new BorderLayout());
		pathMerge.add(unfinishedPayments, BorderLayout.NORTH);
		pathMerge.add(finishedPayments, BorderLayout.CENTER);
		pathMerge.add(eveOriginalLogs, BorderLayout.SOUTH);
		
		JPanel locationPaths = new JPanel(new FlowLayout());
		locationPaths.setBorder(BorderFactory.createTitledBorder("File locations paths"));
		
		locationPaths.add(pathMerge);
		
		add(locationPaths, BorderLayout.WEST);
		add(apply, BorderLayout.SOUTH);
	}
	
	private void setPresetSettings() {
		unfinishedPayments.setText(Settings.unfinishedFleetPaymentsPath);
		finishedPayments.setText(Settings.finishedFleetPaymentsPath);
		eveOriginalLogs.setText(Settings.defaultFleetSavingLocationPath);
	}
	
	private void initListeners() {
		apply.addActionListener(e -> {
			Settings.unfinishedFleetPaymentsPath = unfinishedPayments.getText();
			ConfigurationReader.modifyUserKey("unfinished_fleet_payments", Settings.unfinishedFleetPaymentsPath);
			
			Settings.finishedFleetPaymentsPath = finishedPayments.getText();
			ConfigurationReader.modifyUserKey("finished_fleet_payments", Settings.finishedFleetPaymentsPath);
			
			Settings.defaultFleetSavingLocationPath = eveOriginalLogs.getText();
			ConfigurationReader.modifyUserKey("default_eve_fleet_history", Settings.defaultFleetSavingLocationPath);
		});
	}
	
}