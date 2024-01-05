package testing;

import static org.junit.jupiter.api.Assertions.*;
import com.technopoly.data.*;
import com.technopoly.data.board.*;
import com.technopoly.data.cards.*;
import com.technopoly.data.effects.*;
import com.technopoly.data.items.*;
import com.technopoly.data.upgrades.*;
import com.technopoly.game.*;
import com.technopoly.ui.*;
import com.technopoly.utils.*;
import org.junit.jupiter.api.Test;

class hrDisasterTest {

	@Test
	void test() {
		
		
		Player p = new Player("Jonathan", 1000, 1);         
		p.RemoveResources(10); 
		int b = p.GetResources();   
		
		assertTrue(b == 990, () -> "Test that player loses its 10 developers");  
		
	}
}
