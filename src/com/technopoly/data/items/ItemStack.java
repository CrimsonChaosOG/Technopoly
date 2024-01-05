package com.technopoly.data.items;

public class ItemStack {

	private Item item;
	private int amount;
	
	public ItemStack(Item i,int a) {
		item = i;
		amount = a;
	}
	
	public Item GetItem() {
		return item;
	}
	
	public int GetAmount() {
		return amount;
	}
	
	public String GetDisplay() {
		return item.getItemName() + " x" + GetAmount();
	}
	
}
