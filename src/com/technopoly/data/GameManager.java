package com.technopoly.data;

import java.util.ArrayList;
import java.util.Random;

import java.util.HashMap;
import java.util.Random;

import com.technopoly.data.board.Board;
import com.technopoly.data.board.BoardConfigurator;
import com.technopoly.data.board.BoardSquare;
import com.technopoly.data.board.BoardSquareType;
import com.technopoly.data.board.LogicSquare;
import com.technopoly.data.board.SubsidySquare;
import com.technopoly.data.effects.ActiveEffect;
import com.technopoly.data.effects.Effect;
import com.technopoly.data.upgrades.Upgrade;
import com.technopoly.ui.UIFactory;
import com.technopoly.ui.Window;

public class GameManager {

	// Private properties
	static ArrayList<Player> CurrentPlayers = new ArrayList<Player>();
	static WindowType PreviousWindowType = null;
	static WindowType CurrentWindowType = null;
	static ArrayList<String> textLog = new ArrayList<String>();
	private static HashMap<Player, BoardSquare> playerSquares = new HashMap<Player, BoardSquare>();
	private static int turnIndex = -5;
	private static Board gameBoard = null;

	// Public properties
	public static Player CurrentPlayer = null;
	public static BoardSquare SelectedSquare = null;

	public static void AddPlayer(Player p) {
		if (!CurrentPlayers.contains(p)) {
			CurrentPlayers.add(p);
		}
	}

	public static int GetPlayerCount() {
		return GetCurrentPlayers().size();
	}

	public static ArrayList<Player> GetCurrentPlayers() {
		return CurrentPlayers;
	}

	public static void OpenWindow(WindowType type) {
		ArrayList<ActionItem> additionalItems = new ArrayList<ActionItem>();
		OpenWindow(type, additionalItems);
	}

	public static void OpenWindow(WindowType type, ArrayList<ActionItem> additionalActions) {
		switch (type) {
		case ADD_PLAYER:
			OpenWindow(UIFactory.GenerateAddPlayerWindow(), type);
			break;
		case END_TURN:
			OpenWindow(UIFactory.GenerateConfirmEndTurnWindow(),type);
			break;
		case EXIT_GAME:
			OpenWindow(UIFactory.GenerateExitGameWindow(), type);
			break;
		case GAME_BOARD:
			OpenWindow(UIFactory.GenerateGameBoardWindow(gameBoard, additionalActions), type);
			break;
		case MAIN_MENU:
			OpenWindow(UIFactory.GenerateMainMenuWindow(), type);
			break;
		case VICTORY_SCREEN:
			OpenWindow(UIFactory.GenerateVictoryScreen(), type);
			break;
		case UPGRADE_SQUARE_SELECT:
			OpenWindow(UIFactory.GenerateSquareSelectWindow(UIFactory.SelectSqaureAction.UPGRADE),type);
			break;
		case SELL_SQUARE_SELECT:
			OpenWindow(UIFactory.GenerateSquareSelectWindow(UIFactory.SelectSqaureAction.SELL),type);
			break;
		case SELL_SQUARE:
			OpenWindow(UIFactory.GenerateSellWindow(GameManager.SelectedSquare),type);
			break;
		case UPGRADE_SQUARE:
			OpenWindow(UIFactory.GenerateUpgradeWindow(GameManager.SelectedSquare),type);
			break;
		case USE_ITEM:
			OpenWindow(UIFactory.GenerateUseItemWindow(),type);
		default:
			break;
		}
	}

	private static void OpenWindow(Window w, WindowType type) {
		PreviousWindowType = CurrentWindowType;
		CurrentWindowType = type;

		// Draw the window
		w.Draw();

		// Get the window decision
		if (w.WindowActions.size() > 0) {
			// If we have action items use them
			ActionItem.Decide(w.WindowActions, w.OutputText);
		} else {
			// Else we should have some kind of input
			w.TakeInput();
		}
	}

	public static void AddNewPlayer(String name) {
		// Construct a new Player
		Player p = new Player(name, Constants.Resources.StartingResources, GetPlayerCount());
		// Add this player to our list of players
		AddPlayer(p);
	}

	public static void ExitGame() {
		// TODO Something here

	}
	
	public static void EndGame() {
		OpenWindow(WindowType.VICTORY_SCREEN);
	}

	public static void ReturnToPreviousWindow() {
		if (PreviousWindowType != null) {
			if(PreviousWindowType == WindowType.GAME_BOARD) {
				OpenWindow(PreviousWindowType,GetEndOfMovementActions(CurrentPlayer, true));
			}else {
				OpenWindow(PreviousWindowType);

			}
		}
	}

	public static void SetCurrentPlayer(Player p) {
		CurrentPlayer = p;
	}

	public static void ClearTextLog() {
		textLog.clear();
	}

	public static void AddToTextLog(String s) {
		textLog.add(s);
	}

	public static ArrayList<String> GetTextLog() {
		return textLog;
	}

