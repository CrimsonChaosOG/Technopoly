package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;

class addingRemovingResources {

	@Test
	void test() {
		Player a = new Player("A", 2000, GameManager.GetPlayerCount());
		GameManager.AddPlayer(a);
		
		a.AddResources(100);
		a.RemoveResources(50);
		
		int endBalance = a.GetResources();
		
		assertTrue(2050 == endBalance, () -> "Balance accurate after adding 100 and removing 50 resources");
	   
	}

}
