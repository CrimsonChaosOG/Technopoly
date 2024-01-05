package com.technopoly.utils;

import java.util.ArrayList;

public class WhitelistValidationSetting extends InputValidationSetting {

	private char[] AllowedChars;
	
	public WhitelistValidationSetting(char[] allowedChars) {
		AllowedChars = allowedChars;
	}
	
	private boolean isWhitelistedChar(char c) {
		for(char a : AllowedChars) {
			if(a == c) {
				return true;
			}
		}
		
		return false;
	}
	
	private String buildTest() {
		String s = "";
		for(char a : AllowedChars) {
			s+=a;
		}
		
		return s;
	}
	
	@Override
	public ArrayList<String> getValidationErrors(String raw) {
		
		ArrayList<String> errors = new ArrayList<String>();
		//System.out.println(raw + "-" + buildTest());
		for (int i = 0; i < raw.length(); i++){
		    char c = raw.charAt(i);        
		    if(!isWhitelistedChar(c)) {
		    	errors.add(c + " is not an allowed character");
		    	//System.out.println("Illegal char");
		    }
		}
		
		return errors;
	}
	
}
