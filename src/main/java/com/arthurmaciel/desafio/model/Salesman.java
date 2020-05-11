package com.arthurmaciel.desafio.model;

public class Salesman {

	private String name;
	private String cpf;
	private double salary;
	
	public Salesman(String name, String cpf, String separetedLine) {
		this.name = name;
		this.cpf = cpf;
		this.salary = Double.valueOf(separetedLine);
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Salesman [name=" + name + ", cpf=" + cpf + ", salary=" + salary + "]";
	}
	
}
