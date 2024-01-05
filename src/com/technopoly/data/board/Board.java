package com.technopoly.data.board;

import java.util.ArrayList;

import com.technopoly.utils.Vector2;

public class Board {

	private String boardName;
	private ArrayList<BoardSquare> squares = new ArrayList<BoardSquare>();
	private Vector2 BoardSize;
	
	public Board(Vector2 size) {
		BoardSize = size;
	}
	
	//returns if the number of squares that we have are valid for the board size defined
	public boolean hasValidNumberSquares() {		
		return(squares.size() == getRequiredSquares());
	}
	
	//returns the number of squares a board of this size needs to be valid
	public int getRequiredSquares() {
		int boardArea = BoardSize.getX() * 2 +  (BoardSize.getY() - 2) * 2;
		return boardArea;
	}
	
	
	public void SetBoardName(String name) {
		boardName = name;
	}
	
	public String GetBoardName() {
		return boardName;
	}
	
	public Vector2 GetBoardSize() {
		return BoardSize;
	}
		
	public void AddBoardSqaure(BoardSquare square) {
		squares.add(square);
	}
	
	public ArrayList<BoardSquare> GetBoardSquares(){
		return squares;
	}
	
	public BoardSquare GetStartingSquare() {
		return squares.get(0);
	}
	
}
