package com.arthurmaciel.desafio.app;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.arthurmaciel.desafio.decode.FileDecoder;

public class Application {
	
	public static void main(String args[]) {
		FileDecoder decoder = new FileDecoder();
		
		decoder.decodeFile();
		
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path path = Paths.get("/home/arthur/eclipse-workspace/DesafioTecnico/data/");			
			
			path.register(
			          watchService, 
			            StandardWatchEventKinds.ENTRY_CREATE,  
			                StandardWatchEventKinds.ENTRY_MODIFY);
			 
			        WatchKey key;
			        while ((key = watchService.take()) != null) {
			        	
			        	for (WatchEvent<?> event : key.pollEvents()) {
			                System.out.println(
			                  "Event kind:" + event.kind().name() 
			                    + ". File affected: " + event.context() + ".");
			            }
			        	Thread.sleep(2000);
			        	decoder.decodeFile();
			            key.reset();
			        }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
