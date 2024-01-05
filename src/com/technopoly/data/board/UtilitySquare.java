package com.technopoly.data.board;

public class UtilitySquare extends BoardSquare {
	
	public UtilitySquare(String display, int value,BoardSquareType type) {
		super(display, value,type);
		canBeOwned = true;
	}

}
