package edu.bsu.cs222;

import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

public class InstructionsUITests {
	@Test
	public void InstructionsUITest() {
		JFrame test = configureWindow();
		assertNotNull(test);
	}
	
	JFrame testWindow = new JFrame();
	JLabel testLabel = new JLabel();
	
	private JFrame configureWindow(){
		testWindow.setTitle("This is a Test Window");
		testWindow.add(testLabel);
		return testWindow;
	}
}
