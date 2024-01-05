package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;

class excludedPlayerInRandomPlayer {

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
		
		Player player1 = GameManager.GetRandomPlayer(a);
		
		assertTrue(player1 != a, () -> "Not allowing for player a to be chosen");
	}


}