	public static BoardSquare GetCurrentSquare(Player p) {
		return playerSquares.get(p);
	}

	public static void SetCurrentSquare(Player p, BoardSquare s) {
		playerSquares.put(p, s);
	}

	public static ArrayList<BoardSquare> MovePlayerSquares(Player p, int numberOfSquares) {
		ArrayList<BoardSquare> movedSquares = new ArrayList<BoardSquare>();

		while (numberOfSquares > 0) {
			BoardSquare toMove = GetCurrentSquare(p).NextSquare;
			movedSquares.add(toMove);
			SetCurrentSquare(p, toMove);

			if (toMove instanceof LogicSquare) {
				LogicSquare movedLogic = (LogicSquare) toMove;
				movedLogic.onPassSquare(p);
			}

			numberOfSquares -= 1;
		}

		return movedSquares;
	}

	public static void DoPlayerTurn(Player p) {

		// Clear the textlog
		ClearTextLog();
		// Set the current player to be this player
		SetCurrentPlayer(p);

		// Let the user know who's turn it is
		AddToTextLog(p.GetPlayerName() + "(" + p.GetPlayerNumber() + ") Turn Starts");

		// What square are we starting on
		BoardSquare startingSquare = GetCurrentSquare(p);
		AddToTextLog("You Started the turn on " + startingSquare.getDisplay());
		AddToTextLog("You Currently have " + p.GetResources() + " devs");
		// OnPlayerStart Events
		for (Upgrade u : startingSquare.GetUpgrades()) {
			u.onPlayerStart(p, startingSquare);
		}
		if (startingSquare instanceof LogicSquare) {
			LogicSquare startLogic = (LogicSquare) startingSquare;
			startLogic.onStartSquare(p);
		}

		// What did they roll
		// DiceRoll turnRoll = new DiceRoll();
		DiceRoll turnRoll = p.DoDiceRoll();
		int rollTotal = turnRoll.getTotal();
		if (turnRoll.isDoubles()) {
			AddToTextLog("You rolled a double " + turnRoll.Dice1 + " So you rolled again and got " + turnRoll.Dice3
					+ ", Total: " + rollTotal);
		} else {
			AddToTextLog("You rolled a " + turnRoll.Dice1 + " and a " + turnRoll.Dice2 + ", Total: " + rollTotal);
		}

		// Move to this square
		ArrayList<BoardSquare> movedSquares = MovePlayerSquares(p, rollTotal);

		// What square are they now on
		BoardSquare finalSquare = GetCurrentSquare(p);
		// OnPlayerEnd Events
		for (Upgrade u : finalSquare.GetUpgrades()) {
			u.onPlayerEnd(p, finalSquare);
		}
		if (finalSquare instanceof LogicSquare) {
			LogicSquare finalLogic = (LogicSquare) finalSquare;
			finalLogic.onEndSquare(p);
		}

		AddToTextLog("You are now on " + finalSquare.getDisplay());

		// re-open the window so the user can see their options
		OpenWindow(WindowType.GAME_BOARD, GetEndOfMovementActions(p, false));

	}

	public static ArrayList<ActionItem> GetEndOfMovementActions(Player p, boolean callback) {

		BoardSquare finalSquare = GetCurrentSquare(p);

		ArrayList<ActionItem> turnActions = new ArrayList<ActionItem>();
		
		// At any point the player should be able to end their turn
		ActionItem endTurnAction = new ActionItem("End Turn") {
			@Override
			public void onAction() {
				// Next turn
				TryEndTurn();
			}
		};

		turnActions.add(endTurnAction);
		

		// What actions do they have because of this square
		turnActions.addAll(finalSquare.GetPossibleActionsForPlayer(p, callback));

		// What actions does the player have from effects
		for (ActiveEffect ae : p.GetActiveEffects()) {
			Effect e = ae.GetEffect();
			turnActions.addAll(e.getAdditionalActionItems(p));
		}

		// Can the player make any upgrades?
		if (p.GetUpgradableSquares().size() > 0) {
			turnActions.add(new ActionItem("Upgrade Squares") {
				@Override
				public void onAction() {
					GameManager.OpenWindow(WindowType.UPGRADE_SQUARE_SELECT);
				}
			});
		}
		
		//Can the player sell any squares?
		if(p.GetOwnedSquares().size() > 0) {
			turnActions.add(new ActionItem("Sell Squares") {
				@Override
				public void onAction() {
					GameManager.OpenWindow(WindowType.SELL_SQUARE_SELECT);
				}
			});
		}
		
		if(p.GetInventory().GetItemStacks().size() > 0) {
			turnActions.add(new ActionItem("Use Item") {
				@Override
				public void onAction() {
					GameManager.OpenWindow(WindowType.USE_ITEM);
				}
			});
		}
		
		


		return turnActions;
	}
	
