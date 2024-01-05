package com.technopoly.data.items;

import java.util.ArrayList;

import com.technopoly.data.ActionItem;
import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.board.BoardSquare;
import com.technopoly.data.effects.EffectType;

public class ItemManager {

	private static ArrayList<Item> items = new ArrayList<Item>();
	
	public static void SetupItems() {
		
		//Get out of Jail Card
		Item getOutOfJail = new Item("Get out of Jail","Use this card to get out of jail",ItemType.JAIL_REMOVER){
            @Override
            public void onUseItem(Player p) {
            	if(p.HasEffect(EffectType.JAILED)) {
            		p.RemoveEffect(EffectType.JAILED);
            		GameManager.AddToTextLog("You are now out of Jail");
            	}
            }
        };
        items.add(getOutOfJail);
        
        //Work Visa card
		Item workVisa = new Item("Work Visa","Use this card to add " + Constants.Cards.WorkVisaBonus +" devs to your resources",ItemType.WORK_VISA){
            @Override
            public void onUseItem(Player p) {
            	//Add  money to the player
            	p.AddResources(Constants.Cards.WorkVisaBonus);
            	GameManager.AddToTextLog("You recieved " + Constants.Cards.WorkVisaBonus + " devs");
            	GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
            }
        };
        items.add(workVisa);
        
        //Headhunting
        Item headhunting = new Item("Headhunting", "Use this card to randomly select a player to give you 150 devs", ItemType.HEAD_HUNTING) {
        	@Override
        	public void onUseItem(Player p) {
        		//Take resource from random player
        		Player selectedPlayer = GameManager.GetRandomPlayer(p);
        		selectedPlayer.TransferResources(p, Constants.Resources.HeadHuntingCost);
            	GameManager.AddToTextLog("You transfered " + Constants.Resources.HeadHuntingCost+ " devs");
            	GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        };
        //Corporate Espionage
        Item corporateEspionage = new Item("Corporate Espionage", "Use this card to take " + Constants.Cards.CorpEspLevyPct + "% of every other players' devs", ItemType.CORPORATE_ESPIONAGE) {
        	@Override
        	public void onUseItem(Player p) {
        		ArrayList<Player> currentPlayers = GameManager.GetCurrentPlayers();
        		for(int i=0; i<currentPlayers.size(); i++) {
        			if(currentPlayers.get(i) != p) {
        				int tempRes = currentPlayers.get(i).GetResources();
        				tempRes*=Constants.Cards.CorpEspLevyDec;
        				currentPlayers.get(i).TransferResources(p, tempRes);
        			}
        		}
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        };
        items.add(corporateEspionage);
		
        
        Item internalTraining = new Item("Internal Training","Use this card to Recieve 25 devs from every square you own",ItemType.INTERNAL_TRAINING) {
        	@Override
        	public void onUseItem(Player p ) {
        		p.AddResources(Constants.Cards.InternalTraining * p.GetOwnedSquares().size());
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        };
		items.add(internalTraining);
	
		Item Networking = new Item("Networking","Use this card to recieve " + Constants.Cards.NetworkingBonus + " devs from every player",ItemType.NETWORK) {
			@Override
			public void onUseItem(Player p) {
				int upgradeNumber = 0;
				GameManager.GetCurrentPlayers();
				for(BoardSquare sq : p.GetOwnedSquares()){if(sq.GetUpgrades().size() > 0) { upgradeNumber++; }}
				for(Player player : GameManager.GetCurrentPlayers()){
					if(player != p){ // remember p is the player using this item
						player.TransferResources(p,Constants.Cards.NetworkingBonus*upgradeNumber);
					}
				}
				GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
			}
		};
		items.add(Networking);
        Item blackHatHacker = new Item("Black Hat Hacker", "Randomly steal 20% of another players devs ", ItemType.BLACK_HAT_HACKER){
			@Override
        	public void onUseItem(Player p) {
				Player randomPlayer = GameManager.GetRandomPlayer(p);
				int twentyPercentRandomPlayersDevs = (int) (randomPlayer.GetResources()*0.2);
				randomPlayer.TransferResources(p, twentyPercentRandomPlayersDevs);
				GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
			}
		};
		items.add(blackHatHacker);
	}

	
	public static Item GetItem(ItemType type) {
		for(Item i : items) {
			if(i.getType() == type) {
				return i;
			}
		}
		
		return null;
	}
}