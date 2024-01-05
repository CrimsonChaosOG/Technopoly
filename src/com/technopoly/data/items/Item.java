package com.technopoly.data.items;

import com.technopoly.data.Player;

public class Item {

	private ItemType type;
	
	private String ItemName;
	private String ItemDescription;
	
	public Item(String name,String desc,ItemType type) {
		this.ItemName = name;
		this.ItemDescription =desc;
		this.type = type;
	}
	
	public String getItemName() {
		return ItemName;
	}
	
	public String getItemDescription() {
		return ItemDescription;
	}
	
	public ItemType getType() {
		return type;
	}
	
	public void onUseItem(Player p) {
		
	}
	
}
