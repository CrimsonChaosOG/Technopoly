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

class limitOfPlayers {

	@Test
	void test() {
	
		Player a = new Player("A", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player b = new Player("B", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player c = new Player("C", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player d = new Player("D", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player e = new Player("E", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player f = new Player("F", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player g = new Player("G", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player h = new Player("H", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		Player i = new Player("I", Constants.Resources.StartingResources, GameManager.GetPlayerCount());
		GameManager.AddPlayer(a);
		GameManager.AddPlayer(b);
		GameManager.AddPlayer(c);
		GameManager.AddPlayer(d);
		GameManager.AddPlayer(e);
		GameManager.AddPlayer(f);
		GameManager.AddPlayer(g);
		GameManager.AddPlayer(h);
		GameManager.AddPlayer(i);
		
		assertTrue(GameManager.GetPlayerCount() <= 8, () -> "Test -- "
                + "to avoid constructing complex messages unnecessarily.");
	
	}

}
