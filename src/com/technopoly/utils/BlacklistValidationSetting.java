package com.technopoly.utils;

import java.util.ArrayList;

public class BlacklistValidationSetting extends InputValidationSetting {
	
	private char[] BlockedChars;
	
	public BlacklistValidationSetting(char[] blockedChars) {
		BlockedChars = blockedChars;
	}
	
	private boolean isBlacklistedChar(char c) {
		for(char a : BlockedChars) {
			if(a == c) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public ArrayList<String> getValidationErrors(String raw) {
		
		ArrayList<String> errors = new ArrayList<String>();
		
		for (int i = 0; i < raw.length(); i++){
		    char c = raw.charAt(i);        
		    if(isBlacklistedChar(c)) {
		    	errors.add(c + " is not an allowed character");
		    }
		}
		
		return errors;
	}
}
