package com.technopoly.ui;

import com.technopoly.utils.Vector2;

public class LabelledBox extends UIContainer {

	private String Display = "Box";
	private String BuildString = "*";
	
	public LabelledBox(String name,String display,Vector2 position,Vector2 size) {
		super(name, position, size);
		Display = display;
		
	}
	
	public LabelledBox(String name,String display,Vector2 position,Vector2 size,String buildString) {
		super(name, position, size);
		BuildString = buildString;
		Display = display;
		
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
			
			//what should we build?
			if(currentRow == 0 && currentColumn == 3) {
				//Add the title
				s+=Display;
				
				//Skip the next iterations where we already have text
				i+= Display.length() -1;
				currentColumn +=Display.length() -1;
			}else {
				//build the outline
				if(currentRow == 0 || currentRow == Height -1 || currentColumn == 0 || currentColumn == Width - 1) {
					s+=BuildString;	
				}else {
					s+=" ";
				}
			}

			
			currentColumn++;	
			
		}
		
		//Build the child content
		s = addChildElements(s);
		
		
		
		return s;
	}
	
}
