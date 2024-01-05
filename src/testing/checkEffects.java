package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.technopoly.data.*;
import com.technopoly.data.board.*;
import com.technopoly.data.cards.*;
import com.technopoly.data.effects.*;
import com.technopoly.data.items.*;
import com.technopoly.data.upgrades.*;
import com.technopoly.game.*;
import com.technopoly.ui.*;
import com.technopoly.utils.*;

class checkEffects {

	private boolean effect;

	@Test
	void test() {
		ArrayList<Effect> effects = new ArrayList<Effect>();
		ArrayList<ActiveEffect> activeEffects = new ArrayList<ActiveEffect>();
		Player a = new Player("A", 2000, GameManager.GetPlayerCount());
		GameManager.AddPlayer(a);
		
        Effect VPN = new Effect("VPN",EffectType.VPN);
        effects.add(VPN);
   
        Effect e = EffectManager.GetEffect(EffectType.VPN);
        ActiveEffect ae = new ActiveEffect(e, 1);
		activeEffects.add(ae);
		
		for(ActiveEffect a1 : activeEffects) {
			if(a1.GetEffect().getType() == EffectType.VPN) {
				effect = true;
			}
		}
		
		assertTrue(effect == true, () -> "Check if the user has an active effect");
	}

}
