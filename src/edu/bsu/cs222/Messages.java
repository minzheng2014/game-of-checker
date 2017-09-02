package edu.bsu.cs222;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Messages {
	
	private JFrame errorWindow = new JFrame("ERROR");
	private JPanel formatErrorPanel = new JPanel();
	private JLabel message = new JLabel(
			"Invalid Move!");
	
	private Image errorWindowIcon = Toolkit.getDefaultToolkit().getImage(
			"errorMessage.png");
	public void errorMessage() {
		formatErrorPanel.add(message);
		errorWindow.setIconImage(errorWindowIcon);
		errorWindow.add(formatErrorPanel, BorderLayout.CENTER);
		errorWindow.setSize(300, 100);
		errorWindow.setResizable(false);
		errorWindow.setLocationRelativeTo(null);
		errorWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		errorWindow.setVisible(true);
	}
}
