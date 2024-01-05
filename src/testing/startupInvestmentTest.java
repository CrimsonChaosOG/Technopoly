package testing;

import static org.junit.jupiter.api.Assertions.*;
import com.technopoly.data.*;
import com.technopoly.data.board.*;
import com.technopoly.data.cards.*;
import com.technopoly.data.effects.*;
import com.technopoly.data.items.*;
import com.technopoly.data.upgrades.*;
import com.technopoly.game.*;
import com.technopoly.ui.*;
import com.technopoly.utils.*;


import org.junit.jupiter.api.Test;

class startupInvestmentTest {

	@Test
	void test() {
		Player p = new Player("Remus", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player p2 = new Player("Jonathan", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		

		p.RemoveResources(Constants.Cards.StartupInvestment);
		
		p2.AddResources(Constants.Cards.StartupInvestment);
		
		int newBalance = p2.GetResources();
		
		int value = 2200;

		assertTrue(newBalance == value, () -> "Balances changed");
	}

}
