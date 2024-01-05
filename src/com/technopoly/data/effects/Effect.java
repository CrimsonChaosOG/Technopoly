package com.technopoly.data.effects;

import java.util.ArrayList;

import com.technopoly.data.ActionItem;
import com.technopoly.data.DiceRoll;
import com.technopoly.data.Player;

public class Effect {

	private EffectType type;
	private String display;
	
	public EffectType getType() {
		return type;
	}
	
	public Effect(String dis,EffectType type) {
		display = dis;
		this.type = type;
	}
	
	public String GetDisplay() {
		return display;
	}
	
	public int onCalculateRent(int input) {
		return input;
	}
	
	public DiceRoll onCalculateDice(DiceRoll rawRoll) {
		return rawRoll;
	}
	
	public int onCalculateRecieveRent(int input) {
		return input;
	}
	
	public int onCalculatePurchaseCost(int input) {
		return input;
	}
	
	public int onCalculatePassGo(int input) {
		return input;
	}
	
	public ArrayList<ActionItem> getAdditionalActionItems(Player p){
		return new ArrayList<ActionItem>();
	}

	
}
