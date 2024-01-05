package testing;
import com.technopoly.data.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class diceThrow {

	@Test
	void testDice() {
		// Sucessful completion of the dice throw values must be greater than two, if there is a double dice the etxra throw makes
		Player p = new Player("Remus", 100, 1);
		DiceRoll turnRoll = p.DoDiceRoll();
		int rollTotal = turnRoll.getTotal();
		//should return true
		assertTrue(rollTotal >= 2 && rollTotal <= 18, () -> "Dice roll must be atleast 2 but less than 18");
		
		
	}
   
}
   