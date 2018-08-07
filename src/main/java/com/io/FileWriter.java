package com.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class FileWriter {

	public static void Write(String path, List<String> lines) {
		try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path));){
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			 writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	   
	}
	
}