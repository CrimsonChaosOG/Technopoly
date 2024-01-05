package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import com.technopoly.data.board.*;
import com.technopoly.data.*;
import com.technopoly.data.effects.*;
import com.technopoly.ui.*;
import com.technopoly.utils.*;
import com.technopoly.game.*;
import com.technopoly.data.upgrades.*;
import com.technopoly.data.items.*;
import com.technopoly.data.cards.*;


import org.junit.jupiter.api.Test;

import  com.technopoly.data.DiceRoll;
import com.technopoly.data.Player;

class correctMovedSpacesTest {

	@Test
	void test() {
        Board gameBoard = BoardConfigurator.GetBoard();
        Player p = new Player("Remus", 100, 1);
        BoardSquare startingSquare = gameBoard.GetStartingSquare();
        GameManager.SetCurrentSquare(p, startingSquare);
        DiceRoll turnRoll = p.DoDiceRoll();
        int rollTotal = turnRoll.getTotal();
        GameManager.MovePlayerSquares(p, rollTotal);
        BoardSquare finalSquare = GameManager.GetCurrentSquare(p);
        assertTrue(startingSquare != finalSquare, () -> "Boardsquare not the same");
    }
	   
}
     