package com.technopoly.utils;

import java.io.IOException;
import java.util.ArrayList;

public class Utils {
	
	public static String CreateEmptyStringOfLength(int length) {
		String s = "";
		int index = 0;
	    while (index < length) {
	        s += " ";
	        index++;
	    }
	    return s;
	}
	
	public static boolean StringIsInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static int StringToInt(String s,int fallback) {
		try {
			int i =Integer.parseInt(s);
			return i;
		} catch (Exception e) {
			return fallback;
		}
	}
	
	public static String PadString(String in,int length) {
		return PadString(in,length," ");
	}
	
	public static String PadString(String in,int length,String padCharacter) {
		if(in.length() > length) {
			//we need to remove characters
			return in.substring(0, length -1);
		}else {
			//we need to add characters
			
			boolean addFront = true;
			
			while(in.length() < length) {
				if(addFront) {
					in = padCharacter + in;
				}else {
					in = in + padCharacter;
				}
				addFront = !addFront;
			}
			
			return in;
		}
		
	}

	public static char[] ConvertToChar(ArrayList<Integer> numbers) {
		char[] chars = new char[numbers.size()];
		for (int i = 0; i < numbers.size(); i++) {
			chars[i] = numbers.get(i).toString().charAt(0);
		}
		return chars;
	}
	

}
