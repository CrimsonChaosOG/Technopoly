package com.technopoly.data.board;

import java.util.ArrayList;

import com.technopoly.data.Player;

public class BoardField {

	private String display;
	private ArrayList<BoardSquare> fieldSquares = new ArrayList<BoardSquare>();
	
	public String getDisplay() {
		return display;
	}
	
	public BoardField(String display) {
		this.display = display;
	}
	
	public void AddSquare(BoardSquare square) {
		if(!fieldSquares.contains(square)) {
			fieldSquares.add(square);
		}
		
		square.SetField(this);
	}
	
	public boolean OwnedByPlayer(Player p) {
		//each square owned by this player
		for(BoardSquare s : fieldSquares) {
			if(s.getOwner() != p) {
				return false;
			}
		}
		
		return true;
	}	
	
}
