package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;

public class prDiasterConstantTest {

	@Test
	void test() {
		Player p = new Player("Remus", 1000, 1);
		int a = p.GetResources();
		p.RemoveResources(Constants.Cards.chanceCardPR);
		int b = p.GetResources();
		assertTrue(b == 800, () -> "Double effect when passing over go");
	}
 
}
