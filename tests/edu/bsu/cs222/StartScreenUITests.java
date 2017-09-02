package edu.bsu.cs222;

import static org.junit.Assert.assertNotNull;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

public class StartScreenUITests {
	@Test
	public void UITest() {
		JFrame window = configureWindow();
		assertNotNull(window);
	}

	private JFrame window;
	private String name = "CHECKERS";
	private int WIDTH = 600;
	private int HEIGHT = 600;

	JPanel board = new BoardElements().configureBoard();
	private JFrame configureWindow() {
		window = new JFrame(name);
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(board, BorderLayout.CENTER);
		window.setResizable(false);
		window.setVisible(true);
		return window;
	}
}
