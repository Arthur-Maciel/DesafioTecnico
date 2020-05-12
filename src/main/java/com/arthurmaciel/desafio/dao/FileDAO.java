package com.arthurmaciel.desafio.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.arthurmaciel.desafio.exception.FileDoesNotExistException;
import com.arthurmaciel.desafio.exception.FolderDoesNotExistException;
import com.arthurmaciel.desafio.model.Report;

public class FileDAO {
	
	private static final String OUTPUTPATH = "/home/arthur/eclipse-workspace/DesafioTecnico/report/";
	private static final String FILEEXTENSION = ".report.dat";
	private List<String> filesDone;
	
	public FileDAO() {
		filesDone = new LinkedList<String>();
	}

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
	        printWriter.println("• Amount of clients in the input file: " + report.getQtdCostumers());
	        printWriter.println("• Amount of salesman in the input file: " + report.getQtdSalesman() );
	        printWriter.println("• ID of the most expensive sale: " + report.getMostExpensiveSaleId());
	        printWriter.println("• Worst salesman: " + report.getWorstSalesman());
	        printWriter.close();
	        
	        filesDone.add(report.getFileName());
		} catch (IOException e) {
			throw new FolderDoesNotExistException();
		}
	}
	
	public List<String> getFilesDone(){
		return this.filesDone;
	}
}
