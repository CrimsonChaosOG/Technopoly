package com.technopoly.ui;

import java.util.ArrayList;

import com.technopoly.utils.Vector2;

public class UIElement {

	protected String ElementName;
	
	protected Vector2 Position;
	
	protected Vector2 Size;
		
	public UIElement(String name,Vector2 position,Vector2 size) {
		ElementName = name;
		Position = position;
		Size = size;
	}
	
	public String ConvertToText() {
		
		String s = "";
		for(int i = 0;i<Size.getX() * Size.getY();i++) {
			s+="*";
		}
		
		return s;
	}
	
	public int getWidth() {
		return Size.getX();
	}
	public void setWidth(int width) {
		Size.setX(width);
	}
	
	public int getHeight() {
		return Size.getY();
	}
	public void setHeight(int height) {
		Size.setY(height);
	}

	
}
