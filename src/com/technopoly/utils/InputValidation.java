package com.technopoly.utils;

import java.util.ArrayList;

public class InputValidation {
	
	private ArrayList<InputValidationSetting> settings = new ArrayList<InputValidationSetting>();
	
	public InputValidation(ArrayList<InputValidationSetting> settings) {
		this.setSettings(settings);
	}

	public ArrayList<InputValidationSetting> getSettings() {
		return settings;
	}

	public void setSettings(ArrayList<InputValidationSetting> settings) {
		this.settings = settings;
	}

	public ArrayList<InputValidationError> GetErrors(String raw) {
		ArrayList<InputValidationError> errors = new ArrayList<InputValidationError>();
		
		for(InputValidationSetting s : settings){
			if(!s.isValid(raw)) {
				errors.add(new InputValidationError(s,s.getValidationErrors(raw)));
			}
		}
		
		return errors;
	}

}
