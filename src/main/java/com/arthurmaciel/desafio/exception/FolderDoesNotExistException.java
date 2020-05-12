package com.arthurmaciel.desafio.exception;

public class FolderDoesNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "This folder, for this path does not exist";
	}

}
