package com.technopoly.data.board;

import com.technopoly.data.Player;

public class LogicSquare extends BoardSquare {

	public LogicSquare(String display, int value,BoardSquareType type) {
		super(display, value,type);
		canBeOwned = false;
	}
	
	public void onStartSquare(Player p) {
		
	}
	
	public void onEndSquare(Player p) {
		
	}
	
	public void onPassSquare(Player p) {
		
	}

}
