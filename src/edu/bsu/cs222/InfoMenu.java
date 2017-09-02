package edu.bsu.cs222;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class InfoMenu {
	
	private JMenuBar infoMenuBar = new JMenuBar();
	private JMenuItem authors = new JMenuItem("Authors");
	private JMenuItem instructions = new JMenuItem("Instructions");
	private AuthorsUI authorsUI = new AuthorsUI(authors);
	private InstructionsUI instructionsUI = new InstructionsUI(instructions);
	
	public JMenuBar configureMenu(){
		JMenu info = new JMenu("Information");
		authors.addActionListener(authorsUI);
		instructions.addActionListener(instructionsUI);
		info.add(authors);
		info.add(instructions);
		infoMenuBar.add(info);
		return infoMenuBar;
	}
	
}
