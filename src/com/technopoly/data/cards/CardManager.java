package com.technopoly.data.cards;

import java.util.ArrayList;
import java.util.Random;
import com.technopoly.data.Constants;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.board.BoardSquare;
import com.technopoly.data.board.BoardSquareType;
import com.technopoly.data.board.SubsidySquare;
import com.technopoly.data.items.ItemType;
import com.technopoly.data.Constants;

public class CardManager {

	private static ArrayList<Card> chanceCards = new ArrayList<Card>();
	private static ArrayList<Card> communityCards = new ArrayList<Card>();

	public static void SetupCards() {
		
		Card hrDisaster = new Card("HR Disaster","You lost 10 devs"){
            @Override
            public void onTakeCard(Player p) {
            	GameManager.AddToTextLog("You lost 10 devs");
            	p.RemoveResources(10);
            	GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
            }
            
        };
        chanceCards.add(hrDisaster);
        
        Card chanceCardPR = new Card("PR Disaster", "You lost 200 devs") {
        	@Override
        	public void onTakeCard(Player p) {
        		GameManager.AddToTextLog("You lost 200 devs");
        		p.RemoveResources(Constants.Cards.chanceCardPR);
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        	
        };
        chanceCards.add(chanceCardPR);

        Card corpTaxChCard = new Card("Corporation Tax Chance Card", "Pay 50 devs for every company you own.") {
        	@Override
        	public void onTakeCard(Player p) {
        		int sqNo = p.GetOwnedSquares().size();
        		GameManager.AddToTextLog("You lost " + sqNo*Constants.Cards.CorpTaxCost + " devs");
        		p.RemoveResources(sqNo*Constants.Cards.CorpTaxCost);
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        };
        chanceCards.add(corpTaxChCard);

        
		Card chanceCardScholarship = new Card("Chance Card 2","Gain 300 devs"){
            @Override
            public void onTakeCard(Player p) {
            	GameManager.AddToTextLog("You gained 300 devs");
            	p.AddResources(Constants.Cards.ScholarshipBonus);
            	GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
            }
        };
        chanceCards.add(chanceCardScholarship);
        
        
		Card openSourceCard = new Card("Open Sourcing","Donate 10 devs"){
            @Override
            public void onTakeCard(Player p) {
            	GameManager.AddToTextLog("You lost 10 devs");
            	p.RemoveResources(10);
            	GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
            	
            	BoardSquare sq = GameManager.GetNearestSquareFrom(GameManager.GetCurrentSquare(p), BoardSquareType.SUBSIDY);
            	if(sq != null) {
            		SubsidySquare subSq = (SubsidySquare)sq;
            		subSq.currentSubsidyAmount += Constants.Cards.OpenSourceAmt;
                	GameManager.AddToTextLog("There are now " + subSq.currentSubsidyAmount + " devs in subsidy");

            	}
            }
        };
        communityCards.add(openSourceCard);
        
        Card communityCardRecruit = new Card("Recruitment Fair", "Gain 50 devs") {
        	@Override
        	public void onTakeCard(Player p) {
        		GameManager.AddToTextLog("You gained 50 devs!");
        		p.AddResources(50);
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        		
        	}
        };
        communityCards.add(communityCardRecruit);

        Card isoAuditComCard = new Card("ISO Audit Card", "Pay 10 devs for every upgrade you have") {
        	@Override
        	public void onTakeCard(Player p) {
        		int upgradeNo = 0;
        		for(BoardSquare sq : p.GetOwnedSquares()) {
        			if(sq.GetUpgrades().size() > 0) {
        				upgradeNo++;
        			}
        		}
        		GameManager.AddToTextLog("You lost " + upgradeNo + " devs");
        		p.RemoveResources(upgradeNo*Constants.Cards.ISOAuditCost);
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        };
        communityCards.add(isoAuditComCard);
        
        
        Card startupInvestment = new Card("Startup Investment","Pay a random player 200 devs") {
        	@Override
        	public void onTakeCard(Player p) {
        		p.RemoveResources(Constants.Cards.StartupInvestment);
        		Player recipient = GameManager.GetRandomPlayer(p);
        		recipient.AddResources(Constants.Cards.StartupInvestment);
        		GameManager.AddToTextLog("You paid 200 devs to "+recipient.GetPlayerName());
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        		GameManager.AddToTextLog(recipient.GetPlayerName()+ " now has "+recipient.GetResources()+" devs");
        		
        	}
        };
        communityCards.add(startupInvestment);
        Card unionFormed = new Card("Union Formed","You lost 10% of your devs"){
            @Override
            public void onTakeCard(Player p) {
            	GameManager.AddToTextLog("You lost 10% of your devs");
            	int resourceAmount = p.GetResources();
            	p.RemoveResources((int) (resourceAmount*Constants.Cards.UnionFormCost));
            	GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
            }
        };
        chanceCards.add(unionFormed);
        
        
        Card serverOutage = new Card("Server Outage","Lose 50 developers for every square without an upgrade!") {
        	@Override
        	public void onTakeCard(Player p) {
        		int notUpgradedNumber = 0;
    			for(BoardSquare sq : p.GetOwnedSquares()){if(sq.GetUpgrades().size() <= 0) { notUpgradedNumber++; }}
    			p.RemoveResources((int) (notUpgradedNumber*Constants.Cards.ServerOutageCost));
    			
    			GameManager.AddToTextLog("You lost " + notUpgradedNumber*Constants.Cards.ServerOutageCost + " developers.");
    			GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
        	}
        };
        communityCards.add(serverOutage);

		Card chairmanElection = new Card("Chairman Election", "Transfer every other player 20 developers") {
			@Override
			public void onTakeCard(Player p) {
				for (Player player : GameManager.GetCurrentPlayers()) {
					if (player != p) {
						p.TransferResources(player, Constants.Cards.ChairmanElection);
					}
				}
        		GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");

			}

		};
		chanceCards.add(chairmanElection);
		
		//Chance cards for items
		Card addItemNetworking = new Card("Take Item", "Take Item: Networking") {
			@Override
			public void onTakeCard(Player p) {
				p.GetInventory().AddItem(ItemType.NETWORK);
			}

		};
		chanceCards.add(addItemNetworking);
		
		Card addItemInternalTraining = new Card("Take Item", "Take Item: Internal Training") {
			@Override
			public void onTakeCard(Player p) {
				p.GetInventory().AddItem(ItemType.INTERNAL_TRAINING);
			}

		};
		chanceCards.add(addItemInternalTraining);
		
		Card addItemHeadhunting = new Card("Take Item", "Take Item: Headhunting") {
			@Override
			public void onTakeCard(Player p) {
				p.GetInventory().AddItem(ItemType.HEAD_HUNTING);
			}

		};
		chanceCards.add(addItemHeadhunting);
		
		Card addItemBlackHat = new Card("Take Item", "Take Item: Black Hat") {
			@Override
			public void onTakeCard(Player p) {
				p.GetInventory().AddItem(ItemType.BLACK_HAT_HACKER);
			}

		};
		chanceCards.add(addItemBlackHat);
		
		Card addItemEspionage = new Card("Take Item", "Take Item: Espionage") {
			@Override
			public void onTakeCard(Player p) {
				p.GetInventory().AddItem(ItemType.CORPORATE_ESPIONAGE);
			}

		};
		chanceCards.add(addItemEspionage);
	}

	public static Card GetRandomCard(CardType type) {
		Random rand = new Random(System.currentTimeMillis());
		switch (type) {
		case CHANCE: {
			if (chanceCards.size() == 0) {
				return null;
			}
			int index = rand.nextInt(chanceCards.size());
			return chanceCards.get(index);
		}
		case COMMUNITY_CHEST: {
			if (communityCards.size() == 0) {
				return null;
			}
			int index = rand.nextInt(communityCards.size());
			return communityCards.get(index);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

}
