package com.technopoly.data.upgrades;

import com.technopoly.data.Player;
import com.technopoly.data.board.BoardSquare;

public class Upgrade {
	
	private UpgradeType type;
	
	private String upgradeName;
	private int cost;
	
	public UpgradeType getType() {
		return type;
	}
	
	public Upgrade(String name,UpgradeType type,int cost) {
		this.type = type;
		this.upgradeName = name;
		this.cost = cost;
	}
	
	public String getDisplayName() {
		return upgradeName;
	}
	
	public int getUpgradeCost() {
		return cost;
	}
	
	//Action to perform if a player ends their turn on this square
	public void onPlayerEnd(Player p, BoardSquare s) {
		
	}
	
	//Action to perform if a player stars their turn on this square
	public void onPlayerStart(Player p, BoardSquare s) {
		
	}
	
	//Modifiers to rent when this upgrade is on a square
	public int onCalculateRent(int input) {
		return input;
	}
	
	
	//Conditions under which a player can build this upgrade
	public boolean canBuild(Player p, BoardSquare s) {
		return true;
	}

}
