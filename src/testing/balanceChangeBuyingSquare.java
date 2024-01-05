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

class balanceChangeBuyingSquare {

	@Test
	void test() {
        Board gameBoard = BoardConfigurator.GetBoard();
        Player p = new Player("Remus", Constants.Resources.StartingResources, 1);
        BoardSquare startingSquare = gameBoard.GetStartingSquare();
        GameManager.SetCurrentSquare(p, startingSquare);
        int rollTotal = 3;
        GameManager.MovePlayerSquares(p, rollTotal);
        BoardSquare finalSquare = GameManager.GetCurrentSquare(p);
        
        int startBalance = p.GetResources();
         
        int squareValue = finalSquare.getValue();
        p.RemoveResources(squareValue);
        GameManager.SetSquareOwner(finalSquare, p);
        
        int newBalance = p.GetResources();

        assertTrue(startBalance != newBalance, () -> "Balances changed");
        
    }
}


