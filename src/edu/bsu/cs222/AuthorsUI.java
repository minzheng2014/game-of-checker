package edu.bsu.cs222;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class AuthorsUI implements ActionListener {

	private JMenuItem authors = new JMenuItem();

	public AuthorsUI(JMenuItem authors) {
		this.authors = authors;
	}

	@Override
	public void actionPerformed(ActionEvent authorsClicked) {
		if (authorsClicked.getSource() == authors) {
			configureAuthorsWindow();
		}
	}

	private JFrame authorsPanel = new JFrame();
	private JLabel authorNames = new JLabel(
			"<html>Authors:<br>Min Zheng<br>Jiayue Wu<br>Daniel Cox<br>Dylon Price<br>Copyright fpteam1</html>",
			SwingConstants.CENTER);

	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");

	@SuppressWarnings("static-access")
	public void configureAuthorsWindow() {
		authorNames.setFont(new Font("Times New Roman", Font.ITALIC, 28));
		authorsPanel.setSize(250, 250);
		authorsPanel.setTitle("Authors");
		authorsPanel.setIconImage(windowIcon);
		authorsPanel.add(authorNames, BorderLayout.CENTER);
		authorsPanel.setLocationRelativeTo(null);
		authorsPanel.setDefaultCloseOperation(authorsPanel.DISPOSE_ON_CLOSE);
		authorsPanel.setVisible(true);
	}
}
