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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AITurnSystem extends MouseAdapter {

	private JButton endTurn = new JButton();
	private JLabel currentPlayerTurn = new JLabel();
	private JLabel score = new JLabel();
	private JButton[][] boardUILayout;
	private int[][] currentBoardLayout;
	private MouseListener player1;
	private int lastTurn;
	private LastTurn turn = new LastTurn(lastTurn);

	private JFrame frame = new JFrame();

	public AITurnSystem(JButton endTurn, JLabel currentPlayerTurn,
			JLabel score, JButton[][] boardUILayout,
			int[][] currentBoardLayout, MouseListener player1, JFrame frame) {
		this.endTurn = endTurn;
		this.currentPlayerTurn = currentPlayerTurn;
		this.score = score;
		this.boardUILayout = boardUILayout;
		this.currentBoardLayout = currentBoardLayout;
		this.player1 = player1;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent endTurnClicked) {
		if (endTurnClicked.getSource() == endTurn
				&& currentPlayerTurn.getText().equals(
						"PLAYER TURN: " + turn.getLastTurn())) {
			removePlayer1Movement();
			updateScore();
			configureTimer();
			displayPlayerWinningScreen();
		}
	}

	public void mouseEntered(MouseEvent endTurnClicked) {
		if (endTurnClicked.getSource() == endTurn) {
			endTurn.setBackground(Color.RED);
		}
	}

	public void mouseExited(MouseEvent endTurnClicked) {
		if (endTurnClicked.getSource() == endTurn) {
			endTurn.setBackground(new Color(0, 100, 0));
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

	private String player1Turn = "PLAYER TURN: 1";
	private String computerTurn = "PLAYER TURN: COMPUTER";

	private void changeText() {
		if (currentPlayerTurn.getText().equals(player1Turn)) {
			currentPlayerTurn.setText(computerTurn);
		} else if (currentPlayerTurn.getText().equals(computerTurn)
				&& computerCurrentScore != 0) {
			currentPlayerTurn.setText(player1Turn);
		}
	}

	private int player1CurrentScore = 0;
	private int computerCurrentScore = 0;

	private void updateScore() {
		player1CurrentScore = 0;
		computerCurrentScore = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 1
						|| currentBoardLayout[i][j] == 3) {
					computerCurrentScore += 1;
				}
				if (currentBoardLayout[i][j] == 2
						|| currentBoardLayout[i][j] == 4) {
					player1CurrentScore += 1;
				}
			}
		}

		score.setText("SCORES - PLAYER 1: "
				+ Integer.toString(12 - player1CurrentScore) + " / COMPUTER: "
				+ Integer.toString(12 - computerCurrentScore));
	}

	private void displayPlayerWinningScreen() {
		if (player1CurrentScore == 0) {
			configureWinScreen("PLAYER 1 wins!", "victory.png");
			frame.dispose();
		}
	}

	private void displayComputerWinningScreen() {
		if (computerCurrentScore == 0) {
			configureWinScreen("COMPUTER wins! ", "lose.jpg");
			frame.dispose();
		}
	}

	private JFrame winWindow = new JFrame("Victory");
	private JPanel formatWinPanel = new JPanel();
	private JButton restartButton = new JButton("RESTART");

	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");

	public void configureWinScreen(String message, String icon) {
		JLabel victoryIcon = new JLabel(new ImageIcon(icon));
		JLabel winMessage = new JLabel(message, SwingConstants.CENTER);
		winMessage.setOpaque(true);
		winMessage.setBackground(new Color(0, 100, 0));
		winMessage.setForeground(Color.WHITE);
		victoryIcon.setOpaque(true);
		victoryIcon.setBackground(new Color(0, 100, 0));
		restartButton.setBackground(new Color(0, 100, 0));
		restartButton.setForeground(Color.WHITE);
		restartButton
				.addMouseListener(new RestartGame(restartButton, winWindow));
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
	}

	private Random randomNumber = new Random();

	private void computerMove() {
		int randomNum = 0;
		int index = 0;
		int[] row = new int[24];
		int[] column = new int[24];
		int[] direction = new int[24];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 2) {
					if (i != 0 && j != 0) {
						if (currentBoardLayout[i - 1][j - 1] == 0) {
							row[index] = i;
							column[index] = j;
							direction[index] = 1;
							index++;
						} else if (i != 1 && j != 1) {
							if ((currentBoardLayout[i - 1][j - 1] == 1 || currentBoardLayout[i - 1][j - 1] == 3)
									&& currentBoardLayout[i - 2][j - 2] == 0) {
								row[index] = i;
								column[index] = j;
								direction[index] = 1;
								index++;
							}
						}
					}
					if (i != 0 && j != 7) {
						if (currentBoardLayout[i - 1][j + 1] == 0) {
							row[index] = i;
							column[index] = j;
							direction[index] = 2;
							index++;
						} else if (i != 1 && j != 6) {
							if ((currentBoardLayout[i - 1][j + 1] == 1 || currentBoardLayout[i - 1][j + 1] == 3)
									&& currentBoardLayout[i - 2][j + 2] == 0) {
								row[index] = i;
								column[index] = j;
								direction[index] = 2;
								index++;
							}
						}
					}
				} else if (currentBoardLayout[i][j] == 4) {
					if (i != 0 && j != 0) {
						if (currentBoardLayout[i - 1][j - 1] == 0) {
							row[index] = i;
							column[index] = j;
							direction[index] = 3;
							index++;
						} else if (i != 1 && j != 1) {
							if ((currentBoardLayout[i - 1][j - 1] == 1 || currentBoardLayout[i - 1][j - 1] == 3)
									&& currentBoardLayout[i - 2][j - 2] == 0) {
								row[index] = i;
								column[index] = j;
								direction[index] = 3;
								index++;
							}
						}
					}
					if (i != 0 && j != 7) {
						if (currentBoardLayout[i - 1][j + 1] == 0) {
							row[index] = i;
							column[index] = j;
							direction[index] = 4;
							index++;
						} else if (i != 1 && j != 6) {
							if ((currentBoardLayout[i - 1][j + 1] == 1 || currentBoardLayout[i - 1][j + 1] == 3)
									&& currentBoardLayout[i - 2][j + 2] == 0) {
								row[index] = i;
								column[index] = j;
								direction[index] = 4;
								index++;
							}
						}
					}
					if (i != 7 && j != 0) {
						if (currentBoardLayout[i + 1][j - 1] == 0) {
							row[index] = i;
							column[index] = j;
							direction[index] = 5;
							index++;
						} else if (i != 6 && j != 1) {
							if ((currentBoardLayout[i + 1][j - 1] == 1 || currentBoardLayout[i + 1][j - 1] == 3)
									&& currentBoardLayout[i + 2][j - 2] == 0) {
								row[index] = i;
								column[index] = j;
								direction[index] = 5;
								index++;
							}
						}
					}
					if (i != 7 && j != 7) {
						if (currentBoardLayout[i + 1][j + 1] == 0) {
							row[index] = i;
							column[index] = j;
							direction[index] = 6;
							index++;
						} else if (i != 6 && j != 6) {
							if ((currentBoardLayout[i + 1][j + 1] == 1 || currentBoardLayout[i + 1][j + 1] == 3)
									&& currentBoardLayout[i + 2][j + 2] == 0) {
								row[index] = i;
								column[index] = j;
								direction[index] = 6;
								index++;
							}
						}
					}
				}
			}
		}

		if (index != 0) {
			randomNum = randomNumber.nextInt(index);
		}
		
		if (direction[randomNum] == 1) {
			moveTOPLEFT(row[randomNum], column[randomNum]);
		} else if (direction[randomNum] == 2) {
			moveTOPRIGHT(row[randomNum], column[randomNum]);
		} else if (direction[randomNum] == 3) {
			moveKINGTOPLEFT(row[randomNum], column[randomNum]);
		} else if (direction[randomNum] == 4) {
			moveKINGTOPRIGHT(row[randomNum], column[randomNum]);
		} else if (direction[randomNum] == 5) {
			moveKINGBOTTOMLEFT(row[randomNum], column[randomNum]);
		} else if (direction[randomNum] == 6) {
			moveKINGBOTTOMRIGHT(row[randomNum], column[randomNum]);
		}
	}

	private long seconds = 2;

	private void configureTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				computerMove();
				updateScore();
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						displayComputerWinningScreen();
					}
				}, seconds * 1000);
				turn.setLastTurn(2);
				addPlayer1Movement();
				changeText();
			}

		}, seconds * 1000);
		changeText();
	}

	private void imageMovement(JButton initial, JButton end) {
		ImageIcon hold = (ImageIcon) initial.getIcon();
		initial.setIcon(null);
		end.setIcon(hold);
	}

	private void moveTOPLEFT(int i, int j) {
		if ((i != 0 && j != 0) && turn.getLastTurn() != 2) {
			if (currentBoardLayout[i - 1][j - 1] == 0
					&& turn.getLastTurn() != 2) {
				turn.setLastTurn(2);
				layoutSingleMove("TOPLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 1][j - 1]);
				becomeKing();
			} else if (j > 1
					&& i > 1
					&& ((currentBoardLayout[i - 1][j - 1] == 1 || currentBoardLayout[i - 1][j - 1] == 3) && currentBoardLayout[i - 2][j - 2] == 0)) {
				turn.setLastTurn(2);
				layoutJump("TOPLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 2][j - 2]);
				capturePiece(boardUILayout[i - 1][j - 1]);
				becomeKing();
			}
		}
	}

	private void moveTOPRIGHT(int i, int j) {
		if ((i != 0 && j != 7) && turn.getLastTurn() != 2) {
			if (currentBoardLayout[i - 1][j + 1] == 0
					&& turn.getLastTurn() != 2) {
				turn.setLastTurn(2);
				layoutSingleMove("TOPRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 1][j + 1]);
				becomeKing();
			} else if (j < 6
					&& i > 1
					&& ((currentBoardLayout[i - 1][j + 1] == 1 || currentBoardLayout[i - 1][j + 1] == 3) && currentBoardLayout[i - 2][j + 2] == 0)) {
				turn.setLastTurn(2);
				layoutJump("TOPRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 2][j + 2]);
				capturePiece(boardUILayout[i - 1][j + 1]);
				becomeKing();
			}
		}
	}

	private void moveKINGTOPLEFT(int i, int j) {
		if ((i != 0 && j != 0) && turn.getLastTurn() != 2) {

			if (currentBoardLayout[i - 1][j - 1] == 0
					&& turn.getLastTurn() != 2) {
				layoutKINGSingleMove("TOPLEFT", i, j);
				turn.setLastTurn(2);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 1][j - 1]);

			} else if (((currentBoardLayout[i - 1][j - 1] == 1 || currentBoardLayout[i - 1][j - 1] == 3) && currentBoardLayout[i - 2][j - 2] == 0)
					&& (i >= 3 && j >= 3)) {
				turn.setLastTurn(2);
				layoutKINGJump("TOPLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 2][j - 2]);
				capturePiece(boardUILayout[i - 1][j - 1]);

				boardUILayout[i - 2][j - 2].addMouseListener(this);
			} else {

			}

		} else {

		}
	}

	private void moveKINGTOPRIGHT(int i, int j) {
		if ((i != 0 && j != 7) && turn.getLastTurn() != 2) {
			if (currentBoardLayout[i - 1][j + 1] == 0
					&& turn.getLastTurn() != 2) {
				turn.setLastTurn(2);
				layoutKINGSingleMove("TOPRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 1][j + 1]);

			} else if (((currentBoardLayout[i - 1][j + 1] == 1 || currentBoardLayout[i - 1][j + 1] == 3) && currentBoardLayout[i - 2][j + 2] == 0)
					&& (i <= 5 && j >= 3)) {
				turn.setLastTurn(2);
				layoutKINGJump("TOPRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 2][j + 2]);
				capturePiece(boardUILayout[i - 1][j + 1]);

			} else {

			}
		} else {

		}
	}

	private void moveKINGBOTTOMLEFT(int i, int j) {
		if ((i != 7 && j != 0) && turn.getLastTurn() != 2) {
			if (currentBoardLayout[i + 1][j - 1] == 0
					&& turn.getLastTurn() != 2) {
				turn.setLastTurn(2);
				layoutKINGSingleMove("BOTTOMLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 1][j - 1]);

			} else if (((currentBoardLayout[i + 1][j - 1] == 1 || currentBoardLayout[i + 1][j - 1] == 3) && currentBoardLayout[i + 2][j - 2] == 0)
					&& (i >= 3 && j <= 5)) {
				turn.setLastTurn(2);
				layoutKINGJump("BOTTOMLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 2][j - 2]);
				capturePiece(boardUILayout[i + 1][j - 1]);

			} else {

			}
		} else {

		}
	}

	private void moveKINGBOTTOMRIGHT(int i, int j) {
		if ((i != 7 && j != 7) && turn.getLastTurn() != 2) {
			if (currentBoardLayout[i + 1][j + 1] == 0
					&& turn.getLastTurn() != 2) {
				turn.setLastTurn(2);
				layoutKINGSingleMove("BOTTOMRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 1][j + 1]);

			} else if (((currentBoardLayout[i + 1][j + 1] == 1 || currentBoardLayout[i + 1][j + 1] == 3) && currentBoardLayout[i + 2][j + 2] == 0)
					&& (i <= 5 && j <= 5)) {
				layoutKINGJump("BOTTOMRIGHT", i, j);
				turn.setLastTurn(2);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 2][j + 2]);
				capturePiece(boardUILayout[i + 1][j + 1]);

			} else {

			}
		} else {

		}
	}

	public void layoutSingleMove(String direction, int i, int j) {
		switch (direction) {
		case "TOPLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 1][j - 1] = 2;
			break;
		case "TOPRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 1][j + 1] = 2;
			break;
		}
	}

	public void layoutKINGSingleMove(String direction, int i, int j) {
		switch (direction) {
		case "TOPLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 1][j - 1] = 4;
			break;
		case "TOPRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 1][j + 1] = 4;
			break;

		case "BOTTOMLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 1][j - 1] = 4;
			break;

		case "BOTTOMRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 1][j + 1] = 4;
			break;
		}
	}

	public void layoutKINGJump(String direction, int i, int j) {
		switch (direction) {
		case "TOPLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 2][j - 2] = 4;
			currentBoardLayout[i - 1][j - 1] = 0;
			break;
		case "TOPRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 2][j + 2] = 4;
			currentBoardLayout[i - 1][j + 1] = 0;
			break;

		case "BOTTOMLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 2][j - 2] = 4;
			currentBoardLayout[i + 1][j - 1] = 0;
			break;

		case "BOTTOMRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 2][j + 2] = 4;
			currentBoardLayout[i + 1][j + 1] = 0;
			break;
		}
	}

	public void layoutJump(String direction, int i, int j) {
		switch (direction) {
		case "TOPLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 2][j - 2] = 2;
			currentBoardLayout[i - 1][j - 1] = 0;
			break;
		case "TOPRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 2][j + 2] = 2;
			currentBoardLayout[i - 1][j + 1] = 0;
			break;
		}
	}

	private void becomeKing() {
		for (int j = 0; j < 8; j++) {
			if (currentBoardLayout[0][j] == 2) {
				currentBoardLayout[0][j] = 4;
				boardUILayout[0][j].setIcon(new ImageIcon(
						"Circle_Yellow_King.png"));

			}
		}
	}

	private void capturePiece(JButton piece) {
		piece.setIcon(null);
	}

}
