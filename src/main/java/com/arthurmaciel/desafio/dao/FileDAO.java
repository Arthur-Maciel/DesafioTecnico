package com.arthurmaciel.desafio.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileDAO {

	public List<String> readFile(String path) {
		try {
			return Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			return null;
		}				
	}
	
	public void writeFile() {
		
	}
}
