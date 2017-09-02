package edu.bsu.cs222;

public class LastTurn {
	public static int turn;
	public LastTurn(int turn) {
		LastTurn.turn = turn;
	}
	public void setLastTurn(int updatedTurn){
		turn = updatedTurn;
	}
	public int getLastTurn(){
		return turn;
	}
	
}
