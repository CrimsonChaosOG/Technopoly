package com.technopoly.data.board;

import java.util.ArrayList;

import com.technopoly.utils.Vector2;

public class BoardConfig {
	
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Integer> values = new ArrayList<Integer>();
	ArrayList<BoardSquareType> types = new ArrayList<BoardSquareType>();
	ArrayList<BoardField> fields = new ArrayList<BoardField>();
	
	public Vector2 Size = Vector2.Zero();
	
	public BoardConfig(Vector2 size) {
		Size = size;
	}
	
	
	public void AddSquareConfig(String name,int value,BoardSquareType type) {
		AddSquareConfig(name,value,type,null);
	}
	
	public void AddSquareConfig(String name,int value,BoardSquareType type,BoardField field) {
		names.add(name);
		values.add(value);
		types.add(type);
		fields.add(field);
	}
	
	

}
