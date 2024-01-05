package com.technopoly.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
	
	private static BufferedReader InputReader = new BufferedReader(new InputStreamReader(System.in));

	
	public static String GetString(String message) {
		System.out.println(message + ">>>");
		String input = "";
		try {
			input = InputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;

	}
	
	public static String GetValidStringFallback(String message,InputValidation validation,String fallback) {
		String raw = GetString(message);
		if(validation.GetErrors(raw).size() > 0) {
			//raw string is not valid - return fallback
			return fallback;
		}else {
			//raw string is valid
			return raw;
		}
		
	}
	
	public static String GetValidString(String message,InputValidation validation) {
		boolean hasValid = false;
		String raw = "";
		while(!hasValid) {
			raw = GetString(message);
			if(validation.GetErrors(raw).size() == 0) {
				//raw string is not valid - keep looping until we get a a valid input
				hasValid = true;
				}
			}
		return raw;
		
	}
	
	public static int GetIntFallBack(String message,int fallback) {
		String raw = GetString(message);
		return Utils.StringToInt(raw, fallback);
	}
	
	public static int GetInt(String message) {
		return GetIntFallBack(message,0);
	}
	
	public static int GetValidInt(String message,InputValidation validation) {
		boolean hasValid = false;
		String raw = "";
		while(!hasValid) {
			raw = GetString(message);
			if(validation.GetErrors(raw).size() == 0) {
				//raw string is not valid - keep looping until we get a a valid input
				hasValid = true;
				//System.out.println("Has valid");
			}
		}
		return Utils.StringToInt(raw, 0);
	}
	
	public static int GetValidIntFallback(String message,InputValidation validation,int fallback) {
		String raw = GetString(message);
		if(validation.GetErrors(raw).size() > 0 || !Utils.StringIsInt(raw)) {
			//raw string is not valid - return fallback
			return fallback;
		}else {
			//raw string is valid
			return Utils.StringToInt(raw, fallback);
		}
	}
	

}
