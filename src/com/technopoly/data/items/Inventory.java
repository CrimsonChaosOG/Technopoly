package com.technopoly.data.items;

import java.util.ArrayList;

public class Inventory {

	ArrayList<Item> items = new ArrayList<Item>();
	
	public void AddItem(ItemType type) {
		Item i = ItemManager.GetItem(type);
		if(i != null) {
			items.add(i);
		}
	}
	
	public boolean HasItem(ItemType type) {
		return getItemOfType(type) != null;
	}
	
	public void RemoveItem(ItemType type) {
		Item i = getItemOfType(type);
		if(i != null) {
			items.remove(i);
		}
	}
	
	private Item getItemOfType(ItemType type) {
		for(Item i : items) {
			if(i.getType() == type) {
				return i;
			}
		}
		
		return null;
	}
	
	private int getCountOfItem(ItemType type) {
		int i = 0;
		for(Item item : items) {
			if(item.getType() == type) {
				i++;
			}
		}
		
		return i;
	}
	
	public ArrayList<ItemStack> GetItemStacks(){
		ArrayList<Item> uniqueItems = new ArrayList<Item>();
		for(Item i : items) {
			if(!uniqueItems.contains(i)) {
				uniqueItems.add(i);
			}
		}
		
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		
		for(Item i : uniqueItems) {
			//Get the counts
			ItemStack is = new ItemStack(i, getCountOfItem(i.getType()));
			stacks.add(is);
		}
		
		return stacks;

	}
}
