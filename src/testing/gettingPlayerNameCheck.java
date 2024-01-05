package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.GameManager;
import com.technopoly.data.Player;

class gettingPlayerNameCheck {

	@Test
	void test() {
		Player a = new Player("Jonathan", 2000, GameManager.GetPlayerCount());
		GameManager.AddPlayer(a);
		
		a.AddResources(100);
		a.RemoveResources(50);
		
		String name = a.GetPlayerName();
		
		assertTrue("Jonathan" == name, () -> "Name is accurate");
	}

}
