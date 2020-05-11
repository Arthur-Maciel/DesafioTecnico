package com.arthurmaciel.desafio.app;

import java.io.FileNotFoundException;

import com.arthurmaciel.desafio.file.FileDecoder;

public class Application {
	
	public static void main(String args[]) throws FileNotFoundException {
		FileDecoder decoder = new FileDecoder();
		
		decoder.decodeFile();
	}
	
}
