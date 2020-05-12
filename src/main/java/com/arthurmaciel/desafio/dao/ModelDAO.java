package com.arthurmaciel.desafio.dao;

import java.util.LinkedList;
import java.util.List;

import com.arthurmaciel.desafio.model.Costumer;
import com.arthurmaciel.desafio.model.Sale;
import com.arthurmaciel.desafio.model.Salesman;

public class ModelDAO {
	
	private List<Costumer> costumers;
	private List<Salesman> salesmen;
	private List<Sale> sales;
	
	public ModelDAO() {
		costumers = new LinkedList<Costumer>();
		salesmen = new LinkedList<Salesman>();
		sales = new LinkedList<Sale>();
	}

	public List<Costumer> getCostumers() {
		return costumers;
	}

	public List<Salesman> getSalesmen() {
		return salesmen;
	}

	public List<Sale> getSales() {
		return sales;
	}
	
	public void addCostumer(Costumer costumer) {
		costumers.add(costumer);
	}

	public void addSalesman(Salesman salesman) {
		salesmen.add(salesman);
	}

	public void addSale(Sale sale) {
		sales.add(sale);
	}

}
