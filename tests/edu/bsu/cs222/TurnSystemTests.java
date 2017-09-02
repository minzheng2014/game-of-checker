package edu.bsu.cs222;

import static org.junit.Assert.assertNotNull;

import javax.swing.JLabel;

import org.junit.Test;

public class TurnSystemTests {

		@Test
		public void turnSystemTest() {
			JLabel score = upDateScore();
			assertNotNull(score);
		}
		private int[][] layout = { { 1, 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 2, 0, 2, 0, 2, 0, 2 }, { 2, 0, 2, 0, 2, 0, 2, 0 },
				{ 0, 2, 0, 2, 0, 2, 0, 2 } };
		private JLabel score = new JLabel();
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
		
	}