	public static void TryEndTurn() {
		if(CurrentPlayer.GetResources() >= 0) {
			//Make sure we reduce active effect turns
			ArrayList<ActiveEffect> effectsToRemove = new ArrayList<ActiveEffect>();
			for(ActiveEffect ae : CurrentPlayer.GetActiveEffects()) {
				if(ae.GetRemainingTurns() == 1) {
					effectsToRemove.add(ae);
				}else {
					ae.SetRemainingTurns(ae.GetRemainingTurns() - 1);
				}
			}
			while(effectsToRemove.size() > 0) {
				CurrentPlayer.RemoveEffect(effectsToRemove.get(0).GetEffect().getType());
				effectsToRemove.remove(0);
			}
			GameManager.StartNextPlayerTurn();
		}else {
			//Let the player know they will lose if they end their turn
			OpenWindow(WindowType.END_TURN);
		}
	}

	public static void TryBuySquare(Player p, BoardSquare square) {

		int squareValue = square.getValue();
		// Apply Player Effect modifiers
		for (ActiveEffect ae : p.GetActiveEffects()) {
			Effect e = ae.GetEffect();
			squareValue = e.onCalculatePurchaseCost(squareValue);
		}

		if (p.GetResources() < squareValue) {
			AddToTextLog("You tried to buy " + square.getDisplay() + " but lacked devs");
			OpenWindow(WindowType.GAME_BOARD, GetEndOfMovementActions(p, true));
		}

		p.RemoveResources(squareValue);
		AddToTextLog("You spent " + squareValue + " devs");
		AddToTextLog("You now have " + p.GetResources() + " devs");
		SetSquareOwner(square, p);
		AddToTextLog("You are now the owner of " + square.getDisplay());

		// Redraw the info to the user so they are up to date
		OpenWindow(WindowType.GAME_BOARD, GetEndOfMovementActions(p, true));

	}

	public static void StartGame() {

		gameBoard = BoardConfigurator.GetBoard();

		BoardSquare startingSquare = gameBoard.GetStartingSquare();
		System.out.println("Starting sqaure: " + startingSquare.getDisplay());
		// Set starting resources

		for (Player p : GetCurrentPlayers()) {
			p.SetResources(Constants.Resources.StartingResources);
			SetCurrentSquare(p, startingSquare);
		}

		// Start next turn with first player
		StartNextPlayerTurn();

	}

	public static void StartNextPlayerTurn() {
		if (turnIndex < 0) {
			turnIndex = 0;
		} else {
			if (turnIndex >= GetPlayerCount() - 1) {
				turnIndex = 0;
			} else {
				turnIndex++;
			}
		}

		DoPlayerTurn(GetCurrentPlayers().get(turnIndex));

	}

	public static ArrayList<Player> GetPlayersOnSquare(BoardSquare boardSquare) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (Player p : GetCurrentPlayers()) {
			if (GetCurrentSquare(p) == boardSquare) {
				players.add(p);
			}
		}

		return players;
	}

	public static void SetSquareOwner(BoardSquare finalSquare, Player player) {
		finalSquare.setOwner(player);
	}

	public static void AddSubsidyAmount(BoardSquare square,int amt) {
		BoardSquare nearestSubsidy = GetNearestSquareFrom(square, BoardSquareType.SUBSIDY);
		if(nearestSubsidy != null) {
			SubsidySquare subSQ = (SubsidySquare)nearestSubsidy;
			subSQ.currentSubsidyAmount += amt;
		}
	}

	public static int GetSubsidyAmount(BoardSquare square) {
		BoardSquare nearestSubsidy = GetNearestSquareFrom(square, BoardSquareType.SUBSIDY);
		if(nearestSubsidy != null) {
			SubsidySquare subSQ = (SubsidySquare)nearestSubsidy;
			return subSQ.currentSubsidyAmount;
		}
		
		return 0;
	}
	
	public static Player GetRandomPlayer(Player excludedPlayer) {
		Random rand = new Random(System.currentTimeMillis()); 
	    ArrayList<Player> allPlayers = GameManager.GetCurrentPlayers();
	    if(allPlayers.contains(excludedPlayer)) {
	    	allPlayers.remove(excludedPlayer);
	    }
	    
	    int randomValue = rand.nextInt(allPlayers.size());
        return allPlayers.get(randomValue);

	}

	public static BoardSquare GetNearestSquareFrom(BoardSquare origin,BoardSquareType type) {
		ArrayList<BoardSquare> allSquares = gameBoard.GetBoardSquares();
		int originIndex = allSquares.indexOf(origin);
		
		int closestSquareIndex = Integer.MAX_VALUE;
		BoardSquare closetSquare = null;
		
		for(BoardSquare sq : allSquares) {
			if(sq.getType() == type) {
				int tmpIndex = allSquares.indexOf(sq);
				int distance = Math.abs(originIndex - tmpIndex);
				if(distance < closestSquareIndex) {
					closestSquareIndex = tmpIndex;
					closetSquare = sq;
				}
			}
		}
		
		return closetSquare;
		
	}

}

