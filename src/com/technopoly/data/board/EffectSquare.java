package com.technopoly.data.board;

public class EffectSquare extends LogicSquare {
	
	public EffectSquare(String display, int value,BoardSquareType type) {
		super(display, value,type);
		canBeOwned = false;
	}

}
