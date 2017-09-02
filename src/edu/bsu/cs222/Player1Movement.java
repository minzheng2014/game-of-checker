package edu.bsu.cs222;

import java.awt.event.*;

import javax.swing.*;

public class Player1Movement extends MouseAdapter {

	private JButton[][] boardUILayout = new JButton[8][8];
	private int[][] currentBoardLayout = new int[8][8];
	private int lastTurn;
	public LastTurn turn = new LastTurn(lastTurn);

	public Player1Movement(JButton[][] boardUILayout, int[][] currentBoardLayout) {
		this.boardUILayout = boardUILayout;
		this.currentBoardLayout = currentBoardLayout;
	}

	public Messages message = new Messages();

	public void mouseClicked(MouseEvent checkerAreaClicked) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (checkerAreaClicked.getSource() == boardUILayout[i][j]
						&& currentBoardLayout[i][j] == 1) {
					if (checkerAreaClicked.getY() < TOP(i, j)
							&& checkerAreaClicked.getX() < LEFT(i, j)) {
						message.errorMessage();
					} else if (checkerAreaClicked.getY() < TOP(i, j)
							&& checkerAreaClicked.getX() > RIGHT(i, j)) {
						message.errorMessage();
					} else if (checkerAreaClicked.getY() > BOTTOM(i, j)
							&& checkerAreaClicked.getX() < LEFT(i, j)) {
						moveBOTTOMLEFT(i, j);
					} else if (checkerAreaClicked.getY() > BOTTOM(i, j)
							&& checkerAreaClicked.getX() > RIGHT(i, j)) {
						moveBOTTOMRIGHT(i, j);
					}
				} else if (checkerAreaClicked.getSource() == boardUILayout[i][j]
						&& currentBoardLayout[i][j] == 3) {
					if (checkerAreaClicked.getY() < TOP(i, j)
							&& checkerAreaClicked.getX() < LEFT(i, j)) {
						moveKINGTOPLEFT(i, j);
					} else if (checkerAreaClicked.getY() < TOP(i, j)
							&& checkerAreaClicked.getX() > RIGHT(i, j)) {
						moveKINGTOPRIGHT(i, j);
					} else if (checkerAreaClicked.getY() > BOTTOM(i, j)
							&& checkerAreaClicked.getX() < LEFT(i, j)) {
						moveKINGBOTTOMLEFT(i, j);
					} else if (checkerAreaClicked.getY() > BOTTOM(i, j)
							&& checkerAreaClicked.getX() > RIGHT(i, j)) {
						moveKINGBOTTOMRIGHT(i, j);
					}
				}
			}
		}
	}

	private int TOP(int i, int j) {
		int top = boardUILayout[i][j].getHeight() / 2;
		return top;
	}

	private int BOTTOM(int i, int j) {
		int bottom = boardUILayout[i][j].getHeight() * 1 / 2;
		return bottom;
	}

	private int RIGHT(int i, int j) {
		int right = boardUILayout[i][j].getWidth() * 1 / 2;
		return right;
	}

	private int LEFT(int i, int j) {
		int left = boardUILayout[i][j].getWidth() / 2;
		return left;
	}

	private void moveBOTTOMLEFT(int i, int j) {
		if (i < 7 && j > 0) {
			if (currentBoardLayout[i + 1][j - 1] == 0
					&& turn.getLastTurn() != 1) {
				turn.setLastTurn(1);
				layoutSingleMove("BOTTOMLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 1][j - 1]);
				becomeKing();
				stopMovement();
			} else if ((i < 6 && j > 1)
					&& (currentBoardLayout[i + 1][j - 1] == 2 || currentBoardLayout[i + 1][j - 1] == 4)
					&& currentBoardLayout[i + 2][j - 2] == 0) {
				turn.setLastTurn(1);
				layoutJump("BOTTOMLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 2][j - 2]);
				capturePiece(boardUILayout[i + 1][j - 1]);
				stopMovement();
				boardUILayout[i + 2][j - 2].addMouseListener(this);
				becomeKing();
			} else {
				message.errorMessage();
			}
		} else {
			message.errorMessage();
		}
	}

	private void moveBOTTOMRIGHT(int i, int j) {
		if (i < 7 && j < 7) {
			if (currentBoardLayout[i + 1][j + 1] == 0
					&& turn.getLastTurn() != 1) {
				turn.setLastTurn(1);
				layoutSingleMove("BOTTOMRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 1][j + 1]);
				becomeKing();
				stopMovement();
			} else if ((i < 6 && j < 6)
					&& (currentBoardLayout[i + 1][j + 1] == 2 || currentBoardLayout[i + 1][j + 1] == 4)
					&& currentBoardLayout[i + 2][j + 2] == 0) {
				layoutJump("BOTTOMRIGHT", i, j);
				turn.setLastTurn(1);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 2][j + 2]);
				capturePiece(boardUILayout[i + 1][j + 1]);
				stopMovement();
				boardUILayout[i + 2][j + 2].addMouseListener(this);
				becomeKing();
			} else {
				message.errorMessage();
			}
		} else {
			message.errorMessage();
		}
	}

	private void moveKINGTOPLEFT(int i, int j) {
		if (i != 0 && j != 0) {
			if (currentBoardLayout[i - 1][j - 1] == 0
					&& turn.getLastTurn() != 1) {
				turn.setLastTurn(1);
				layoutKINGSingleMove("TOPLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 1][j - 1]);
				stopMovement();
			} else if ((currentBoardLayout[i - 1][j - 1] == 2 || currentBoardLayout[i - 1][j - 1] == 4)
					&& currentBoardLayout[i - 2][j - 2] == 0) {
				layoutKINGJump("TOPLEFT", i, j);
				turn.setLastTurn(1);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 2][j - 2]);
				capturePiece(boardUILayout[i - 1][j - 1]);
				stopMovement();
				boardUILayout[i - 2][j - 2].addMouseListener(this);
			} else {
				message.errorMessage();
			}
		} else {
			message.errorMessage();
		}
	}

	private void moveKINGTOPRIGHT(int i, int j) {
		if (i != 0 && j != 7) {
			if (currentBoardLayout[i - 1][j + 1] == 0
					&& turn.getLastTurn() != 1) {
				layoutKINGSingleMove("TOPRIGHT", i, j);
				turn.setLastTurn(1);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 1][j + 1]);
				stopMovement();

			} else if ((currentBoardLayout[i - 1][j + 1] == 2 || currentBoardLayout[i - 1][j + 1] == 4)
					&& currentBoardLayout[i - 2][j + 2] == 0) {
				layoutKINGJump("TOPRIGHT", i, j);
				turn.setLastTurn(1);
				imageMovement(boardUILayout[i][j], boardUILayout[i - 2][j + 2]);
				capturePiece(boardUILayout[i - 1][j + 1]);
				stopMovement();
				boardUILayout[i - 2][j + 2].addMouseListener(this);
			} else {
				message.errorMessage();
			}
		} else {
			message.errorMessage();
		}
	}

	private void moveKINGBOTTOMLEFT(int i, int j) {
		if (i != 7 && j != 0) {
			if (currentBoardLayout[i + 1][j - 1] == 0
					&& turn.getLastTurn() != 1) {
				layoutKINGSingleMove("BOTTOMLEFT", i, j);
				turn.setLastTurn(1);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 1][j - 1]);
				stopMovement();

			} else if ((currentBoardLayout[i + 1][j - 1] == 2 || currentBoardLayout[i + 1][j - 1] == 4)
					&& currentBoardLayout[i + 2][j - 2] == 0) {
				turn.setLastTurn(1);
				layoutKINGJump("BOTTOMLEFT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 2][j - 2]);
				capturePiece(boardUILayout[i + 1][j - 1]);
				stopMovement();
				boardUILayout[i + 2][j - 2].addMouseListener(this);
			} else {
				message.errorMessage();
			}
		} else {
			message.errorMessage();
		}
	}

	private void moveKINGBOTTOMRIGHT(int i, int j) {
		if (i != 7 && j != 7) {
			if (currentBoardLayout[i + 1][j + 1] == 0
					&& turn.getLastTurn() != 1) {
				turn.setLastTurn(1);
				layoutKINGSingleMove("BOTTOMRIGHT", i, j);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 1][j + 1]);
				stopMovement();

			} else if ((currentBoardLayout[i + 1][j + 1] == 2 || currentBoardLayout[i + 1][j + 1] == 4)
					&& currentBoardLayout[i + 2][j + 2] == 0) {
				layoutKINGJump("BOTTOMRIGHT", i, j);
				turn.setLastTurn(1);
				imageMovement(boardUILayout[i][j], boardUILayout[i + 2][j + 2]);
				capturePiece(boardUILayout[i + 1][j + 1]);
				stopMovement();
				boardUILayout[i + 2][j + 2].addMouseListener(this);
			} else {
				message.errorMessage();
			}
		} else {
			message.errorMessage();
		}
	}

	public void layoutKINGSingleMove(String direction, int i, int j) {
		switch (direction) {
		case "TOPLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 1][j - 1] = 3;
			break;
		case "TOPRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 1][j + 1] = 3;
			break;

		case "BOTTOMLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 1][j - 1] = 3;
			break;

		case "BOTTOMRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 1][j + 1] = 3;
			break;
		}
	}

	public void layoutKINGJump(String direction, int i, int j) {
		switch (direction) {
		case "TOPLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 2][j - 2] = 3;
			currentBoardLayout[i - 1][j - 1] = 0;
			break;
		case "TOPRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i - 2][j + 2] = 3;
			currentBoardLayout[i - 1][j + 1] = 0;
			break;

		case "BOTTOMLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 2][j - 2] = 3;
			currentBoardLayout[i + 1][j - 1] = 0;
			break;

		case "BOTTOMRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 2][j + 2] = 3;
			currentBoardLayout[i + 1][j + 1] = 0;
			break;
		}
	}

	public void layoutSingleMove(String direction, int i, int j) {
		switch (direction) {
		case "BOTTOMLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 1][j - 1] = 1;
			break;

		case "BOTTOMRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 1][j + 1] = 1;
			break;
		}
	}

	public void layoutJump(String direction, int i, int j) {
		switch (direction) {
		case "BOTTOMLEFT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 2][j - 2] = 1;
			currentBoardLayout[i + 1][j - 1] = 0;
			break;

		case "BOTTOMRIGHT":
			currentBoardLayout[i][j] = 0;
			currentBoardLayout[i + 2][j + 2] = 1;
			currentBoardLayout[i + 1][j + 1] = 0;
			break;
		}
	}

	private void imageMovement(JButton initial, JButton end) {
		ImageIcon hold = (ImageIcon) initial.getIcon();
		initial.setIcon(null);
		end.setIcon(hold);
	}

	private void capturePiece(JButton piece) {
		piece.setIcon(null);
	}

	private void becomeKing() {
		for (int j = 0; j < 8; j++) {
			if (currentBoardLayout[7][j] == 1) {
				currentBoardLayout[7][j] = 3;
				boardUILayout[7][j].setIcon(new ImageIcon(
						"Circle_Grey_King.png"));
				stopMovement();
			}
		}
	}

	private void stopMovement() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoardLayout[i][j] == 1
						|| currentBoardLayout[i][j] == 3) {
					boardUILayout[i][j].removeMouseListener(this);
				}
			}
		}
	}
}