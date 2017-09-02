package edu.bsu.cs222;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RestartGame extends MouseAdapter {

	private JButton restartButon = new JButton();
	private JFrame referenceFrame = new JFrame();
	private Color darkGreen = new Color(0, 100, 0);

	public RestartGame(JButton restart, JFrame frame) {
		this.restartButon = restart;
		this.referenceFrame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == restartButon) {	
			new StartScreenUI();
			referenceFrame.dispose();
		}
	}

	public void mouseEntered(MouseEvent restartClicked){
		if (restartClicked.getSource() == restartButon){
			restartButon.setBackground(Color.RED);
		}
	}
	
	public void mouseExited(MouseEvent restartClicked){
		if (restartClicked.getSource() == restartButon){
			restartButon.setBackground(darkGreen);
		}
	}
	
}
