package edu.bsu.cs222;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.junit.Test;


public class PvcUITests {
	@Test
	public void configureWindowTest() {
		JFrame test = configureWindow();
		assertNotNull(test);
	}
	
	@Test
	public void configureTurnSystemTest(){
		JPanel testPanel = configureTurnSystem();
		assertNotNull(testPanel);
	}
	
	@Test
	public void configureTurnsTest(){
		AITurnSystem testSystem = turns();
		assertNotNull(testSystem);
	}
	
	private String checkersWindowTitle = "CHECKERS";
	private int windowWidth = 800;
	private int windowHeight = 600;
	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");
	private JFrame checkersWindow;
	private JFrame configureWindow() {
		checkersWindow = new JFrame(checkersWindowTitle);
		checkersWindow.setIconImage(windowIcon);
		checkersWindow.setSize(windowWidth, windowHeight);
		checkersWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkersWindow.setLocationRelativeTo(null);
		checkersWindow.setResizable(false);
		checkersWindow.setVisible(true);
		return checkersWindow;
	}
	
	JPanel turnSystemPanel = new JPanel(new GridLayout());
	JLabel playerTurn = new JLabel("PLAYER TURN: 1", SwingConstants.CENTER);
	JLabel playerScore = new JLabel("SCORES - PLAYER 1: 0 / COMPUTER: 0", SwingConstants.CENTER);
	JButton endTurn = new JButton("END TURN");
	BoardElements boardElements = new BoardElements();
	
	public AITurnSystem turns() {
		return new AITurnSystem(endTurn, playerTurn, playerScore, boardElements.getBoardUILayout(),
				boardElements.getInitialCheckerPieceLayout(), boardElements.getPlayer1(), checkersWindow);
	}
	
	public JPanel configureTurnSystem() {
		playerTurn.setForeground(Color.WHITE);
		playerScore.setForeground(Color.WHITE);
		endTurn.setBackground(new Color(0, 100, 0));
		endTurn.setForeground(Color.WHITE);
		endTurn.addMouseListener(turns());
		turnSystemPanel.add(playerTurn);
		turnSystemPanel.add(playerScore);
		turnSystemPanel.add(endTurn);
		turnSystemPanel.setBackground(new Color(0, 100, 0));
		return turnSystemPanel;
	}
}
