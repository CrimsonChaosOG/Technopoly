package com.technopoly.utils;

import java.util.ArrayList;

public class InputValidationError {

	private InputValidationSetting Setting;
	private ArrayList<String> Reasons;
	
	public InputValidationError(InputValidationSetting setting, ArrayList<String> reasons) {
		setSetting(setting);
		setReasons(reasons);
	}

	public ArrayList<String> getReasons() {
		return Reasons;
	}

	public void setReasons(ArrayList<String> reasons) {
		Reasons = reasons;
	}

	public InputValidationSetting getSetting() {
		return Setting;
	}

	public void setSetting(InputValidationSetting setting) {
		Setting = setting;
	}
	
}
