package edu.bsu.cs222;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class InstructionsUI implements ActionListener {

	private JMenuItem instructions = new JMenuItem();

	public InstructionsUI(JMenuItem authors) {
		this.instructions = authors;
	}

	@Override
	public void actionPerformed(ActionEvent instructionsClicked) {
		if (instructionsClicked.getSource() == instructions) {
			configureAuthorsWindow();
		}
	}

	private JFrame instructionsPanel = new JFrame("Instructions");
	private JLabel instructionsText = new JLabel(
			"<html>To Move: Click on corners of pieces<br>To Capture: Click on corner of piece near capturable opposing piece<br> To Win: Capture all opposing pieces<html>",
			SwingConstants.CENTER);

	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");
	
	@SuppressWarnings("static-access")
	public void configureAuthorsWindow() {
		instructionsPanel.setSize(500, 100);
		instructionsPanel.setIconImage(windowIcon);
		instructionsPanel.add(instructionsText, BorderLayout.CENTER);
		instructionsPanel.setLocationRelativeTo(null);
		instructionsPanel.setDefaultCloseOperation(instructionsPanel.DISPOSE_ON_CLOSE);
		instructionsPanel.setVisible(true);
	}

}