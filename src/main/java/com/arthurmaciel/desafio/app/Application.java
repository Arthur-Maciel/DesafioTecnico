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
	
	private static final String PATH = "/home/data/in";
	
	public static void main(String args[]) {
		FileDecoder decoder = new FileDecoder();
		
		decoder.decodeFile();
		
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path path = Paths.get(PATH);			
			
			path.register(
			          watchService, 
			            StandardWatchEventKinds.ENTRY_CREATE);
			 
			        WatchKey key;
			        while ((key = watchService.take()) != null) {
			        	
			        	for (WatchEvent<?> event : key.pollEvents()) {
			                System.out.println(
			                  "Event kind:" + event.kind().name() 
			                    + ". File affected: " + event.context() + ".");
			                Thread.sleep(2000);
			                decoder.decodeFile(PATH+event.context().toString());
			            }
			            key.reset();
			        }
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to keep watching the folder");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Failed to keep watching the folder");
		}
	}
	
}
