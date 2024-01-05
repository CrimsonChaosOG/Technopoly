package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.Player;

class unionFormedTest {

	@Test
	void test() {
		Player p = new Player("Jonathan", 2000, 0);
		int resourceAmount = p.GetResources();
		p.RemoveResources((int) (resourceAmount*Constants.Cards.UnionFormCost));
		
		double newBalance = p.GetResources();
		
		assertTrue(newBalance != resourceAmount, () -> "Test that player loses 10% of its developers"); 
	}

}
