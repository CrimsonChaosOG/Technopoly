package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.board.Board;
import com.technopoly.data.board.BoardConfigurator;
import com.technopoly.data.board.BoardSquare;

class sellingSquare {

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
        
        int value = finalSquare.getRawValue();
		finalSquare.getOwner().AddResources(value);
		finalSquare.setOwner(null);
        
        int newBalance = p.GetResources();
        
        

        assertTrue(startBalance == newBalance, () -> "Balance returned after buying and then selling");
	}

}
