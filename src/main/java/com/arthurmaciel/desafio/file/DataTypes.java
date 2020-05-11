package com.arthurmaciel.desafio.file;

public enum DataTypes {
	
	SALESMAN("001"), COSTUMER("002"), SALE("003");

	private String code;
	
	DataTypes(String string) {
		this.code = string;
	}

	public String getCode() {
		return code;
	}

}
