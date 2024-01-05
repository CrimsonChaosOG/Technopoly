package com.technopoly.ui;

import java.util.ArrayList;

import com.technopoly.utils.Vector2;

public class UIContainer extends UIElement {

	protected ArrayList<UIElement> UIElements = new ArrayList<UIElement>();

	
	public UIContainer(String name, Vector2 position, Vector2 size) {
		super(name, position, size);
		
	}
	
	
	public void addUIElement(UIElement element) {
		UIElements.add(element);
	}
	
	protected String addChildElements(String s) {
		
		int Width = getWidth();
		int Height = getHeight();
		
		char[] lineChars = s.toCharArray();
		

		
		//Loop through each child element and build their string values
		for(UIElement e : UIElements) {
			try {
				//Get element to text
				String elementText = e.ConvertToText();
				
				int startIndex = e.Position.getX() + Width * e.Position.getY();

				int currentIndex = startIndex;
				int lineNumber = 0;
				int charsThisLine = 0;
				for(int i = 0; i<elementText.length(); i ++) {
					
					char c = elementText.charAt(i);
					try {
						lineChars[currentIndex] = c;
						currentIndex ++;
						charsThisLine++;
						if(charsThisLine >= e.Size.getX()) {
							//end of line so go to new line
							lineNumber++;
							charsThisLine = 0;
							currentIndex = startIndex + (Width * lineNumber);

						}
					} catch (Exception e2) {
						System.out.println("Error building: " + e.ElementName + " index error" + " Stats:"+e.Position.toString() + " " + startIndex +"/"+lineChars.length);
						lineChars[currentIndex -1] = 'E';
					}
					
				}
			} catch (Exception e2) {
				System.out.println("Error building: " + e.ElementName + " " + e2.getMessage());
				throw e2;
			}
			
		}
		
		s = String.valueOf(lineChars);
		
		return s;
	}

}
