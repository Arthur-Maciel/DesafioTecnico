package com.arthurmaciel.desafio.exception;

public class FolderIsEmptyException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "There is no file on this folder path";
	}

}
