package com.technopoly.utils;

import java.util.ArrayList;

public class InputTester {

	public static void main(String[] args) {
		
//		String input1 = Input.GetString("Input 1");
//		System.out.println(input1);
//		
//		ArrayList<InputValidationSetting> input2ValidationSettings = new ArrayList<InputValidationSetting>();
//		input2ValidationSettings.add(new LengthValidationSetting(0,5));
//		InputValidation input2Validation = new InputValidation(input2ValidationSettings);
//		String input2 = Input.GetValidStringFallback("Input 2",input2Validation, "failed");
//		System.out.println(input2);
//		
//		ArrayList<InputValidationSetting> input3ValidationSettings = new ArrayList<InputValidationSetting>();
//		input3ValidationSettings.add(new LengthValidationSetting(0,5));
//		InputValidation input3Validation = new InputValidation(input3ValidationSettings);
//		String input3 = Input.GetValidString("Input 3",input3Validation);
//		System.out.println(input3);
		
		ArrayList<InputValidationSetting> input4ValidationSettings = new ArrayList<InputValidationSetting>();
		char[] input4Blacklist = {'a', 'd', 'a', 'm'};
		input4ValidationSettings.add(new BlacklistValidationSetting(input4Blacklist));
		InputValidation input4Validation = new InputValidation(input4ValidationSettings);
		String input4 = Input.GetValidString("Input 4",input4Validation);
		System.out.println(input4);
		
		System.out.println("Done");
	}

}
