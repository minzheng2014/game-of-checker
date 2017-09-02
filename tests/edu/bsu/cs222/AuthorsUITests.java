package edu.bsu.cs222;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import org.junit.Test;

public class AuthorsUITests {

	
	JMenuItem authors = new JMenuItem();
	JFrame authorsPanel = new JFrame();
	JLabel authorNames = new JLabel(
			"<html>Authors:<br>Min Zheng<br>Jiayue Wu<br>Daniel Cox<br>Dylon Price<br>Copyright fpteam1</html>",
			SwingConstants.CENTER);

	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");

	@SuppressWarnings("static-access")
	public JFrame configureAuthorsWindow() {
		authorNames.setFont(new Font("Times New Roman", Font.BOLD, 28));
		authorsPanel.setSize(250, 250);
		authorsPanel.setTitle("Authors");
		authorsPanel.setIconImage(windowIcon);
		authorsPanel.add(authorNames, BorderLayout.CENTER);
		authorsPanel.setLocationRelativeTo(null);
		authorsPanel.setDefaultCloseOperation(authorsPanel.DISPOSE_ON_CLOSE);
		authorsPanel.setVisible(true);
		return authorsPanel;
	}
	
	@Test
	public void AuthorsUITest() {
		JFrame authorTestFrame = configureAuthorsWindow();
		assertNotNull(authorTestFrame);
	}

}
