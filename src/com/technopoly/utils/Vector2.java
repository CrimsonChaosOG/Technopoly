package com.technopoly.utils;

public class Vector2 {

	private int X;
	private int Y;
	
	public Vector2(int x,int y) {
		X = x;
		Y = y;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public void setX(int x) {
		X = x;
	}
	
	public void setY(int y) {
		Y = y;
	}
	
	public static Vector2 Zero() {
		return new Vector2(0,0);
	}
	
	@Override
	public String toString() {
		return "["+getX()+","+getY()+"]";
	}
}
