package com.technopoly.ui;

import java.util.ArrayList;

import com.technopoly.data.ActionItem;
import com.technopoly.utils.Input;
import com.technopoly.utils.InputValidation;
import com.technopoly.utils.InputValidationSetting;
import com.technopoly.utils.Utils;
import com.technopoly.utils.Vector2;

public class Window extends UIContainer {

	
	public ArrayList<ActionItem> WindowActions = new ArrayList<ActionItem>();
	public String OutputText ="";
	public InputValidation InputValidation;
	
	public Window(String name,Vector2 size) {
		super(name,Vector2.Zero(),size);
	}
	
	
	@Override
	public String ConvertToText() {
		//Some reused values, only need to fetch once
		int Width = getWidth();
		int Height = getHeight();
		
		//Create an empty string of the right length
		String lines = Utils.CreateEmptyStringOfLength(Width * Height);
		return addChildElements(lines);
	}
	
	public void Draw() {
		int Width = getWidth();
		int Height = getHeight();
		String text = ConvertToText();
		char[] textChars = text.toCharArray();
		int currentLine = 0;
		for(int i = 0; i < Width * Height; i++) {
			System.out.print(textChars[i]);
			currentLine++;
			if(currentLine >= Width) {
				System.out.print('\n');
				currentLine = 0;
			}
		}
	}
	
	public InputValidation GetInputValidation() {
		
		if(InputValidation == null) {
			ArrayList<InputValidationSetting> settings = new ArrayList<InputValidationSetting>();
			InputValidation = new InputValidation(settings);
		}
		

		
		return InputValidation;
	}


	public void TakeInput() {
		String input = Input.GetValidString(OutputText, GetInputValidation());
		HandleInput(input);
	}
	
	public void HandleInput(String input) {
		
	}
	
	
}
