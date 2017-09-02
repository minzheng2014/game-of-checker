package edu.bsu.cs222;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardElementsTests {

	private JPanel panel = new JPanel(new GridLayout(8, 8, 1, 1));

	private int[][] layout = { { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 }, { 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 } };

	private JButton[][] map = new JButton[8][8];

	private ImageIcon player1Piece = new ImageIcon("Circle_Grey.png",
			"Circle_Grey.png");
	private ImageIcon player2Piece = new ImageIcon("Circle_Yellow.png",
			"Circle_Yellow.png");

	@Test
	public void BoardTest() {
		JPanel board = board();
		assertNotNull(board);
	}

	private JPanel board() {
		JPanel panel = new JPanel(new GridLayout(8, 8, 1, 1));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				panel.add(new JButton(new ImageIcon("circle-xl.png")));
			}
		}
		panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
		return panel;
	}

	@Test
	public void boardColorTest() {
		JButton color = new JButton();
		setBoardColors(color);
		boolean result;
		if (color.getBackground() == Color.RED) {
			result = true;
		} else {
			result = false;
		}
		assertTrue(result);
	}

	private void setBoardColors(JButton color) {
		color.setBackground(Color.RED);
	}

	@Test
	public void boardPiecesTest() {
		boolean result;
		setBoardPieces();
		if (map[0][0].getIcon().equals(player1Piece)) {
			result = true;
		} else {
			result = false;
		}
		assertTrue(result);
	}

	private void setBoardPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (layout[i][j] == 1) {
					map[i][j] = new JButton(player1Piece);
				} else if (layout[i][j] == 2) {
					map[i][j] = new JButton(player2Piece);

				} else if (layout[i][j] == 0) {
					map[i][j] = new JButton();
				}
				panel.add(map[i][j]);
			}
		}
	}

	@Test
	public void checkersMovementTest() {
		boolean result = false;
		JButton practiceButton = new JButton();
		setCheckersMovement(practiceButton);
		if (practiceButton != null) {
			result = true;
		}
		assertTrue(result);
	}

	private void setCheckersMovement(JButton practice) {
		practice.addMouseListener(new Player1Movement(map, layout	));
	}

	@Test
	public void testboardUILayout(){
		boardUILayout = getBoardUILayout();
		assertNotNull(boardUILayout);
	}
	
	public JButton[][] getBoardUILayout() {
		return boardUILayout;
	}
	private JButton[][] boardUILayout = new JButton[8][8];
	
	@Test
	public void testCheckerLayout(){
		initialCheckerPieceLayout = getInitialCheckerPieceLayout();
		assertNotNull(initialCheckerPieceLayout);
		
	}
	private int[][] initialCheckerPieceLayout = { { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 }, { 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 2, 0, 2, 0, 2, 0, 2 } };
	public int[][] getInitialCheckerPieceLayout() {
		return initialCheckerPieceLayout;
	}
	
	@Test
	public void player1Test(){
		player1 = getPlayer1();
		assertNull(player1);
	}
	public Player1Movement getPlayer1() {
		return player1;
		
	}
	Player1Movement player1 ;
	
	@Test
	public void player2Test(){
		player2 = getPlayer2();
		assertNull(player2);
	}
	public Player1Movement getPlayer2() {
		return player2;
		
	}
	Player1Movement player2;
}
