package com.arthurmaciel.desafio.decode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arthurmaciel.desafio.model.Costumer;
import com.arthurmaciel.desafio.model.Sale;
import com.arthurmaciel.desafio.model.Salesman;
import com.arthurmaciel.desafio.model.Item;
import com.arthurmaciel.desafio.model.Report;
import com.arthurmaciel.desafio.dao.FileDAO;
import com.arthurmaciel.desafio.dao.ModelDAO;
import com.arthurmaciel.desafio.exception.FolderDoesNotExistException;
import com.arthurmaciel.desafio.exception.InvalidIDException;

public class FileDecoder {

	private FileDAO fileDAO;
	private Report report;
	private ModelDAO modelDAO;
	private static final String SPLITLINE = "ç";
	private static final int MAXSIZESPLIT = 4;
	private static final String FILEPATH = "/home/data/in/";

	public FileDecoder() {
		fileDAO = new FileDAO();
	}

	public void decodeFile() {
		for(String file : getFilesFromFolder()) {

			List<String> lines = fileDAO.readFile(file);
			report = new Report(getFileName(file));
			modelDAO = new ModelDAO();

			for(String line : lines) {
				String[] split = line.split(SPLITLINE);
				if(split.length == MAXSIZESPLIT) {
					try {
						checkID(split);	
					}catch(InvalidIDException e) {
						System.out.println(e + "\n File:" + getFileName(file));
					}
				}					
			}

			if(modelDAO.getSalesmen().size() != 0)
				decodeWorstSalesman();

			mostExpensiveSale();
			fileDAO.writeFile(report);
		}
	}

	public void decodeFile(String file) {
		List<String> lines = fileDAO.readFile(file);
		report = new Report(getFileName(file));
		modelDAO = new ModelDAO();

		for(String line : lines) {
			String[] split = line.split(SPLITLINE);
			if(split.length == MAXSIZESPLIT) {
				try {
					checkID(split);	
				}catch(InvalidIDException e) {
					System.out.println(e + "\n File:" + getFileName(file));
				}
			}					
		}
		if(modelDAO.getSalesmen().size() != 0)
			decodeWorstSalesman();

		mostExpensiveSale();
		fileDAO.writeFile(report);
	}

	private void checkID(String[] line) {
		switch(line[0]) {
		case "001":
			decodeSalesman(line);
			break;
		case "002":
			decodeCostumer(line);
			break;
		case "003":
			decodeSale(line);
			break;
		default:
			throw new InvalidIDException();
		}
	}

	private void decodeSalesman(String[] salesman) {
		modelDAO.addSalesman(new Salesman(salesman[1], salesman[2], salesman[3]));
		report.addQtdSalesman();		
	}

	private void decodeCostumer(String[] costumer) {
		modelDAO.addCostumer(new Costumer(costumer[1], costumer[2], costumer[3]));
		report.addQtdCostumers();
	}

	private void decodeWorstSalesman() {
		report.setWorstSalesman(modelDAO.getSalesmen().stream().reduce((salesman1, salesman2) -> 
		salesSalesman(salesman1.getName()) < salesSalesman(salesman2.getName()) ? salesman1 : salesman2).get().getName());
	}

	private void decodeSale(String[] sale) {
		String salesmanName = sale[3];
		Sale aux = new Sale(sale[1], findSalesman(salesmanName));

		String[] items = sale[2].replaceAll("\\[|\\]", "").split(",");

		for(int i = 0; i < items.length ; i++) {
			String[] itemDetail = items[i].split("-");

			aux.addItem(new Item(itemDetail[0], itemDetail[1], itemDetail[2]));
		}

		modelDAO.addSale(aux);
	}

	private Salesman findSalesman(String name) {
		return modelDAO.getSalesmen().stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
	}

	private void mostExpensiveSale() {
		report.setMostExpensiveSaleId(modelDAO.getSales().stream().reduce((sale1, sale2) -> sale1.getTotal() > sale2.getTotal() ? sale1 : sale2).get().getSaleId());
	}

	private double salesSalesman(String salesmanName){
		double total = 0;

		for(Sale sale : modelDAO.getSales()){
			if(sale.getSalesman().getName().equals(salesmanName)){
				total += sale.getTotal();
			}
		}
		return total;
	}

	private List<String> getFilesFromFolder() {
		try (Stream<Path> walk = Files.walk(Paths.get(FILEPATH))) {
			return walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());
		} catch (IOException e) {
			throw new FolderDoesNotExistException();
		}
	}

	private String getFileName(String path) {
		String[] aux = path.split("/");
		String[] file = aux[aux.length-1].split("\\.");
		return file[0];
	}

}
