package com.technopoly.data.board;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.Constants.Resources;

public class SubsidySquare extends LogicSquare {
	
	public int currentSubsidyAmount = Constants.Resources.DefaultSubsidy;
	
	public SubsidySquare(String display, int value,BoardSquareType type) {
		super(display, value,type);
		canBeOwned = false;
	}
	
	@Override
	public void onEndSquare(Player p) {
		super.onEndSquare(p);
		
		GameManager.AddToTextLog("You recieved subsidy of: " + currentSubsidyAmount);
		p.AddResources(currentSubsidyAmount);
		currentSubsidyAmount = 0;
		GameManager.AddToTextLog("You Currently have " + p.GetResources() + " devs");
		
	}
	
	

}