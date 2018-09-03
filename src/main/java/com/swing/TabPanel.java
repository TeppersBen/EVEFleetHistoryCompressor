package com.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.handlers.DataSetHandler;
import com.handlers.ThreadHandler;
import com.swing.nodes.DataSetNode;
import com.swing.nodes.LogNode;
import com.utils.Logger;

public class TabPanel extends JPanel {

	private static final long serialVersionUID = -3270157778518859428L;

	private LogNode node_unfinishedPayments = new LogNode(false);
	private LogNode node_finishedPayments = new LogNode(true);
	private DataSetNode node_overall = new DataSetNode(DataSetHandler.get("Overall"), true);
	
	private Object[] dataBox;
	
	public TabPanel() {
		super(new BorderLayout());
		JTabbedPane tab = new JTabbedPane();

		tab.add("Total Earnings", node_overall);
		tab.add("Unfinished Payments", node_unfinishedPayments);
		tab.add("Finished Payments", node_finishedPayments);
		add(tab);
		
		dataBox = new Object[] {node_overall, node_unfinishedPayments, node_finishedPayments };
		
		node_overall.setActive(true);
		
		ThreadHandler.assemble(node_overall, node_unfinishedPayments, node_finishedPayments);
		
		tab.addChangeListener(e -> {
			String selectedSource = ((JTabbedPane)e.getSource()).getTitleAt(((JTabbedPane)e.getSource()).getSelectedIndex());
			
			if (selectedSource.equalsIgnoreCase("Total Earnings")) {
				set_correct_window_active(0);
			} else if (selectedSource.equalsIgnoreCase("Unfinished Payments")) {
				set_correct_window_active(1);
			} else {
				set_correct_window_active(2);
			}
			
			Logger.log(this, "Selection updated to " + selectedSource);
			
		});
	}
	
	private void set_correct_window_active(int index) {
		for (int i = 0; i < dataBox.length; i++) {
			if (i == index) {
				if (dataBox[i] instanceof LogNode)
					((LogNode)dataBox[i]).setActive(true);
				else
					((DataSetNode)dataBox[i]).setActive(true);
			} else {
				if (dataBox[i] instanceof LogNode)
					((LogNode)dataBox[i]).setActive(false);
				else
					((DataSetNode)dataBox[i]).setActive(false);
			}	
		}
	}
}