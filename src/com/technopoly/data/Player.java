package com.technopoly.data;

import java.util.ArrayList;

import com.technopoly.data.board.BoardSquare;
import com.technopoly.data.board.BoardSquareType;
import com.technopoly.data.effects.ActiveEffect;
import com.technopoly.data.effects.Effect;
import com.technopoly.data.effects.EffectManager;
import com.technopoly.data.effects.EffectType;
import com.technopoly.data.items.Inventory;
import com.technopoly.data.upgrades.Upgrade;
import com.technopoly.data.upgrades.UpgradeManager;
import com.technopoly.utils.Debug;

public class Player {

	//Properties
	private String Name;
	private int Resources;
	private int PlayerNumber;
	private Inventory Inventory;	
	private ArrayList<ActiveEffect> activeEffects = new ArrayList<ActiveEffect>();
	private ArrayList<BoardSquare> ownedSquares = new ArrayList<BoardSquare>();
	
	
	public Player(String name,int startingMoney,int playerNumber) {
		Name = name;
		Resources = startingMoney;
		PlayerNumber = playerNumber;
		Inventory = new Inventory();
	}
	
	public Inventory GetInventory() {
		return Inventory;
	}
	
	public int GetPlayerNumber() {
		return PlayerNumber;
	}
	
	public String GetPlayerName() {
		return Name;
	}
	
	public void SetResources(int amt) {
		Resources = amt;
	}
	
	public void AddResources(int amt) {
		Resources += amt;
	}
	
	public void RemoveResources(int amt) {
		Resources -= amt;
	}
	
	public boolean HasResources(int amt) {
		return Resources >= amt;
	}

	public int GetResources() {
		return Resources;
	}
	
	public ArrayList<ActiveEffect> GetActiveEffects(){
		return activeEffects;
	}
	
	public void AddEffect(Effect e, int amt) {
		ActiveEffect ae = new ActiveEffect(e, amt);
		activeEffects.add(ae);
		GameManager.AddToTextLog("You were given the " + e.GetDisplay() + " effect");
	}
	
	public void AddEffect(EffectType type, int amt) {
		Effect e = EffectManager.GetEffect(type);
		AddEffect(e, amt);
	}
	
	public boolean HasEffect(EffectType type) {
		for(ActiveEffect ae : activeEffects) {
			if(ae.GetEffect().getType() == type) {
				return true;
			}
		}
		
		return false;
	}
	
	public void RemoveEffect(EffectType type) {
		
		ArrayList<ActiveEffect> effectsToRemove = new ArrayList<ActiveEffect>();
		
		for(ActiveEffect e : activeEffects) {
			if(e.GetEffect().getType() == type) {
				effectsToRemove.add(e);
			}
		}
		
		activeEffects.removeAll(effectsToRemove);
		
	}

	public DiceRoll DoDiceRoll() {
		DiceRoll roll = new DiceRoll();
		
		//Apply any modifiers from the players current effects
		for(ActiveEffect ae : activeEffects) {
			Effect e = ae.GetEffect();
			roll = e.onCalculateDice(roll);
		}
		
		return roll;
	}
	
	
	//returns a list of all squares the Player owns that they can upgrade
	public ArrayList<BoardSquare> GetUpgradableSquares(){
		ArrayList<BoardSquare> squares = new ArrayList<BoardSquare>();
		for(BoardSquare s : GetOwnedSquares()) {
			
			for(Upgrade u : UpgradeManager.GetUpgrades()) {
				if(u.canBuild(this, s) && s.GetField() != null && s.GetField().OwnedByPlayer(this)) {
					squares.add(s);
					break;
				}
			}
			
		}
		
		return squares;
	}

	public void TransferResources(Player otherPlayer, int value) {
		RemoveResources(value);
		otherPlayer.AddResources(value);
		
	}
	
	public ArrayList<BoardSquare> GetOwnedSquares(){
		return ownedSquares;
	}
	
	public void AddOwnedSquare(BoardSquare square) {
		if(!ownedSquares.contains(square)) {
			ownedSquares.add(square);
		}
	}
	
	public void RemoveOwnedSquare(BoardSquare square) {
		if(ownedSquares.contains(square)) {
			ownedSquares.remove(square);
		}
	}

	public void SellSquare(BoardSquare square) {
		int value = square.getRawValue();
		square.getOwner().AddResources(value);
		square.setOwner(null);
		GameManager.AddToTextLog("You sold " + square.getDisplay());
		GameManager.AddToTextLog("You now have " + GetResources() + " devs");
		

		
		
	}

	public void TeleportToNearest(BoardSquareType type) {
		BoardSquare targetSquare = GameManager.GetNearestSquareFrom(GameManager.GetCurrentSquare(this),type);
		if(targetSquare != null) {
			GameManager.AddToTextLog("You were teleported to " + targetSquare.getDisplay());
			GameManager.SetCurrentSquare(this, targetSquare);
		}
		
	}

	public ArrayList<BoardSquare> GetOwnedSquaresOfType(BoardSquareType type) {
		ArrayList<BoardSquare> squares = new ArrayList<BoardSquare>();
		for(BoardSquare sq : ownedSquares) {
			if(sq.getType() == type) {
				squares.add(sq);
			}
		}
		
		return squares;
	}
	
}
