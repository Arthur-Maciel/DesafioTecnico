package com.arthurmaciel.desafio.model;

public class Item {

	private String itemId;
	private int quantity;
	private double price;
	
	public Item(String itemId, String quantity, String price) {
		this.itemId = itemId;
		this.quantity = Integer.parseInt(quantity);
		this.price = Double.valueOf(price);
	}

	public String getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
