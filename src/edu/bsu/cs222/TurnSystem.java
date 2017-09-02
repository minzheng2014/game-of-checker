package edu.bsu.cs222;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TurnSystem extends MouseAdapter {

	private JButton endTurn = new JButton();
	private JLabel currentPlayerTurn = new JLabel();
	private JLabel score = new JLabel();
	private JButton[][] boardUILayout;
	private int[][] currentBoardLayout;
	private MouseListener player1;
	private MouseListener player2;
	private int lastTurn;
	private LastTurn turn = new LastTurn(lastTurn);

	private JFrame referenceFrame = new JFrame();

	public TurnSystem(JButton endTurn, JLabel currentPlayerTurn, JLabel score,
			JButton[][] boardUILayout, int[][] currentBoardLayout,
			MouseListener player1, MouseListener player2, JFrame frame) {
		this.endTurn = endTurn;
		this.currentPlayerTurn = currentPlayerTurn;
		this.score = score;
		this.boardUILayout = boardUILayout;
		this.currentBoardLayout = currentBoardLayout;
		this.player1 = player1;
		this.player2 = player2;
		this.referenceFrame = frame;
	}

	public void mouseClicked(MouseEvent endTurnClicked) {
		if (endTurnClicked.getSource() == endTurn
				&& currentPlayerTurn.getText().equals(
						"PLAYER TURN: " + turn.getLastTurn())) {
			updateCurrentMovement();
			updateScore();
			changeText();
		}
	}
	
	public void mouseEntered(MouseEvent endTurnClicked){
		if (endTurnClicked.getSource() == endTurn){
			endTurn.setBackground(Color.RED);
		}
	}
	
	public void mouseExited(MouseEvent endTurnClicked){
		if (endTurnClicked.getSource() == endTurn){
			endTurn.setBackground(new Color(0, 100, 0));
		}
	}
	
	private void updateCurrentMovement() {
		if (currentPlayerTurn.getText().equals(player2Turn)) {
			addPlayer1Movement();
			removePlayer2Movement();
		} else if (currentPlayerTurn.getText().equals(player1Turn)) {
			addPlayer2Movement();
			removePlayer1Movement();
		}
	}

	private void addPlayer1Movement() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 1
						|| currentBoardLayout[i][j] == 3) {
					boardUILayout[i][j].addMouseListener(player1);

				}
			}
		}
	}

	private void addPlayer2Movement() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 2
						|| currentBoardLayout[i][j] == 4) {
					boardUILayout[i][j].addMouseListener(player2);
				}
			}
		}
	}

	private void removePlayer1Movement() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 1
						|| currentBoardLayout[i][j] == 3) {
					boardUILayout[i][j].removeMouseListener(player1);
				}
			}
		}
	}

	private void removePlayer2Movement() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 2
						|| currentBoardLayout[i][j] == 4) {
					boardUILayout[i][j].removeMouseListener(player2);
				}
			}
		}
	}

	private String player1Turn = "PLAYER TURN: 1";
	private String player2Turn = "PLAYER TURN: 2";

	private void changeText() {
		if (currentPlayerTurn.getText().equals(player1Turn)) {
			currentPlayerTurn.setText(player2Turn);
		} else {
			currentPlayerTurn.setText(player1Turn);
		}
	}

	private void updateScore() {
		int player1CurrentScore = 0;
		int player2CurrentScore = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 1
						|| currentBoardLayout[i][j] == 3) {
					player2CurrentScore += 1;
				}
				if (currentBoardLayout[i][j] == 2
						|| currentBoardLayout[i][j] == 4) {
					player1CurrentScore += 1;
				}
			}
		}
		if (player1CurrentScore == 0) {
			playerWinScreen("PLAYER 1 wins!");
			referenceFrame.dispose();
		} else if (player2CurrentScore == 0) {
			playerWinScreen("PLAYER 2 wins!");
			referenceFrame.dispose();
		}
		score.setText("SCORES - PLAYER 1: "
				+ Integer.toString(12 - player1CurrentScore) + " / PLAYER 2: "
				+ Integer.toString(12 - player2CurrentScore));
	}

	private JFrame winWindow = new JFrame("Victory");
	private JPanel formatWinPanel = new JPanel();
	private JButton restart = new JButton("RESTART");

	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");
	
	public void playerWinScreen(String message) {
		JLabel victoryIcon = new JLabel(new ImageIcon("victory.png"));
		JLabel winMessage = new JLabel(message, SwingConstants.CENTER);
		winMessage.setOpaque(true);
		winMessage.setBackground(new Color(0, 100, 0));
		winMessage.setForeground(Color.WHITE);
		victoryIcon.setOpaque(true);
		victoryIcon.setBackground(new Color(0, 100, 0));
		restart.setBackground(new Color(0, 100, 0));
		restart.setForeground(Color.WHITE);
		restart.addMouseListener(new RestartGame(restart, winWindow));
		formatWinPanel.add(winMessage, BorderLayout.NORTH);
		formatWinPanel.add(victoryIcon, BorderLayout.CENTER);
		formatWinPanel.add(restart, BorderLayout.SOUTH);
		formatWinPanel.setBackground(new Color(0, 100, 0));
		winWindow.add(formatWinPanel, BorderLayout.CENTER);
		winWindow.setSize(270, 270);
		winWindow.setIconImage(windowIcon);
		winWindow.setResizable(false);
		winWindow.setLocationRelativeTo(null);
		winWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winWindow.setVisible(true);
	}

}
