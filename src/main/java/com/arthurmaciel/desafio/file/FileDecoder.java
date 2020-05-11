package com.arthurmaciel.desafio.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arthurmaciel.desafio.exception.FolderIsEmptyException;

public class FileDecoder {
	
	private FileDAO fileDAO;
	
	public FileDecoder() {
		fileDAO = new FileDAO();
	}
	
	private List<String> getFilesFromFolder() {
		try (Stream<Path> walk = Files.walk(Paths.get("/home/arthur/eclipse-workspace/DesafioTecnico/data/"))) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			throw new FolderIsEmptyException();
		}
	}
	
	public void decodeFile() {
		for(String file : getFilesFromFolder()) {
			fileDAO.readFile(file);
		}
	}

}
