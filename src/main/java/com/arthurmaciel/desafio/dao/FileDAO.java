package com.arthurmaciel.desafio.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.arthurmaciel.desafio.exception.FileDoesNotExistException;
import com.arthurmaciel.desafio.exception.FolderDoesNotExistException;
import com.arthurmaciel.desafio.model.Report;

public class FileDAO {
	
	private static final String OUTPUTPATH = "/home/data/out";
	private static final String FILEEXTENSION = ".report.dat";
	private static final String REPORTCOSTUMERS = "• Amount of clients in the input file: ";
	private static final String REPORTSALESMEN = "• Amount of salesman in the input file: ";
	private static final String REPORTSALE = "• ID of the most expensive sale: ";
	private static final String REPORTWORSTSALESMAN = "• Worst salesman: ";

	public List<String> readFile(String path) {
		try {
			return Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			throw new FileDoesNotExistException();
		}				
	}
	
	public void writeFile(Report report) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(OUTPUTPATH + report.getFileName() + FILEEXTENSION);
			PrintWriter printWriter = new PrintWriter(fileWriter);
	        printWriter.println(REPORTCOSTUMERS + report.getQtdCostumers());
	        printWriter.println(REPORTSALESMEN + report.getQtdSalesman() );
	        printWriter.println(REPORTSALE + report.getMostExpensiveSaleId());
	        printWriter.println(REPORTWORSTSALESMAN + report.getWorstSalesman());
	        printWriter.close();
		} catch (IOException e) {
			throw new FolderDoesNotExistException();
		}
	}
	
}
