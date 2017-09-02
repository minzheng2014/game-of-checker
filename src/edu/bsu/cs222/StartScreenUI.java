package edu.bsu.cs222;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreenUI {

	public static void main(String[] args) {
		new StartScreenUI();
	}

	public StartScreenUI() {
		configureStartScreenUI();
	}

	private JFrame startScreenWindow = new JFrame("CHECKERS");
	private int startWindowWidth = 400;
	private int startWindowHeight = 400;
	private Image windowIcon = Toolkit.getDefaultToolkit().getImage(
			"checkers.jpg");

	private JLabel startScreenImage = new JLabel(new ImageIcon("icon.png"));
	private Color darkGreen = new Color(0, 100, 0);
	
	private void configureStartScreenUI() {
		startScreenImage.setOpaque(true);
		startScreenImage.setBackground(darkGreen);
		startScreenWindow.setIconImage(windowIcon);
		startScreenWindow.add(configureWelcomeMessage(), BorderLayout.NORTH);
		startScreenWindow.add(startScreenImage, BorderLayout.CENTER);
		startScreenWindow.add(configuredSettings(), BorderLayout.SOUTH);
		startScreenWindow.setJMenuBar(new InfoMenu().configureMenu());
		startScreenWindow.setResizable(false);
		startScreenWindow.setSize(startWindowWidth, startWindowHeight);
		startScreenWindow
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreenWindow.setLocationRelativeTo(null);
		startScreenWindow.setVisible(true);
	}

	private JPanel settings = new JPanel(new GridLayout(1, 2, 1, 1));
	private JButton playerVsPlayer = new JButton("PLAYER VS PLAYER");
	private JButton playerVsComputer = new JButton("PLAYER VS COMPUTER");
	private PvpUI pvpMatch = new PvpUI(playerVsPlayer, startScreenWindow);
	private PvcUI pvcMatch = new PvcUI(playerVsComputer, startScreenWindow);

	private JPanel configuredSettings() {
		settings.setBackground(darkGreen);
		playerVsPlayer.setForeground(Color.WHITE);
		playerVsComputer.setForeground(Color.WHITE);
		playerVsPlayer.setBackground(darkGreen);
		playerVsComputer.setBackground(darkGreen);
		playerVsPlayer.addMouseListener(pvpMatch);
		playerVsComputer.addMouseListener(pvcMatch);
		settings.add(playerVsPlayer);
		settings.add(playerVsComputer);
		return settings;
	}

	private JPanel welcomeMessage = new JPanel();
	private JLabel message = new JLabel(
			"Welcome to the Game of Checkers. Please pick an option:");

	private JPanel configureWelcomeMessage() {
		message.setForeground(Color.WHITE);
		welcomeMessage.setBackground(darkGreen);
		welcomeMessage.add(message);
		return welcomeMessage;
	}
}
