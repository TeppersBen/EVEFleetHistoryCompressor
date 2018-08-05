package com.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.handlers.ContainerHandler;

public class TabPanel extends JPanel {

	private static final long serialVersionUID = -3270157778518859428L;

	private LogPanel unfinishedPayments = new LogPanel(false);
	private LogPanel finishedPayments = new LogPanel(true);
	private DataSetPanel overall = new DataSetPanel(ContainerHandler.get("Overall"), true);

	public TabPanel() {
		super(new BorderLayout());
		JTabbedPane tab = new JTabbedPane();

		tab.add("Total Earnings", overall);
		tab.add("Unfinished Payments", unfinishedPayments);
		tab.add("Finished Payments", finishedPayments);
		add(tab);
	}
}