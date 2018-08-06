package com.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.Settings;

public class SwingFrame extends JFrame {

	private static final long serialVersionUID = 2693157725904702767L;

	public SwingFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Settings.APP_NAME);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		initComponents();

		pack();

		validate();

		setVisible(true);

		setMinimumSize(new Dimension(getWidth() + 500, 500));

		setLocationRelativeTo(null);
	}

	private void initComponents() {
		JTabbedPane tab = new JTabbedPane();

		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(new FilterPanel(), BorderLayout.NORTH);
		mainPanel.add(new TabPanel(), BorderLayout.CENTER);

		tab.add("Fleet History Compressor", mainPanel);
		tab.add("Settings", new SettingsPanel());

		add(tab);
	}

}