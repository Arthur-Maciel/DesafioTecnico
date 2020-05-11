package com.arthurmaciel.desafio.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileDAO {

	public Scanner readFile(String path) {
		Scanner reader;
		try {
			reader = new Scanner(new File(path));
			
			return reader;
		} catch (FileNotFoundException e) {
			return null;
		}				
	}
	
	public void writeFile() {
		
	}
}
