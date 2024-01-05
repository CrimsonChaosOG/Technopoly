package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.Player;
import com.technopoly.data.effects.ActiveEffect;
import com.technopoly.data.effects.EffectType;

 public class doublePassOverGo {

	 public void testDoubleDice() {
		Player p = new Player("Remus", 0, 1);
		int b = p.GetResources();
		int rawResources = Constants.Resources.StartingResources;
		p.AddEffect(EffectType.DOUBLE_UP, 1);
		for (ActiveEffect ae : p.GetActiveEffects()) {
			rawResources = ae.GetEffect().onCalculatePassGo(rawResources);
		}
		p.AddResources(rawResources);
		int a = p.GetResources();
		// should return true 
		assertTrue(b == a*2, () -> "Double effect when passing over go");
	}
  
} 
    