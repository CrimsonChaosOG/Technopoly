package com.technopoly.game;

import com.technopoly.data.GameManager;
import com.technopoly.data.WindowType;
import com.technopoly.data.cards.CardManager;
import com.technopoly.data.effects.EffectManager;
import com.technopoly.data.items.ItemManager;
import com.technopoly.data.upgrades.UpgradeManager;
import com.technopoly.ui.UIFactory;
import com.technopoly.ui.Window;

public class Main  {
	

	public static void main(String[] args) {
		
		//Setup entities
		CardManager.SetupCards();
		EffectManager.SetupEffects();
		ItemManager.SetupItems();
		UpgradeManager.SetupUpgrades();		
		
		//Open the Main menu
		GameManager.OpenWindow(WindowType.MAIN_MENU);
		
		
	}

}
