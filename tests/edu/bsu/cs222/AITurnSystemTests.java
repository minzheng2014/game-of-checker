package edu.bsu.cs222;

import static org.junit.Assert.assertNotNull;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.junit.Test;

public class AITurnSystemTests {
	private JLabel score = new JLabel();

	@Test
	public void turnSystemTest() {
		JLabel score = upDateScore();
		assertNotNull(score);
	}
	
	@Test
	public void configureWinTest(){
		JFrame testFrame = configureWinScreen("win", "win");
		assertNotNull(testFrame);
		
	}
	private int[][] layout = { { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 }, { 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 } };
	public JLabel upDateScore() {
		int player1CurrentScore = 0;
		int player2CurrentScore = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (layout[i][j] == 1) {
					player2CurrentScore += 1;
				}
				if (layout[i][j] == 2) {
					player1CurrentScore += 1;
				}
			}
		}
		
		score.setText("SCORES - PLAYER 1: "
				+ Integer.toString(12 - player1CurrentScore) + " / PLAYER 2: "
				+ Integer.toString(12 - player2CurrentScore));
		return score;
	}

	private JFrame winWindow = new JFrame("Victory");
	private JPanel formatWinPanel = new JPanel();
	private JButton restartButton = new JButton("RESTART");

	private Image windowIcon = Toolkit.getDefaultToolkit().getImage("checkers.jpg");

	public JFrame configureWinScreen(String message, String icon) {
		JLabel victoryIcon = new JLabel(new ImageIcon(icon));
		JLabel winMessage = new JLabel(message, SwingConstants.CENTER);
		winMessage.setOpaque(true);
		winMessage.setBackground(new Color(0, 100, 0));
		winMessage.setForeground(Color.WHITE);
		victoryIcon.setOpaque(true);
		victoryIcon.setBackground(new Color(0, 100, 0));
		restartButton.setBackground(new Color(0, 100, 0));
		restartButton.setForeground(Color.WHITE);
		restartButton.addMouseListener(new RestartGame(restartButton, winWindow));
		formatWinPanel.add(winMessage, BorderLayout.NORTH);
		formatWinPanel.add(victoryIcon, BorderLayout.CENTER);
		formatWinPanel.add(restartButton, BorderLayout.SOUTH);
		formatWinPanel.setBackground(new Color(0, 100, 0));
		winWindow.add(formatWinPanel, BorderLayout.CENTER);
		winWindow.setSize(270, 270);
		winWindow.setIconImage(windowIcon);
		winWindow.setResizable(false);
		winWindow.setLocationRelativeTo(null);
		winWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winWindow.setVisible(true);
		return winWindow;
	}

}
