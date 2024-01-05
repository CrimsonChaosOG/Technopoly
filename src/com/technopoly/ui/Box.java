package com.technopoly.ui;

import com.technopoly.utils.Vector2;

public class Box extends UIContainer {
	private String BuildString = "*";
	
	public Box(String name,Vector2 position,Vector2 size) {
		super(name, position, size);
		
	}
	
	public Box(String name,Vector2 position,Vector2 size,String buildString) {
		super(name, position, size);
		BuildString = buildString;
		
	}
	
	@Override
	public String ConvertToText() {
		
		int Width = getWidth();
		int Height = getHeight();
		
		String s = "";
		
		int currentRow = 0;
		int currentColumn = 0;
		//Build the outline and title
		for(int i = 0;i<Size.getX() * Size.getY();i++) {

			//Keep track of what row we are on
			if(currentColumn == Width) {
				currentRow++;
				currentColumn = 0;
			}
			
			//build the outline
				if(currentRow == 0 || currentRow == Height -1 || currentColumn == 0 || currentColumn == Width - 1) {
					s+=BuildString;	
				}else {
					s+=" ";
				}
				
				currentColumn++;	
			}
		
			
			
		
		//Build the child content
		s = addChildElements(s);
		
		
		
		return s;
	}
}
