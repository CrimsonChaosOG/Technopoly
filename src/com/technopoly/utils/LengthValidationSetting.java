package com.technopoly.utils;

import java.util.ArrayList;

public class LengthValidationSetting extends InputValidationSetting {

	private int minLength;
	private int maxLength;
	
	public LengthValidationSetting(int min,int max) {
		this.minLength = min;
		this.maxLength = max;
	}
	
	@Override
	public ArrayList<String> getValidationErrors(String raw) {
		ArrayList<String> errors = new ArrayList<String>();
		
		int length = raw.length();
		
		//Can't be less than minimum
		if(length < minLength) {
			errors.add(String.format("String is less than minimum length {0} (min: {1})", length,minLength));
		}
		
		//Can't be more than maximum
		if(length > maxLength) {
			errors.add(String.format("String is greater than maximum length {0} (min: {1})", length,maxLength));
		}
		
		return errors;
		
	}
	
	

}
