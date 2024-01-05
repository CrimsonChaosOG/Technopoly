package com.technopoly.ui;

import com.technopoly.utils.Utils;
import com.technopoly.utils.Vector2;

public class Label extends UIElement {

	private String Display = "";
	private LabelAlignment Alignment = LabelAlignment.LEFT;
	
	public Label(String name,String display, Vector2 position, Vector2 size) {
		super(name, position, size);
		Display = display;
	}
	
	public Label(String name,String display, Vector2 position, Vector2 size,LabelAlignment al) {
		super(name, position, size);
		Display = display;
		Alignment = al;
	}
	
	@Override
	public String ConvertToText() {
		switch (Alignment) {
		case CENTRE: {
			int length = Size.getX();
			String str = Utils.PadString(Display, length-2, " ");
			return str;
		}
		case LEFT:{
			return Display;
		}
		case RIGHT:{
			int length = Size.getX();
			String str = Utils.PadString("", length-Display.length(), " ");
			str+=Display;
			return str;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + Alignment);
		}
	}

}
