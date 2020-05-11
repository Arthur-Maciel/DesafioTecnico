package com.arthurmaciel.desafio.model;

public class Costumer {

	private String name;
	private String cnpj;
	private String businessArea;
	
	public Costumer(String cnpj, String name,String businessArea) {
		this.name = name;
		this.cnpj = cnpj;
		this.businessArea = businessArea;
	}

	public String getName() {
		return name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	@Override
	public String toString() {
		return "Costumer [name=" + name + ", cnpj=" + cnpj + ", businessArea=" + businessArea + "]";
	}
	
}
