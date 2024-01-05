package com.technopoly.data;

import java.util.ArrayList;

import com.technopoly.utils.Input;
import com.technopoly.utils.InputValidation;
import com.technopoly.utils.InputValidationSetting;
import com.technopoly.utils.LengthValidationSetting;
import com.technopoly.utils.Utils;
import com.technopoly.utils.WhitelistValidationSetting;

public class ActionItem {

	private String Display;
	
	public ActionItem(String display) {
		setDisplay(display);
	}
	
	public String getDisplay() {
		return Display;
	}

	public void  setDisplay(String display) {
		Display = display;
	}
	
	//What to do when a player selects this action
	public void onAction() {
		
	}

	
	public static void Decide(ArrayList<ActionItem> windowActions,String optionText) {
		
		ArrayList<InputValidationSetting> optionValidationSettings = new ArrayList<InputValidationSetting>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int i = 1;
		for(ActionItem item : windowActions) {
			numbers.add(i);
			i++;
		}
		//TODO Seem to be able to crash with ' char????
		char[] validChars = Utils.ConvertToChar(numbers);
		WhitelistValidationSetting whiteListValidation = new WhitelistValidationSetting(validChars);
		LengthValidationSetting lengthValid = new LengthValidationSetting(1, 3);
		optionValidationSettings.add(whiteListValidation);
		optionValidationSettings.add(lengthValid);
		InputValidation optionValidation = new InputValidation(optionValidationSettings);
		int option = Input.GetValidInt(optionText, optionValidation);
		
		if(option != -1) {
			windowActions.get(option-1).onAction();
		}
		
	}
	
}
