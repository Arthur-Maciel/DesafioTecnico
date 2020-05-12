package com.arthurmaciel.desafio.exception;

public class FileDoesNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "There is no file with this name on this path";
	}
}
