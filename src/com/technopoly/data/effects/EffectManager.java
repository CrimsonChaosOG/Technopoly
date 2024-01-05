package com.technopoly.data.effects;

import java.util.ArrayList;

import com.technopoly.data.ActionItem;
import com.technopoly.data.Constants;
import com.technopoly.data.DiceRoll;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.WindowType;

public class EffectManager {

	private static ArrayList<Effect> effects = new ArrayList<Effect>();

	public static void SetupEffects() {

		Effect jailed = new Effect("Jailed", EffectType.JAILED) {
			@Override
			public DiceRoll onCalculateDice(DiceRoll roll) {
				// If they rolled doubled then they can move
				if (roll.Dice1 == roll.Dice2) {
					return roll;
				} else {
					return new DiceRoll(0, 0, 0);
				}
			}

			@Override
			public ArrayList<ActionItem> getAdditionalActionItems(Player p) {
				ArrayList<ActionItem> items = new ArrayList<ActionItem>();

				ActionItem outOfJailAction = new ActionItem("Pay out of Jail") {
					@Override
					public void onAction() {
						if (p.HasResources(Constants.Resources.OutOfJailCost)) {
							p.RemoveEffect(EffectType.JAILED);
							p.RemoveResources(Constants.Resources.OutOfJailCost);
							GameManager.AddToTextLog("You paid to get out of jail");
							GameManager.AddToTextLog("You now have " + p.GetResources() + "devs");
							GameManager.OpenWindow(WindowType.GAME_BOARD,GameManager.GetEndOfMovementActions(p,true));
							
						}
					}
				};
				items.add(outOfJailAction);

				return items;
			}
		};
		effects.add(jailed);
		
		Effect doubleDice = new Effect("Double Dice",EffectType.DOUBLE_DICE){
            @Override
            public DiceRoll onCalculateDice(DiceRoll roll) {
            	return new DiceRoll(roll.Dice1*2,roll.Dice2*2,roll.Dice3*2);
            }
        };
    
        effects.add(doubleDice);
        
        Effect unionBusting = new Effect("Union Busting",EffectType.UNION_BUSTING) {
        	@Override
        	public int onCalculatePurchaseCost(int input) {
        		return input/2;
        	};
        };
        effects.add(unionBusting);
        
        
        Effect doubleUp = new Effect("Double Up", EffectType.DOUBLE_UP) {
        	@Override
        	public int onCalculatePassGo(int input) {
        		return input*2;
        	}
        };
        effects.add(doubleUp);
        Effect bigData = new Effect("Big Data",EffectType.BIG_DATA) {
        	@Override
        	public DiceRoll onCalculateDice(DiceRoll roll) {
        		return new DiceRoll(roll.Dice1/2, roll.Dice2/2,roll.Dice3/2);
        	}
        	public int onCalculatePurchaseCost(int input) {
        		return input/2;
        	}
        };
        effects.add(bigData);
        Effect techSavvy = new Effect("Tech Savvy",EffectType.TECH_SAVVY) {
        	@Override
        	public int onCalculateRent(int input) {
        		double toReturn =  input*Constants.Cards.TechSavvy;
        		return((int)toReturn);
        	};
        	public int onCalculateRecieveRent(int input) {
        		double toReturn = input*Constants.Cards.TechSavvyRecieve;
        		return((int)toReturn);
        	};
        };
        effects.add(techSavvy);
        Effect VPN = new Effect("VPN",EffectType.VPN) {
        	@Override
        	public int onCalculateRent(int input) {
        		return (int) (input*Constants.Cards.VPNrent);
        	};
        };
        effects.add(VPN);
        

		Effect investmentStrategy = new Effect("Investment Strategy", EffectType.INVESTMENT_STRATEGY) {
			@Override
			public int onCalculateRent(int input) {
				return (int) (input * 1.5);
			};

			public int onCalculateRecieveRent(int input) {
				return (int) (input * 1.7);
			};
		};
		effects.add(investmentStrategy);

	}

	public static Effect GetEffect(EffectType type) {
		for (Effect e : effects) {
			if (e.getType() == type) {
				return e;
			}
		}

		return null;
	}

}
