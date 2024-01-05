package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.Player;
import com.technopoly.data.board.BoardSquare;

public class serverOutageCommunityTest {

	@Test
	void test() {
	
		Player p = new Player("Remus", 100, 1);
		int notUpgradedNumber = 0;
		notUpgradedNumber++;
		for(BoardSquare sq : p.GetOwnedSquares()){if(sq.GetUpgrades().size() <= 0) { notUpgradedNumber++; }}
		p.RemoveResources((int) (notUpgradedNumber*Constants.Cards.ServerOutageCost));
		int total = p.GetResources();
		assertTrue(total == 50, () -> "Double effect when passing over go");
	}
   
}
