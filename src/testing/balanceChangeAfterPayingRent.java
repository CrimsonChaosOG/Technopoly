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

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.board.Board;
import com.technopoly.data.board.BoardConfigurator;
import com.technopoly.data.board.BoardSquare;

class balanceChangeAfterPayingRent {

	@Test
	void test() {
		Board gameBoard = BoardConfigurator.GetBoard();
		Player p = new Player("Remus", Constants.Resources.StartingResources, 1);
		BoardSquare startingSquare = gameBoard.GetStartingSquare();
		GameManager.SetCurrentSquare(p, startingSquare);
		int rollTotal = 3;
		GameManager.MovePlayerSquares(p, rollTotal);
		BoardSquare finalSquare = GameManager.GetCurrentSquare(p);

		int squareValue = finalSquare.getValue();
		p.RemoveResources(squareValue);
		finalSquare.setOwner(p);

		Player p2 = new Player("Jonathan", Constants.Resources.StartingResources, 2);
		int startBalance = p2.GetResources();
		BoardSquare startingSquare2 = gameBoard.GetStartingSquare();
		GameManager.SetCurrentSquare(p2, startingSquare2);
		
		GameManager.MovePlayerSquares(p2, rollTotal);
		BoardSquare finalSquare2 = GameManager.GetCurrentSquare(p2);

		Player owner = finalSquare2.getOwner();
		int rentValue = finalSquare2.getRentValue(p2, owner);
		p2.TransferResources(owner, rentValue);

		int newBalance = p2.GetResources();

		assertTrue(startBalance != newBalance, () -> "Balances changed");
	}

}
