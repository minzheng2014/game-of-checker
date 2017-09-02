package edu.bsu.cs222;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class PvpUI extends MouseAdapter {

	private JFrame checkersWindow = new JFrame();
	private String checkersWindowTitle = "CHECKERS: Player Vs Player";
	private int windowWidth = 800;
	private int windowHeight = 650;
	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");

	private JButton playerVsPlayer;
	private JFrame startScreenWindow;
	private Color darkGreen = new Color(0,100, 0);

	public PvpUI(JButton playerVsPlayer, JFrame startScreenWindow) {
		this.playerVsPlayer = playerVsPlayer;
		this.startScreenWindow = startScreenWindow;
	}

	public void mouseClicked(MouseEvent playerVsPlayerClicked) {
		if (playerVsPlayerClicked.getSource() == playerVsPlayer) {
			configureWindow();
			startScreenWindow.dispose();
		}
	}
	
	public void mouseEntered(MouseEvent playerVsPlayerClicked){
		if (playerVsPlayerClicked.getSource() == playerVsPlayer){
			playerVsPlayer.setBackground(Color.RED);
		}
	}
	
	public void mouseExited(MouseEvent playerVsPlayerClicked){
		if (playerVsPlayerClicked.getSource() == playerVsPlayer){
			playerVsPlayer.setBackground(darkGreen);
		}
	}

	private BoardElements boardElements = new BoardElements();
	private JPanel checkersBoard = boardElements.configureBoard();

	private void configureWindow() {
		checkersWindow = new JFrame(checkersWindowTitle);
		checkersWindow.setIconImage(windowIcon);
		checkersWindow.setSize(windowWidth, windowHeight);
		checkersWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkersWindow.add(configureTurnSystem(), BorderLayout.NORTH);
		checkersWindow.add(checkersBoard, BorderLayout.CENTER);
		checkersWindow.add(configureCloseOperations(), BorderLayout.SOUTH);
		checkersWindow.setJMenuBar(new InfoMenu().configureMenu());
		checkersWindow.setLocationRelativeTo(null);
		checkersWindow.setResizable(false);
		checkersWindow.setVisible(true);
	}

	private JButton restartButton = new JButton("RESTART GAME");
	private JPanel closeOperations = new JPanel();

	private JPanel configureCloseOperations() {
		restartButton.setBackground(darkGreen);
		restartButton.setForeground(Color.WHITE);
		restartButton.addMouseListener(new RestartGame(restartButton,
				checkersWindow));
		closeOperations.add(restartButton);
		closeOperations.setBackground(darkGreen);
		return closeOperations;
	}

	public JPanel turnSystemPanel = new JPanel(new GridLayout());
	public JLabel playerTurn = new JLabel("PLAYER TURN: 1", SwingConstants.CENTER);
	public JLabel playerScore = new JLabel("SCORES - PLAYER 1: 0 / PLAYER 2: 0",
			SwingConstants.CENTER);
	public JButton endTurn = new JButton("END TURN");

	public JPanel configureTurnSystem() {
		playerTurn.setForeground(Color.WHITE);
		playerScore.setForeground(Color.WHITE);
		endTurn.setBackground(darkGreen);
		endTurn.setForeground(Color.WHITE);
		endTurn.addMouseListener(instantiateTurnSystem());
		turnSystemPanel.add(playerTurn);
		turnSystemPanel.add(playerScore);
		turnSystemPanel.add(endTurn);
		turnSystemPanel.setBackground(darkGreen);
		return turnSystemPanel;
	}

	public TurnSystem instantiateTurnSystem() {
		return new TurnSystem(endTurn, playerTurn, playerScore,
				boardElements.getBoardUILayout(),
				boardElements.getInitialCheckerPieceLayout(),
				boardElements.getPlayer1(), boardElements.getPlayer2(),
				checkersWindow);
	}

}
