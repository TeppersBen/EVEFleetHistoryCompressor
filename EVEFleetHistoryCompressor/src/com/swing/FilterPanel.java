package com.swing;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class FilterPanel extends JPanel {

	private static final long serialVersionUID = -3361771769563260316L;

	public FilterPanel() {
		super(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Filters"));
	}
	
}
