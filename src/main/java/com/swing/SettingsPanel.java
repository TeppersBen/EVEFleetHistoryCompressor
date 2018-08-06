package com.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Settings;
import com.io.ConfigurationReader;

public class SettingsPanel extends JPanel {
	
	private static final long serialVersionUID = -4528911195291791234L;

	private JLabel label_unfinishedFleetPayments;
	private JTextField txt_unfinishedFleetPayments;
	private JButton button_setUnfinishedFleetPayments;
	
	private JLabel label_finishedFLeetPayments;
	private JTextField txt_finishedFleetPayments;
	private JButton button_setFinishedFleetPayments;
	
	private JLabel label_defaultEveFleetHistory;
	private JTextField txt_defaultEveFleetHistory;
	private JButton button_setDefaultEveFleetHistory;
	
	public SettingsPanel() {
		super(new BorderLayout());
		initComponents();
		layoutComponents();
		initListeners();
	}
	
	private void initComponents() {
		label_unfinishedFleetPayments = new JLabel("Unfinished payments: ");
		txt_unfinishedFleetPayments = new JTextField(30);
		button_setUnfinishedFleetPayments = new JButton("Set");
		
		label_finishedFLeetPayments = new JLabel("Finished payments: ");
		txt_finishedFleetPayments = new JTextField(30);
		button_setFinishedFleetPayments = new JButton("Set");
		
		label_defaultEveFleetHistory = new JLabel("original EVE log location: ");
		txt_defaultEveFleetHistory = new JTextField(30);
		button_setDefaultEveFleetHistory = new JButton("Set");
	}
	
	private void layoutComponents() {
		JPanel panel_0 = new JPanel(new BorderLayout());
		panel_0.add(label_unfinishedFleetPayments, BorderLayout.WEST);
		panel_0.add(txt_unfinishedFleetPayments, BorderLayout.CENTER);
		panel_0.add(button_setUnfinishedFleetPayments, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel(new BorderLayout());
		panel_1.add(label_finishedFLeetPayments, BorderLayout.WEST);
		panel_1.add(txt_finishedFleetPayments, BorderLayout.CENTER);
		panel_1.add(button_setFinishedFleetPayments, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel(new BorderLayout());
		panel_2.add(label_defaultEveFleetHistory, BorderLayout.WEST);
		panel_2.add(txt_defaultEveFleetHistory, BorderLayout.CENTER);
		panel_2.add(button_setDefaultEveFleetHistory, BorderLayout.EAST);
		
		JPanel pathMerge = new JPanel(new BorderLayout());
		pathMerge.add(panel_0, BorderLayout.NORTH);
		pathMerge.add(panel_1, BorderLayout.CENTER);
		pathMerge.add(panel_2, BorderLayout.SOUTH);
		
		JPanel locationPaths = new JPanel(new FlowLayout());
		locationPaths.setBorder(BorderFactory.createTitledBorder("File locations paths"));
		
		locationPaths.add(pathMerge);
		
		add(locationPaths, BorderLayout.WEST);
	}
	
	private void initListeners() {
		button_setUnfinishedFleetPayments.addActionListener(e -> {
			Settings.unfinishedFleetPaymentsPath = txt_unfinishedFleetPayments.getText();
			ConfigurationReader.modifyUserKey("unfinished_fleet_payments", txt_unfinishedFleetPayments.getText());
		});
		
		button_setFinishedFleetPayments.addActionListener(e -> {
			Settings.finishedFleetPaymentsPath = txt_finishedFleetPayments.getText();
			ConfigurationReader.modifyUserKey("finished_fleet_payments", txt_finishedFleetPayments.getText());
		});
		
		button_setDefaultEveFleetHistory.addActionListener(e -> {
			Settings.defaultFleetSavingLocationPath = txt_defaultEveFleetHistory.getText();
			ConfigurationReader.modifyUserKey("default_eve_fleet_history", txt_defaultEveFleetHistory.getText());
		});
	}
	
}