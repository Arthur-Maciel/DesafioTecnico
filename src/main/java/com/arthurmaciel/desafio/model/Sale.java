package com.arthurmaciel.desafio.model;

import java.util.LinkedList;
import java.util.List;

import com.arthurmaciel.desafio.model.Item;
import com.arthurmaciel.desafio.model.Salesman;

public class Sale {

	private String saleId;
	private List<Item> items;
	private Salesman salesman;
	
	public Sale(String saleId, Salesman salesman) {
		this.saleId = saleId;
		this.salesman = salesman;
		this.items = new LinkedList<Item>();
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public String getSaleId() {
		return saleId;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public double getTotal(){
		double total = 0;

		for(Item item : items){
			total += item.getQuantity() * item.getPrice();
		}

		return total;
	}

	@Override
	public String toString() {
		return "Sale [saleId=" + saleId + ", items=" + items + ", salesmanName=" + salesman + "]";
	}
	
}
