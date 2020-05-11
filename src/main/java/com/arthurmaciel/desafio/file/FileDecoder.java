package com.arthurmaciel.desafio.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arthurmaciel.desafio.model.Costumer;
import com.arthurmaciel.desafio.exception.FolderIsEmptyException;
import com.arthurmaciel.desafio.exception.InvalidIDException;

public class FileDecoder {
	
	private FileDAO fileDAO;
	private Report report;
	private List<Costumer> costumers;
	private static final String SPLITLINE = "ç";
	
	public FileDecoder() {
		fileDAO = new FileDAO();
	}
	
	public void decodeFile() {
		for(String file : getFilesFromFolder()) {
			List<String> lines = fileDAO.readFile(file);
			
			for(String line : lines) {
				String[] split = line.split(SPLITLINE);
				
				switch(split[0]) {
				case "001":
					decodeCostumer(split);
					break;
				case "002":
					decodeSalesman(split);
					break;
				case "003":
					decodeSale(split);
					break;
					default:
						throw new InvalidIDException();
				}							
			}		
		}
	}
	
	private void decodeCostumer(String[] costumer) {
		costumers.add(new Costumer(costumer[1], costumer[2], costumer[3]));
		report.addQtdCostumers();
	}
	
	private void decodeSalesman(String[] salesman) {
		
	}
	
	private void decodeSale(String[] sale) {
		
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

}
