package com.arthurmaciel.desafio.exception;

public class InvalidIDException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "There is an invalid ID in this file";
	}

}
