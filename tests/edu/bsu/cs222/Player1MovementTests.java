package edu.bsu.cs222;

import org.junit.Test;

import static org.junit.Assert.*;
import javax.swing.JButton;

public class Player1MovementTests {
	
		private JButton testButton = new JButton();

		private int TOP(){
			testButton.setSize(100, 100);
			int top = testButton.getHeight() / 3;
			return top;
		}

		private int BOTTOM() {
			testButton.setSize(100, 100);
			int bottom = testButton.getHeight() * 2 / 3;
			return bottom;
		}

		private int RIGHT() {
			testButton.setSize(100, 100);
			int right = testButton.getWidth() * 2 / 3;
			return right;
		}

		private int LEFT() {
			testButton.setSize(100, 100);
			int left = testButton.getWidth() / 3;
			return left;
		}
		
		@Test
		public void testTOP() {
			int top = TOP();
			assertNotNull(top);
		}
		@Test
		public void testBOTTOM(){
			int bottom = BOTTOM();
			assertNotNull(bottom);
		}
		
		@Test
		public void testRIGHT(){
			int right = RIGHT();
			assertNotNull(right);
		}
		
		@Test
		public void testLEFT(){
			int left = LEFT();
			assertNotNull(left);
		}
		
		
}
