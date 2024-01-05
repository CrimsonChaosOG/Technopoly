package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.Player;
import com.technopoly.data.Constants.Resources;

class chanceCardScholarshipTest {

	@Test
	void test() {
		Player p = new Player("Remus", 100, 1);
		int a = p.GetResources();
		p.AddResources((Constants.Cards.ScholarshipBonus));
		int b = p.GetResources();
		
		
		assertTrue(b == 400 , () -> "Players will GET 2000 DEVS AT BEGINNING");
	}
   
}
