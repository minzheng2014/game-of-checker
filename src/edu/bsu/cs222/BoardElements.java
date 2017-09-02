package edu.bsu.cs222;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardElements {

	private int[][] initialCheckerPieceLayout = { { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 }, { 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 } };

	private int[][] boardColorLayout = { { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 } };

	private JButton[][] boardUILayout = new JButton[8][8];

	private JPanel checkersBoardPanel = new JPanel(new GridLayout(8, 8, 1, 1));
	private JPanel configureCheckersBoardPanel = new JPanel(new GridBagLayout());
	private Color darkGreen = new Color(0, 100, 0);

	public JPanel configureBoard() {
		setBoardPieces();
		setBoardColors();
		setCheckersMovement();
		checkersBoardPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0,
				15));
		configureCheckersBoardPanel.add(checkersBoardPanel);
		configureCheckersBoardPanel.setBackground(darkGreen);
		checkersBoardPanel.setBackground(darkGreen);
		checkersBoardPanel.setPreferredSize(new Dimension(500, 500));
		return configureCheckersBoardPanel;
	}

	private ImageIcon player1Piece = new ImageIcon("Circle_Grey.png",
			"Circle_Grey.png");
	private ImageIcon player2Piece = new ImageIcon("Circle_Yellow.png",
			"Circle_Yellow.png");

	private void setBoardPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (initialCheckerPieceLayout[i][j] == 1) {
					boardUILayout[i][j] = new JButton(player1Piece);
				} else if (initialCheckerPieceLayout[i][j] == 2) {
					boardUILayout[i][j] = new JButton(player2Piece);

				} else if (initialCheckerPieceLayout[i][j] == 0) {
					boardUILayout[i][j] = new JButton();
				}
				checkersBoardPanel.add(boardUILayout[i][j]);
			}
		}
	}

	private void setBoardColors() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardColorLayout[i][j] == 0) {
					boardUILayout[i][j].setBackground(Color.RED);
				}
				if (boardColorLayout[i][j] == 1) {
					boardUILayout[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}

	private void setCheckersMovement() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (initialCheckerPieceLayout[i][j] == 1) {
					boardUILayout[i][j].addMouseListener(player1);
				}
			}
		}
	}

	private Player1Movement player1 = new Player1Movement(boardUILayout,
			initialCheckerPieceLayout);
	private Player2Movement player2 = new Player2Movement(boardUILayout,
			initialCheckerPieceLayout);

	public JButton[][] getBoardUILayout() {
		return boardUILayout;
	}

	public int[][] getInitialCheckerPieceLayout() {
		return initialCheckerPieceLayout;
	}

	public Player1Movement getPlayer1() {
		return player1;
	}

	public Player2Movement getPlayer2() {
		return player2;
	}
}
