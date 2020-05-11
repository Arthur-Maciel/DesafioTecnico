package com.arthurmaciel.desafio.file;

public class Report {
	
	private int qtdCostumers = 0;
	private int qtdSalesmen = 0;
	private String fileName;
	private String worstSalesman;
	
	public Report(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName + "done";
	}
	
	public int getQtdCostumers() {
		return this.qtdCostumers;
	}
	
	public int getQtdSalesman() {
		return this.qtdSalesmen;
	}

	public String getMostExpensiveSaleId() {
		return worstSalesman;
	}
	
	public void getWorstSalesman() {
		
	}
	
	public void addQtdCostumers() {
		this.qtdCostumers++;
	}
	
	public void addQtdSalesman() {
		this.qtdSalesmen++;
	}

	public void setMostExpensiveSaleId() {
		
	}
	
	public void setWorstSalesman(String worstSalesman) {
		this.worstSalesman = worstSalesman;
	}
	
	@Override
	public String toString() {
		return "Report [qtdCostumers=" + qtdCostumers + ", qtdSalesmen=" + qtdSalesmen + ", fileName=" + fileName
				+ ", worstSalesman=" + worstSalesman + "]";
	}
}
