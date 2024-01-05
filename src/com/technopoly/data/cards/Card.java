package com.technopoly.data.cards;

import com.technopoly.data.Player;

public class Card {
	
	private String CardName;
	private String Description;
	
	public Card(String cardName,String description) {
		CardName = cardName;
		Description = description;
	}
	
	public void onTakeCard(Player p) {
		
	}
	
	public String GetCardName() {
		return CardName;
	}
	
	public String GetDescription() {
		return Description;
	}

}
