package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.items.ItemStack;
import com.technopoly.data.items.ItemType;

public class headHuntingItem {

	@Test
	public void test() {
		Player currentPlayer = new Player("Jonny", 500, 0);
		Player getRandomPlayer = new Player("Remus", 400,0);
		int a = currentPlayer.GetResources();
		//Player currentPlayer = GameManager.CurrentPlayer;
		//Player getRandomPlayer = GameManager.GetRandomPlayer(currentPlayer);
		//ArrayList<ItemStack> allItemsStacks = currentPlayer.GetInventory().GetItemStacks();
		if(currentPlayer.GetInventory().HasItem(ItemType.HEAD_HUNTING)){
			Player selectedPlayer = GameManager.GetRandomPlayer(getRandomPlayer);
    		selectedPlayer.TransferResources(currentPlayer, Constants.Resources.HeadHuntingCost);
			int b = currentPlayer.GetResources();
			// should return false as the current resources will now be different
			assertTrue(b != a, () -> "Players will have differnt values");
		}
		
	}
   
}
  