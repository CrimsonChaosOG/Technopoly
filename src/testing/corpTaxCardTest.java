package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;

class corpTaxCardTest {

	@Test
	void test() {
		Player p = new Player("Remus", 1000, 1);
		int a = p.GetResources();
		int sqNo = p.GetOwnedSquares().size();
		sqNo++;
		GameManager.AddToTextLog("You lost " + sqNo*Constants.Cards.CorpTaxCost + " devs");
		p.RemoveResources(sqNo*Constants.Cards.CorpTaxCost);
		int b = p.GetResources();
		assertTrue(b != a, () -> "CorpCardTest");
		  
	}

}
  