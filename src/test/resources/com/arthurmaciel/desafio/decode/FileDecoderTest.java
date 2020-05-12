package com.arthurmaciel.desafio.decode;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.arthurmaciel.desafio.dao.FileDAO;
import com.arthurmaciel.desafio.dao.ModelDAO;
import com.arthurmaciel.desafio.exception.InvalidIDException;
import com.arthurmaciel.desafio.model.Report;
import com.arthurmaciel.desafio.model.Salesman;

@RunWith(MockitoJUnitRunner.class)
public class FileDecoderTest {

	@Mock
	ModelDAO modelDAO;

	@Mock
	FileDAO fileDAO;

	@Mock
	Report report;

	@InjectMocks
	private FileDecoder fileDecoder;

	@Before
	public void initialize() {
		fileDecoder = new FileDecoder(modelDAO, fileDAO, report);
	}

	@Test
	public void getFileNameTest() {
		assertEquals("data", fileDecoder.getFileName("/home/data/in/data.dat"));
	}

	@Test
	public void findSalesmanTest() {
		List<Salesman> list = new LinkedList<>();

		list.add(new Salesman("00", "Arthur", "4000"));
		list.add(new Salesman("00", "Paulo", "4000"));

		Mockito.when(modelDAO.getSalesmen()).thenReturn(list);

		assertEquals("Paulo", fileDecoder.findSalesman("Paulo").getName());

	}

	@Test
	public void decodeSaleTest() {
		fileDecoder.decodeSale("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato".split("ç"));
		Mockito.verify(modelDAO).addSale(Mockito.any());
	}

	@Test
	public void salesSalesmanTest() {
		fileDecoder.salesSalesman("Paulo");	
		Mockito.verify(modelDAO).getSales();
	}

	@Test(expected = InvalidIDException.class)
	public void checkIDExceptionTest() {
		String[] aux = {"004"};

		fileDecoder.checkID(aux);
	}

	@Test
	public void checkIDCostumerTest() {
		String[] aux = {"002","2345675434544345","Jose da Silva","Rural"};
		fileDecoder.checkID(aux);

		Mockito.verify(modelDAO).addCostumer(Mockito.any());
		Mockito.verify(report).addQtdCostumers();
	}

	@Test
	public void checkIDSalesmanTest() {
		String[] aux = {"001","2345675434544345","Jose da Silva","5000"};
		fileDecoder.checkID(aux);

		Mockito.verify(modelDAO).addSalesman(Mockito.any());
		Mockito.verify(report).addQtdSalesman();
	}

	@Test
	public void decodeFileTest() {
		List<String >file = new LinkedList<>();
		file.add("001ç1234567891234çDiegoç50000");
		file.add("001ç3245678865434çRenatoç40000.99");
		file.add("002ç2345675434544345çJose da SilvaçRural");
		file.add("002ç2345675433444345çEduardoPereiraçRural");
		file.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego");
		file.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato");
		
		Mockito.when(fileDAO.readFile(Mockito.anyString())).thenReturn(file);
		
		fileDecoder.decodeFile("path");
		
		Mockito.verify(fileDAO).writeFile(Mockito.any());
	}

}
