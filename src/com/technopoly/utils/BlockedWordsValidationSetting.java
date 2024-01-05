package com.technopoly.utils;

import java.util.ArrayList;

public class BlockedWordsValidationSetting extends InputValidationSetting {
	private ArrayList<String> Blocked;
	
	public BlockedWordsValidationSetting(ArrayList<String> blocked) {
		Blocked = blocked;
	}
	
	private boolean isBlockedWord(String s) {
		for(String b : Blocked) {
			if(s.toUpperCase().equals(b.toUpperCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public ArrayList<String> getValidationErrors(String raw) {
		
		ArrayList<String> errors = new ArrayList<String>();
		  
		    if(isBlockedWord(raw)) {
		    	errors.add(raw + " is not an allowed word");
		    }
		
		return errors;
	}
}
