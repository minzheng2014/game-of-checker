package edu.bsu.cs222;

import static org.junit.Assert.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.junit.Test;

public class InfoMenuTests {
	
	@Test
	public void InformationMenuTest() {
		JMenuBar menuBarTest = configureMenu();
		assertNotNull(menuBarTest);
	}

	JMenuBar menuBar = new JMenuBar();
	JMenuItem authors = new JMenuItem("Authors");
	JMenuItem instructions = new JMenuItem("Instructions");
	AuthorsUI authorsUI = new AuthorsUI(authors);
	InstructionsUI instructionsUI = new InstructionsUI(instructions);

	public JMenuBar configureMenu() {
		JMenu info = new JMenu("Information");
		authors.addActionListener(authorsUI);
		instructions.addActionListener(instructionsUI);
		info.add(authors);
		info.add(instructions);
		menuBar.add(info);
		return menuBar;
	}

}
