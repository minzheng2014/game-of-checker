package edu.bsu.cs222;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LastTurnTests {
	
	@Test
	public void lastTurnTest(){	
		assertNotNull(getLastTurn());
	}
	public int turn = 3;
	
	public void LastTurn(int turn) {
		LastTurn.turn = turn;
	}
	public void setLastTurn(int updatedTurn){
		turn = updatedTurn;
	}
	public int getLastTurn(){
		return turn;
	}
}
