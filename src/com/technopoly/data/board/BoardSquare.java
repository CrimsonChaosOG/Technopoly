package com.technopoly.data.board;

import java.util.ArrayList;

import com.technopoly.data.ActionItem;
import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.effects.ActiveEffect;
import com.technopoly.data.effects.Effect;
import com.technopoly.data.upgrades.Upgrade;
import com.technopoly.data.upgrades.UpgradeManager;
import com.technopoly.data.upgrades.UpgradeType;
import com.technopoly.utils.Utils;

public class BoardSquare {

	
	private String Display;
	private int Value;
	private BoardSquareType Type;
	
	private Player Owner = null;
	private BoardField boardField = null;
	
	public BoardSquare NextSquare = null;
	public BoardSquare PreviousSquare = null;
	public boolean canBeOwned = true;
	
	private ArrayList<Upgrade> Upgrades = new ArrayList<Upgrade>();
	
	public BoardSquare(String display,int value,BoardSquareType type) {
		setDisplay(display);
		setValue(value);
		setType(type);
	}
	
	public String getDisplay() {
		return Display;
	}
	
	public void setDisplay(String display) {
		Display = display;
	}
	public int getValue() {
		int startingValue = getRawValue();
		return startingValue;
	}
	
	public int getRawValue() {
		return Value;
	}
	
	public void setValue(int value) {
		Value = value;
	}
	public BoardSquareType getType() {
		return Type;
	}
	public void setType(BoardSquareType type) {
		Type = type;
	}
	
	public String getPlayerText() {
		String players = "";
		for(Player p : GameManager.GetPlayersOnSquare(this)) {
			players += p.GetPlayerNumber();
		}
		return players;
	}
	
	public String getUpdgradesText() {
		return "";
	}
	
	//return a 9x4 square of text
	public String[] convertToText() {
		String[] lines = new String[4];
		
		lines[0] = Utils.PadString(Display,7);
		lines[1] = Utils.PadString("$"+Value ,7);
		lines[2] = Utils.PadString(getPlayerText(), 7);
		lines[3] = Utils.PadString(getUpdgradesText(),7);
		
		return lines;
		
	}

	public Player getOwner() {
		return Owner;
	}
	
	public void setOwner(Player p) {
		if(Owner != null) {
			Owner.RemoveOwnedSquare(this);
		}
		Owner = p;
		if(p!= null) {
			p.AddOwnedSquare(this);
		}
	}

	public int getRentValue(Player paying,Player receiving) {
		int startingValue = 0;
		if(Type == BoardSquareType.PROPERTY) {
			startingValue = getRawValue() / 10;
		}
		if(Type == BoardSquareType.UNIVERSITY) {
			startingValue = Constants.Squares.University.BaseUniversityPay * receiving.GetOwnedSquaresOfType(Type.UNIVERSITY).size();
		}
		
		//Apply Upgrades Modifiers
		for(Upgrade u : Upgrades) {
			startingValue = u.onCalculateRent(startingValue);
		}
		
		//Apply receiving modifiers
		for(ActiveEffect ae : receiving.GetActiveEffects()) {
			Effect e = ae.GetEffect();
			startingValue = e.onCalculateRecieveRent(startingValue);
		}
		
		//Apply paying modifiers
		for(ActiveEffect ae : paying.GetActiveEffects()) {
			Effect e = ae.GetEffect();
			startingValue = e.onCalculateRent(startingValue);
		}
		
		return startingValue;
	}

	
	//I know this looks weird but its the easiest way to reference the current square when we are overriding functions
	private BoardSquare getSquare() {
		return this;
	}
	
	public ArrayList<ActionItem> GetPossibleActionsForPlayer(Player p,boolean callback){
		ArrayList<ActionItem> actions = new ArrayList<ActionItem>();
		
		// Is this a square that can be owned?
		if (canBeOwned) {
			// Is there an owner?
			Player owner = getOwner();
			if (owner != null) {
				if(owner != p && !callback) {
					// Someone owns this square
					GameManager.AddToTextLog("This square belongs to " + owner.GetPlayerName());
					int rentValue = getRentValue(p,owner);
					p.TransferResources(owner,rentValue);
					GameManager.AddToTextLog("You transfered " + rentValue + " to " + owner.GetPlayerName());
					GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
				}
			} else {
				// No one owns this square
				if(!callback) {
					GameManager.AddToTextLog("No one owns this square");
				}
				//Add option to buy this square
				ActionItem buyPropertyAction = new ActionItem("Buy Square: " + getDisplay()){
		            @Override
		            public void onAction() {
		            	GameManager.TryBuySquare(p,getSquare());
		            }
		        };
		        actions.add(buyPropertyAction);
			}

		}
		
		return actions;
		
	}

	public boolean HasUpgrade(UpgradeType type) {
		return HasUpgrade(type,1);
	}
	
	public boolean HasUpgrade(UpgradeType type,int count) {
		int found = 0;
		for(Upgrade u : Upgrades) {
			if(u.getType() == type) {
				found ++;
				if(found >= count) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean TryAddUpgrade(UpgradeType type,Player p) {
		Upgrade u = UpgradeManager.GetUpgrade(type);
		return TryAddUpgrade(u, p);
	}
	
	public boolean TryAddUpgrade(Upgrade u,Player p) {
		//Check to see if the player has resources
		int cost = u.getUpgradeCost();
		if(p.HasResources(cost)) {
			p.RemoveResources(cost);
			GameManager.AddToTextLog("Added Upgrade " + u.getDisplayName() + " to " + this.getDisplay());
			Upgrades.add(u);
			GameManager.AddToTextLog("You spent " + cost + " on this upgrade");
			GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<Upgrade> GetUpgrades(){
		return Upgrades;
	}
	
	public ArrayList<Upgrade> GetPossibleUpgrades(){
		ArrayList<Upgrade> allUpgrades = UpgradeManager.GetUpgrades();
		ArrayList<Upgrade> possibleUpgrades = new ArrayList<Upgrade>();
		for(Upgrade u : allUpgrades) {
			if(u.canBuild(Owner, this)) {
				possibleUpgrades.add(u);
			}
		}
		
		return allUpgrades;
	}

	public void SetField(BoardField boardField) {
		this.boardField = boardField;
	}
	
	public BoardField GetField() {
		return boardField;
	}

}
