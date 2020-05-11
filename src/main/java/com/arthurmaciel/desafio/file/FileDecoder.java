package com.arthurmaciel.desafio.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arthurmaciel.desafio.model.Costumer;
import com.arthurmaciel.desafio.model.Sale;
import com.arthurmaciel.desafio.model.Salesman;
import com.arthurmaciel.desafio.model.Item;
import com.arthurmaciel.desafio.exception.FolderIsEmptyException;
import com.arthurmaciel.desafio.exception.InvalidIDException;

public class FileDecoder {
	
	private FileDAO fileDAO;
	private Report report;
	private List<Costumer> costumers;
	private List<Salesman> salesmen;
	private List<Sale> sales;
	private static final String SPLITLINE = "ç";
	
	public FileDecoder() {
		fileDAO = new FileDAO();
		costumers = new LinkedList<Costumer>();
		salesmen = new LinkedList<Salesman>();
		sales = new LinkedList<Sale>();
	}
	
	public void decodeFile() {
		for(String file : getFilesFromFolder()) {
			List<String> lines = fileDAO.readFile(file);
			report = new Report(file);
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
			
			decodeWorstSalesman();
			mostExpensiveSale();
		}
	}
	
	private void decodeCostumer(String[] salesman) {
		salesmen.add(new Salesman(salesman[1], salesman[2], salesman[3]));
		report.addQtdSalesman();		
	}
	
	private void decodeSalesman(String[] costumer) {
		costumers.add(new Costumer(costumer[1], costumer[2], costumer[3]));
		report.addQtdCostumers();
	}
	
	private void decodeWorstSalesman() {
		report.setWorstSalesman(salesmen.stream().reduce((salesman1, salesman2) -> salesSalesman(salesman1.getName()) < salesSalesman(salesman2.getName()) ? salesman1 : salesman2).get().getName());
	}
	
	private void decodeSale(String[] sale) {
		String salesmanName = sale[3];
		Sale aux = new Sale(sale[1], findSalesman(salesmanName));
		
		String[] items = sale[2].replaceAll("\\[|\\]", "").split(",");

		for(int i = 0; i < items.length ; i++) {
			String[] itemDetail = items[i].split("-");
			
			aux.addItem(new Item(itemDetail[0], itemDetail[1], itemDetail[2]));
		}
		
		sales.add(aux);
	}
	
	private Salesman findSalesman(String name) {
		return salesmen.stream().filter(p -> p.getName().equals(name)).findFirst().get();
	}
	
	private void mostExpensiveSale() {
		report.setMostExpensiveSaleId(sales.stream().reduce((sale1, sale2) -> sale1.getTotal() > sale2.getTotal() ? sale1 : sale2).get().getSaleId());
	}
	
	private double salesSalesman(String salesmanName){
		double total = 0;

		for(Sale sale : sales){
			if(sale.getSalesman().getName().equals(salesmanName)){
				total += sale.getTotal();
			}
		}
		return total;
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
