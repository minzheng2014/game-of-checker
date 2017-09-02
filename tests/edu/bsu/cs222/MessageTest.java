package edu.bsu.cs222;

import static org.junit.Assert.assertNotNull;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

public class MessageTest {
	@Test
	public void BoardTest() {
		JLabel error = errorMessage();
		assertNotNull(error);
	}
	public JLabel errorMessage() {
		JFrame error = new JFrame("ERROR");
		JPanel formatError = new JPanel();
		JLabel message = new JLabel("You cannot move any further");
		formatError.add(message);
		error.add(formatError, BorderLayout.CENTER);
		error.setSize(300, 100);
		error.setVisible(true);
		return message;
	}
}
