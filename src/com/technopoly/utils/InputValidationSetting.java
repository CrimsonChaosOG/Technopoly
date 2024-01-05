package com.technopoly.utils;

import java.util.ArrayList;

public class InputValidationSetting {
	
	public boolean isValid(String raw) {
		ArrayList<String> errors = getValidationErrors(raw);
		return errors.size() == 0;
	}

	public ArrayList<String> getValidationErrors(String raw) {
		return new ArrayList<String>();
	}

}
