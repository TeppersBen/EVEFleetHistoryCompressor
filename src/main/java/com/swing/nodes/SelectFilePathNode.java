package com.swing.nodes;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class SelectFilePathNode extends JPanel {

	private static final long serialVersionUID = -6933329447882445469L;

	private JButton button;
	private JTextField textField;

	public SelectFilePathNode(String borderName) {
		this(borderName, 20);
	}

	public SelectFilePathNode(String borderName, int textFieldLength) {
		super(new BorderLayout());

		initComponents(borderName, textFieldLength);
		layoutComponents();
		initListeners();
	}

	private void initComponents(String borderName, int textFieldLength) {
		button = new JButton("Search");
		textField = new JTextField(textFieldLength);

		textField.setEditable(false);
		setBorder(BorderFactory.createTitledBorder(borderName));
	}

	private void layoutComponents() {
		add(textField, BorderLayout.CENTER);
		add(button, BorderLayout.EAST);
	}

	private void initListeners() {
		button.addActionListener(e -> {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				textField.setEditable(true);
				textField.setText(jfc.getSelectedFile().getAbsolutePath());
				textField.setEditable(false);
			}
		});
	}

	public String getText() {
		return textField.getText();
	}
	
	public void setText(String text) {
		textField.setText(text);
	}

}