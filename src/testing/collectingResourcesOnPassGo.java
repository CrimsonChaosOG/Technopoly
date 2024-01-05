package testing;

import com.technopoly.data.effects.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.Player;

class collectingResourcesOnPassGo {
	
	@Test
	void test() {

		Player p = new Player("Remus", 100, 1);
		int rawResources = Constants.Resources.StartingResources;
		int b = p.GetResources();
		for (ActiveEffect ae : p.GetActiveEffects()) {
			rawResources = ae.GetEffect().onCalculatePassGo(rawResources);
		}
		p.AddResources(rawResources);
		int a = p.GetResources();
		// this should now return true as the getResources () Values will be different
		assertTrue(a == 2100, () -> "Dice roll must be atleast 2 but less than 18");
	
	}
 
}

  
     