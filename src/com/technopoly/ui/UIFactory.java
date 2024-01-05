package com.technopoly.ui;

import java.util.ArrayList;

import com.technopoly.data.ActionItem;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.WindowType;
import com.technopoly.data.board.Board;
import com.technopoly.data.board.BoardSquare;
import com.technopoly.data.effects.ActiveEffect;
import com.technopoly.data.items.Item;
import com.technopoly.data.items.ItemStack;
import com.technopoly.data.upgrades.Upgrade;
import com.technopoly.utils.BlockedWordsValidationSetting;
import com.technopoly.utils.Input;
import com.technopoly.utils.InputValidation;
import com.technopoly.utils.InputValidationSetting;
import com.technopoly.utils.LengthValidationSetting;
import com.technopoly.utils.Vector2;

public class UIFactory {
	
	public static Window GenerateExitGameWindow() {
		Window w = new Window("Exit Game Window", new Vector2(240,4));
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(50,4),"-");
		Label label1 = new Label("Exit Label","Are you sure you want to exit?",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		
		
		
		ActionItem exitGameAction = new ActionItem("Yes"){
            @Override
            public void onAction() {
            	//Exit Game
            	GameManager.ExitGame();
            }
        };
        w.WindowActions.add(exitGameAction);
        
		ActionItem cancelAction = new ActionItem("No"){
            @Override
            public void onAction() {
            	//Do not Exit Game
            	GameManager.ReturnToPreviousWindow();
            }
        };
        w.WindowActions.add(cancelAction);
        
        w.setHeight(w.WindowActions.size()+3);
        gameBox.setHeight(w.WindowActions.size()+3);
		
		
		gameBox.addUIElement(label1);
		
		int id = 1;
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(1,1+id),new Vector2(50,10),LabelAlignment.CENTRE);
			gameBox.addUIElement(lab);
			id++;
		}
		w.addUIElement(gameBox);

		
		return w;
	}
	
	public static Window GenerateConfirmEndTurnWindow() {
		Window w = new Window("End Turn Window", new Vector2(240,10));
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(50,10),"-");
		Label label1 = new Label("Label","You have negative devs.",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		Label label2 = new Label("Label","If you end your turn you will lose and the game is over.",new Vector2(1,2),new Vector2(50,10),LabelAlignment.CENTRE);
		Label label3 = new Label("Label","Are you sure you want to end your turn?",new Vector2(1,3),new Vector2(50,10),LabelAlignment.CENTRE);

		
		ActionItem endTurnAction = new ActionItem("Yes"){
            @Override
            public void onAction() {
            	//End Game - the current player is the loser
            	GameManager.EndGame();
            }
        };
        w.WindowActions.add(endTurnAction);
        
		ActionItem cancelAction = new ActionItem("No"){
            @Override
            public void onAction() {
            	//Do not End Game
            	GameManager.ReturnToPreviousWindow();
            }
        };
        w.WindowActions.add(cancelAction);
		
		
		gameBox.addUIElement(label1);
		gameBox.addUIElement(label2);
		gameBox.addUIElement(label3);
		
		int id = 1;
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(1,3+id),new Vector2(50,10),LabelAlignment.CENTRE);
			gameBox.addUIElement(lab);
			id++;
		}
		w.addUIElement(gameBox);

		
		return w;
	}
	
	public enum SelectSqaureAction{
		UPGRADE,
		SELL
	}
	
	public static Window GenerateSquareSelectWindow(SelectSqaureAction actionType) {
		Player currentPlayer = GameManager.CurrentPlayer;
		
		Window w = new Window("Square Select Window",new Vector2(240, 62));
		
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(50,5),"-");

		
		int currentX =0;
		int currentY =0;
		int currentRows =0;
		int currentColumns = 0;
		
		int sizeX = 20;
		int sizeY = 5;
		
		int maxRows = 3;
		
		ArrayList<BoardSquare> squares = new ArrayList<BoardSquare>();
		
		switch(actionType) {
		case UPGRADE:
			squares = currentPlayer.GetUpgradableSquares();
			break;
		case SELL:
			squares = currentPlayer.GetOwnedSquares();
			break;
		default:
			squares = currentPlayer.GetOwnedSquares();
		}
		
		int i = 1;
		for(BoardSquare s : squares) {
			Box box = new Box(s.getDisplay()+"_select",new Vector2(currentX,currentY),new Vector2(sizeX,sizeY),"-");
			Label label1 = new Label("select_name",i +". " + s.getDisplay(),new Vector2(1,1),new Vector2(sizeX-2,1),LabelAlignment.CENTRE);
			ActionItem selectAction = new ActionItem("Select " + s.getDisplay()){
	            @Override
	            public void onAction() {
	            	GameManager.SelectedSquare = s;
	            	switch(actionType) {
					case SELL:
						GameManager.OpenWindow(WindowType.SELL_SQUARE);
						break;
					case UPGRADE:
						GameManager.OpenWindow(WindowType.UPGRADE_SQUARE);
						break;
					default:
						GameManager.OpenWindow(WindowType.GAME_BOARD);
						break;
	            	
	            	}
	            }
	        };
	        
	        box.addUIElement(label1);
	        w.addUIElement(box);
	        
	        w.WindowActions.add(selectAction);
	        currentY += sizeY;
	        currentRows ++;
	        if(currentRows > maxRows) {
	        	currentX += sizeX;
	        	currentY = 0;
	        	currentColumns ++;
	        }
	        
	        i++;
		}
		
		//Cancel Action
		ActionItem cancelAction = new ActionItem("Cancel"){
            @Override
            public void onAction() {
            	GameManager.OpenWindow(WindowType.GAME_BOARD, GameManager.GetEndOfMovementActions(currentPlayer, true));
            }
        };
        w.WindowActions.add(cancelAction);
        
        Box box = new Box("Cancel"+"_select",new Vector2(currentX,currentY),new Vector2(sizeX,sizeY),"-");
		Label label1 = new Label("select_name",i +". " + "Cancel",new Vector2(1,1),new Vector2(sizeX-2,1),LabelAlignment.CENTRE);
		
        box.addUIElement(label1);
        w.addUIElement(box);

		
		
		return w;
	}
	
	public static Window GenerateUpgradeWindow(BoardSquare square) {
		Player currentPlayer = GameManager.CurrentPlayer;
		
		Window w = new Window("Square Upgrade Window",new Vector2(240, 62));
		
		//Get a list of all upgrades this square can buy
		ArrayList<Upgrade> possibleUpgrades = square.GetPossibleUpgrades();
				
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(230,60),"-");
		Label label1 = new Label("Message","Select Upgrade",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		
		w.addUIElement(gameBox);
		gameBox.addUIElement(label1);
		
		int currentX =0;
		int currentY =0;
		int currentRows =0;
		int currentColumns = 0;
		
		int sizeX = 10;
		int sizeY = 3;
		
		int maxRows = 3;
		
		int i = 1;
		for(Upgrade u : possibleUpgrades) {
			Box box = new Box(u.getDisplayName()+"_select",new Vector2(currentX,currentY),new Vector2(sizeX,sizeY),"-");
			Label label_tmp = new Label("select_name",i +". " + u.getDisplayName(),new Vector2(1,1),new Vector2(sizeX-2,1),LabelAlignment.CENTRE);
			ActionItem selectAction = new ActionItem("Select " + u.getDisplayName()){
	            @Override
	            public void onAction() {
	            	square.TryAddUpgrade(u, currentPlayer);
	            	GameManager.OpenWindow(WindowType.GAME_BOARD,GameManager.GetEndOfMovementActions(currentPlayer, true));
	            }
	        };
	        
	        box.addUIElement(label_tmp);
	        w.addUIElement(box);
	        
	        w.WindowActions.add(selectAction);
	        currentY += sizeY;
	        currentRows ++;
	        if(currentRows > maxRows) {
	        	currentX += sizeX;
	        	currentY = 0;
	        	currentColumns ++;
	        }
	        
	        i++;
		}
		
		//Cancel Action
		ActionItem cancelAction = new ActionItem("Cancel"){
            @Override
            public void onAction() {
            	GameManager.ReturnToPreviousWindow();
            }
        };
        w.WindowActions.add(cancelAction);
        
        
        //Set the output prompt for the user making the decision
        w.OutputText="Select Option";
		
		int id = 1;
		//For each ActionItem add a label to the box so the user can see what options are available
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(1,2+id),new Vector2(50,10),LabelAlignment.CENTRE);
			gameBox.addUIElement(lab);
			id++;
		}
		w.addUIElement(gameBox);
		
		return w;
	}
	
	public static Window GenerateUseItemWindow() {
		Player currentPlayer = GameManager.CurrentPlayer;
		
		Window w = new Window("Use Item Window",new Vector2(240, 62));
		
		//Get a list of all upgrades this square can buy
		ArrayList<ItemStack> allItemsStacks = currentPlayer.GetInventory().GetItemStacks();
		ArrayList<Item> uniqueItems = new ArrayList<Item>();
		for(ItemStack is : allItemsStacks) {
			if(!uniqueItems.contains(is.GetItem())) {
				uniqueItems.add(is.GetItem());
			}
		}
				
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(230,60),"-");
		Label label1 = new Label("Message","Select Upgrade",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		
		w.addUIElement(gameBox);
		gameBox.addUIElement(label1);
		
		int currentX =0;
		int currentY =0;
		int currentRows =0;
		int currentColumns = 0;
		
		int sizeX = 10;
		int sizeY = 3;
		
		int maxRows = 3;
		
		int i = 1;
		for(Item item : uniqueItems) {
			Box box = new Box(item.getItemName()+"_select",new Vector2(currentX,currentY),new Vector2(sizeX,sizeY),"-");
			Label label_tmp = new Label("select_name",i +". " + item.getItemName(),new Vector2(1,1),new Vector2(sizeX-2,1),LabelAlignment.CENTRE);
			ActionItem selectAction = new ActionItem("Use " + item.getItemName()){
	            @Override
	            public void onAction() {
	            	GameManager.AddToTextLog("You used item: " + item.getItemName());
	            	item.onUseItem(currentPlayer);
	            	currentPlayer.GetInventory().RemoveItem(item.getType());
	            	GameManager.OpenWindow(WindowType.GAME_BOARD,GameManager.GetEndOfMovementActions(currentPlayer, true));

	            }
	        };
	        
	        box.addUIElement(label_tmp);
	        w.addUIElement(box);
	        
	        w.WindowActions.add(selectAction);
	        currentY += sizeY;
	        currentRows ++;
	        if(currentRows > maxRows) {
	        	currentX += sizeX;
	        	currentY = 0;
	        	currentColumns ++;
	        }
	        
	        i++;
		}
		
		//Cancel Action
		ActionItem cancelAction = new ActionItem("Cancel"){
            @Override
            public void onAction() {
            	GameManager.ReturnToPreviousWindow();
            }
        };
        w.WindowActions.add(cancelAction);
        
        
        //Set the output prompt for the user making the decision
        w.OutputText="Select Option";
        
        //Calculate the height of the box to display based on the number of options we can select
        w.setHeight(2+w.WindowActions.size()+2);
        gameBox.setHeight(2+w.WindowActions.size()+2);
		
		int id = 1;
		//For each ActionItem add a label to the box so the user can see what options are available
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(1,2+id),new Vector2(50,10),LabelAlignment.CENTRE);
			gameBox.addUIElement(lab);
			id++;
		}
		w.addUIElement(gameBox);
		
		return w;
	}
	
	public static Window GenerateSellWindow(BoardSquare square) {
		Player currentPlayer = GameManager.CurrentPlayer;
		
		Window w = new Window("Square Sell Window",new Vector2(240, 62));
		
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(50,5),"-");
		Label label1 = new Label("Message","Do you want to sell this Square?",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		
		w.addUIElement(gameBox);
		gameBox.addUIElement(label1);
		
		//Do Sell Action
		ActionItem sellAction = new ActionItem("Sell"){
            @Override
            public void onAction() {
            	currentPlayer.SellSquare(square);
            	GameManager.OpenWindow(WindowType.GAME_BOARD,GameManager.GetEndOfMovementActions(currentPlayer, true));
            }
        };
        w.WindowActions.add(sellAction);
		
		//Cancel Action
		ActionItem cancelAction = new ActionItem("Cancel"){
            @Override
            public void onAction() {
            	GameManager.ReturnToPreviousWindow();
            }
        };
        w.WindowActions.add(cancelAction);
        
        
        //Set the output prompt for the user making the decision
        w.OutputText="Select Option";
        
        //Calculate the height of the box to display based on the number of options we can select
        w.setHeight(2+w.WindowActions.size()+2);
        gameBox.setHeight(2+w.WindowActions.size()+2);
		
		int id = 1;
		//For each ActionItem add a label to the box so the user can see what options are available
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(1,2+id),new Vector2(50,10),LabelAlignment.CENTRE);
			gameBox.addUIElement(lab);
			id++;
		}
		w.addUIElement(gameBox);
		
		
		return w;
	}
	
	public static Window GenerateVictoryScreen() {
		Window w = new Window("Victory Window",new Vector2(240, 10));
		Label label1 = new Label("Game Over Label","Game Over!",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		Label label2 = new Label("Loser Label","Loser: " + GameManager.CurrentPlayer.GetPlayerName() ,new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);

		w.addUIElement(label1);
		w.addUIElement(label2);
		
		return w;
	}
	
	public static Window GenerateMainMenuWindow() {
		
		//Get a count of the current number of players we have
		int currentPlayerCount = GameManager.GetPlayerCount();
		
		Window w = new Window("Main Menu Window", new Vector2(240,10));
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(50,5),"-");
		Label label1 = new Label("Welcome Label","Welcome to Technopoly",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		Label label2 = new Label("Player Label","Current Players: "+currentPlayerCount,new Vector2(1,2),new Vector2(50,10),LabelAlignment.CENTRE);
		
		//If we have more than 2 players than we can have an option to start the game
		if(currentPlayerCount > 2) {
			ActionItem startGameAction = new ActionItem("Start Game"){
	            @Override
	            public void onAction() {
	            	//Open start the game
	            	GameManager.StartGame();
	            }
	        };
	        w.WindowActions.add(startGameAction);
		}
		
		//If we have less than 8 players then we can have the option to add a new player
		if(currentPlayerCount < 8) {
			ActionItem addPlayerAction = new ActionItem("Add Player"){
	            @Override
	            public void onAction() {
	            	//Open the add player window
	            	GameManager.OpenWindow(WindowType.ADD_PLAYER);
	            }
	        };
	        w.WindowActions.add(addPlayerAction);
		}
		
		//At any point in the menu we can opt to quit the game
		ActionItem exitGameAction = new ActionItem("Quit Game"){
            @Override
            public void onAction() {
            	//Exit Game window
            	GameManager.OpenWindow(WindowType.EXIT_GAME);
            }
        };
        w.WindowActions.add(exitGameAction);
        
        //Set the output prompt for the user making the decision
        w.OutputText="Select Option";
        
        //Calculate the height of the box to display based on the number of options we can select
        w.setHeight(2+w.WindowActions.size()+2);
        gameBox.setHeight(2+w.WindowActions.size()+2);
		
		//Add our two labels to the box
		gameBox.addUIElement(label1);
		gameBox.addUIElement(label2);
		
		int id = 1;
		//For each ActionItem add a label to the box so the user can see what options are available
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(1,2+id),new Vector2(50,10),LabelAlignment.CENTRE);
			gameBox.addUIElement(lab);
			id++;
		}
		w.addUIElement(gameBox);

		
		return w;
	}
	
	public static Window GenerateAddPlayerWindow() {
		Window w = new Window("Add Player Window", new Vector2(240,5)) {
			@Override
			public void HandleInput(String input) {
				//The input of this window will be a valid player name
				GameManager.AddNewPlayer(input);
				GameManager.OpenWindow(WindowType.MAIN_MENU);
			};
		};
		
		//Add UI Elements for display
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(50,5),"-");
		Label label1 = new Label("Title Label","Add New Player",new Vector2(1,1),new Vector2(50,10),LabelAlignment.CENTRE);
		Label label2 = new Label("Player Label","Input New Players Name Below",new Vector2(1,2),new Vector2(50,10),LabelAlignment.CENTRE);
		Label label3 = new Label("Info Label","(Names must be unique)",new Vector2(1,3),new Vector2(50,10),LabelAlignment.CENTRE);

		
		gameBox.addUIElement(label1);
		gameBox.addUIElement(label2);
		gameBox.addUIElement(label3);

		w.addUIElement(gameBox);

		w.OutputText = "Input Player Name";
		
		ArrayList<InputValidationSetting> settings = new ArrayList<InputValidationSetting>();
		
		ArrayList<String> blockedWords = new ArrayList<String>();
		for(Player p : GameManager.GetCurrentPlayers()) {
			blockedWords.add(p.GetPlayerName());
		}
		
		BlockedWordsValidationSetting blacklist = new BlockedWordsValidationSetting(blockedWords);
		settings.add(blacklist);
		
		LengthValidationSetting lengthValid = new LengthValidationSetting(1, 30);
		settings.add(lengthValid);
		
		w.InputValidation = new InputValidation(settings);
		
		return w;
	}

	public static Box GenerateBoardBox(Board board) {
		
		if(!board.hasValidNumberSquares()) {
			return new Box("Gameboard",new Vector2(1,1),new Vector2(1,1));
		}
		
		ArrayList<BoardSquare> squares = board.GetBoardSquares();
		
		Vector2 boardSize = board.GetBoardSize();
		
		int charsForSquareWidth = 10;
		int charsForSquareHeight = 6;
		
		Box box = new Box("Gameboard",new Vector2(2,1),new Vector2(boardSize.getX() *charsForSquareWidth,(boardSize.getY()) * charsForSquareHeight ));
		
		Vector2 currentPosition = Vector2.Zero();
		
		int currentLineNo = 0;
		//we start top left
		//0 - moving to from left to right, 1 - moving down right side, 2- move bottom from right to left, 3 - moving up left side
		int currentSection = 0;
		
		for(BoardSquare s : squares) {
			
			String buildString ="*";
			if(s.getOwner() != null) {
				buildString = ""+s.getOwner().GetPlayerNumber();
			}
			
			Box squareBox = new Box(s.getDisplay()+"_box("+currentSection+")",currentPosition,new Vector2(charsForSquareWidth,charsForSquareHeight),buildString);
			
			int labelNo = 0;
			for(String str : s.convertToText()) {
				Label label = new Label(squareBox.ElementName+"_label_"+labelNo,str,new Vector2(1,labelNo+1),new Vector2(str.length(),1));
				squareBox.addUIElement(label);
				labelNo++;
			}
			
			
			box.addUIElement(squareBox);
			
			currentLineNo++;
			
			//calculate next line number
			switch(currentSection) {
			case 0:
			case 2:
				//width
				if(currentLineNo >= boardSize.getX()) {
					//end of section
					currentSection++;
					currentLineNo = 0;
					//System.out.println("End of section:" + squareNumber + "/" + squares.size());

				}
				break;
			case 1:
			case 3:
				//height
				if(currentLineNo >= boardSize.getY() - 2) {
					//end of section
					currentSection++;
					currentLineNo = 0;
					//System.out.println("End of section:" + squareNumber + "/" + squares.size());
				}
				break;
			}
			
			
			//calculate next position
			switch(currentSection) {
			case 0:
				currentPosition = new Vector2(0+(currentLineNo*charsForSquareWidth),0);
				break;
			case 1:
				currentPosition = new Vector2((boardSize.getX() - 1)*charsForSquareWidth,charsForSquareHeight+(currentLineNo * charsForSquareHeight));
				break;
			case 2:
				currentPosition = new Vector2((boardSize.getX() - 1)*charsForSquareWidth-(currentLineNo*charsForSquareWidth),0+((boardSize.getY() - 1) * charsForSquareHeight));
				break;
			case 3:
				currentPosition = new Vector2(0,0+((boardSize.getY() - 2) * charsForSquareHeight)-(currentLineNo * charsForSquareHeight));
				break;
			}
			
		}
		
		return box;
	}

	public static Window GenerateGameBoardWindow(Board gameBoard,ArrayList<ActionItem> additionalActions) {
		
		Player currentPlayer = GameManager.CurrentPlayer;
			
		
		Window w = new Window("Game Window", new Vector2(240,62));
		Box gameBox = new Box("Element 1",Vector2.Zero(),new Vector2(144,50),"@");
		gameBox.addUIElement(UIFactory.GenerateBoardBox(gameBoard));
		w.addUIElement(gameBox);
		
		LabelledBox textLogBox = new LabelledBox("Element 2","Text Log",new Vector2(150,0),new Vector2(70,62),"@");
		
		ArrayList<String> textLog = GameManager.GetTextLog();
		for (int i = 0; i < textLog.size(); i++) {
			String s = textLog.get(i);
			Label lab = new Label(i+"_label",s,new Vector2(1,2+i),new Vector2(68,10),LabelAlignment.CENTRE);
			textLogBox.addUIElement(lab);
		}
		
		
		w.addUIElement(textLogBox);
        
        w.WindowActions.addAll(additionalActions);
        
        LabelledBox actionItemBox = new LabelledBox("Element 3","Actions",new Vector2(0,52),new Vector2(50,10),"@");
        
		w.addUIElement(actionItemBox);
		//Add each action as an option
		int id = 1;
		//For each ActionItem add a label to the box so the user can see what options are available
		for(ActionItem act : w.WindowActions) {
			Label lab = new Label(act.getDisplay()+"_label",id +". " + act.getDisplay(),new Vector2(2,id),new Vector2(50,10),LabelAlignment.LEFT);
			actionItemBox.addUIElement(lab);
			id++;
		}
		
		LabelledBox inventoryBox = new LabelledBox("Element 4","Inventory",new Vector2(51,52),new Vector2(45, 10),"@");
		int inventoryId = 1;
		//For each item in the player inventory show a display so that they are aware of what they have available
		for(ItemStack is : currentPlayer.GetInventory().GetItemStacks()) {
			Label lab = new Label(is.GetItem().getItemName(),inventoryId+". " + is.GetDisplay(),new Vector2(2,inventoryId),new Vector2(50, 10),LabelAlignment.LEFT);
			inventoryBox.addUIElement(lab);
			inventoryId++;
		}
		
		w.addUIElement(inventoryBox);
		
		LabelledBox effectsBox = new LabelledBox("Element 5","Effects",new Vector2(98,52),new Vector2(45, 10),"@");
		int effectId = 1;
		//Display each effect the player has and how many more turns they will have it for
		for(ActiveEffect ae : currentPlayer.GetActiveEffects()) {
			Label lab = new Label(ae.GetEffect().getType()+"_"+effectId,effectId+". " + ae.GetEffect().GetDisplay() +"("+ae.GetRemainingTurns()+")",new Vector2(2,effectId),new Vector2(50, 10),LabelAlignment.LEFT);
			effectsBox.addUIElement(lab);
			effectId++;
		}
		
		w.addUIElement(effectsBox);	
		
		return w;
		
	}

}
