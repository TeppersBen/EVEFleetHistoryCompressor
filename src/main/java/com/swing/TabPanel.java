package com.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.handlers.ContainerHandler;
import com.swing.nodes.DataSetNode;
import com.swing.nodes.LogNode;

public class TabPanel extends JPanel {

	private static final long serialVersionUID = -3270157778518859428L;

	private LogNode node_unfinishedPayments = new LogNode(false);
	private LogNode node_finishedPayments = new LogNode(true);
	private DataSetNode node_overall = new DataSetNode(ContainerHandler.get("Overall"), true);

	public TabPanel() {
		super(new BorderLayout());
		JTabbedPane tab = new JTabbedPane();

		tab.add("Total Earnings", node_overall);
		tab.add("Unfinished Payments", node_unfinishedPayments);
		tab.add("Finished Payments", node_finishedPayments);
		add(tab);
		
		tab.addChangeListener(e -> {
			String selectedSource = ((JTabbedPane)e.getSource()).getTitleAt(((JTabbedPane)e.getSource()).getSelectedIndex());
			
			switch (selectedSource) {
			case "Total Earnings":
				//start Overall threads
				break;
			case "Unfinished Payments":
				//start Unfinished payments threads
				break;
			case "Finished Payments":
				//start finished payments threads
				break;
			default:
				break;
			}
		});
	}
}