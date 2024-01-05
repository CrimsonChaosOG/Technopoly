package com.technopoly.data;

import java.util.Random;

public class DiceRoll {
	
	public int Dice1 = 0;
	public int Dice2 = 0;
	public int Dice3 = 0;
	
	public boolean isDoubles() {
		return Dice1 == Dice2;
	}
	
	public int getTotal() {
		return Dice1 + Dice2 + Dice3;
	}
	
	public DiceRoll() {
		Random rand = new Random(System.currentTimeMillis()); 
		int value1 = rand.nextInt(6);
		value1 ++;
		
		int value2 = rand.nextInt(6);
		value2 ++;
		
		Dice1 = value1;
		Dice2 = value2;
		
		if(isDoubles()) {
			int value3 = rand.nextInt(6);
			value3 ++;
			Dice3 = value3;
		}
		
	}
	
	public DiceRoll(int value1,int value2,int value3) {
		Dice1 = value1;
		Dice2 = value2;
		Dice3 = value3;
	}

}
