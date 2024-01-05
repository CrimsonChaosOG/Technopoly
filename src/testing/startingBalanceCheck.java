package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.Constants.Resources;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;

public class startingBalanceCheck {

	@Test
	void test() {
		//make sure intial starting is zero
		Player p = new Player("Remus", 0, 1);
		p.AddResources(Resources.StartingResources);
		int b = p.GetResources();
		int startingResources = Constants.Resources.StartingResources;
		assertTrue(b == startingResources, () -> "Players will GET 2000 DEVS AT BEGINNING");
		
	}
 
}  
   