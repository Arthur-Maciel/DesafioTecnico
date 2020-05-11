package com.arthurmaciel.desafio.file;

public class Report {
	
	private int qtdCostumers = 0;
	private int qtdSalesmen = 0;
	private String fileName;
	private String worstSalesman;
	private String saleID;
	
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
		return this.saleID;
	}
	
	public String getWorstSalesman() {
		return this.worstSalesman;
	}
	
	public void addQtdCostumers() {
		this.qtdCostumers++;
	}
	
	public void addQtdSalesman() {
		this.qtdSalesmen++;
	}

	public void setMostExpensiveSaleId(String saleID) {
		this.saleID = saleID;
	}
	
	public void setWorstSalesman(String worstSalesman) {
		this.worstSalesman = worstSalesman;
	}
	
	@Override
	public String toString() {
		return "Report [saleID=" + saleID + ", qtdCostumers=" + qtdCostumers + ", qtdSalesmen=" + qtdSalesmen
				+ ", fileName=" + fileName + ", worstSalesman=" + worstSalesman + "]";
	}
	
}
