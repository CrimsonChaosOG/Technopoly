package com.technopoly.data.board;

import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.cards.Card;
import com.technopoly.data.cards.CardManager;
import com.technopoly.data.cards.CardType;

public class CardSquare extends LogicSquare {
	
	public CardSquare(String display, int value,BoardSquareType type,CardType cardType) {
		super(display, value,type);
		Type = cardType;
		canBeOwned = false;
	}
	
	private CardType Type;
	
	public Card GetRandomCard() {
		return CardManager.GetRandomCard(Type);
	}
	
	@Override
	public void onEndSquare(Player p) {
		//Draw a random card
		Card drawnCard = GetRandomCard();
		GameManager.AddToTextLog("You ended on a Card Square");
		GameManager.AddToTextLog("You drew: " + drawnCard.GetCardName());
		GameManager.AddToTextLog("It says: " + drawnCard.GetDescription());
		drawnCard.onTakeCard(p);
	}

}
