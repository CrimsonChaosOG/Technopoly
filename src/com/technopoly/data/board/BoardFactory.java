package com.technopoly.data.board;

import java.util.ArrayList;

import com.technopoly.data.Constants;
import com.technopoly.data.GameManager;
import com.technopoly.data.Player;
import com.technopoly.data.cards.CardType;
import com.technopoly.data.effects.ActiveEffect;
import com.technopoly.data.effects.EffectType;
import com.technopoly.utils.Vector2;

public class BoardFactory {

	public static Board BuildBoard(Vector2 size) {

		return BuildBoard(size, new ArrayList<String>(), new ArrayList<Integer>(), new ArrayList<BoardSquareType>(),new ArrayList<BoardField>());

	}

	public static int GetRequiredSquaresForSize(Vector2 BoardSize) {
		int boardArea = BoardSize.getX() * 2 + (BoardSize.getY() - 2) * 2;
		return boardArea;
	}

	public static Board BuildBoard(BoardConfig config) {
		return BuildBoard(config.Size, config.names, config.values, config.types,config.fields);
	}

	public static Board BuildBoard(Vector2 size, ArrayList<String> names, ArrayList<Integer> values,
			ArrayList<BoardSquareType> types,ArrayList<BoardField> fields) {

		Board b = new Board(size);
		BoardSquare previousSquare = null;
		BoardSquare firstSquare = null;
		// Add sqyares to the board
		for (int i = 0; i < b.getRequiredSquares(); i++) {

			//Default values
			String sqName = "SQ" + i;
			int sqValue = i * 5;
			BoardSquareType sqType = BoardSquareType.PROPERTY;
			BoardField sqField = null;

			//Check to see if we have supplied non-default values
			if (names.size() > i) {
				sqName = names.get(i);
			}

			if (values.size() > i) {
				sqValue = values.get(i);
			}

			if (types.size() > i) {
				sqType = types.get(i);
			}
			
			if(fields.size() > i) {
				sqField = fields.get(i);
			}

			BoardSquare sq = null;
			
			switch (sqType) {
			case CHANCE:
				sq = new CardSquare(sqName, sqValue,sqType, CardType.CHANCE);
				break;
			case COMMUNITY_CHEST:
				sq = new CardSquare(sqName, sqValue,sqType, CardType.COMMUNITY_CHEST);
				break;
			case JAIL:
				sq = new EffectSquare(sqName,sqValue,sqType);
				break;
			case PROPERTY:
				sq = new PropertySquare(sqName, sqValue,sqType);
				break;
			case START:
				sq = new LogicSquare(sqName,sqValue,sqType) {
					
					
					@Override
					public void onPassSquare(Player p) {
						int rawResources = Constants.Resources.StartingResources;
						for(ActiveEffect ae : p.GetActiveEffects()) {
							rawResources = ae.GetEffect().onCalculatePassGo(rawResources);
						}
						p.AddResources(rawResources);
						GameManager.AddToTextLog("You Passed GO and Collect " + rawResources + " devs");
						GameManager.AddToTextLog("You now have " + p.GetResources() + " devs");
					};
				};
				break;
			case SUBSIDY:
				sq = new SubsidySquare(sqName,sqValue,sqType);
				break;
			case UNIVERSITY:
				sq = new UtilitySquare(sqName, sqValue,sqType);
				break;
			case GOJAIL:
				sq = new LogicSquare(sqName,sqValue,sqType) {
					@Override
					public void onEndSquare(Player p) {
						if(!p.HasEffect(EffectType.JAILED)) {
							GameManager.AddToTextLog("You have been sent to Jail");
							p.AddEffect(EffectType.JAILED, Constants.Cards.JailedTurns);
							p.TeleportToNearest(BoardSquareType.JAIL);
						}
					}
				};
				break;
			default:
				System.out.println("Error no method setup for: " + sqType);
				break;
			}

			b.AddBoardSqaure(sq);
			
			if(sqField != null) {
				sqField.AddSquare(sq);
			}

			if (i == 0) {
				firstSquare = sq;
			}

			if (previousSquare != null) {
				previousSquare.NextSquare = sq;
			}

			sq.PreviousSquare = previousSquare;

			previousSquare = sq;

		}

		firstSquare.PreviousSquare = previousSquare;
		previousSquare.NextSquare = firstSquare;

		return b;

	}

}
