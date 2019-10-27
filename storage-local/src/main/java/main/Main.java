package main;

import models.FileLocalImplementation;

public class Main {

	public static void main(String[] args) {
		
		// Test
		FileLocalImplementation localFile = new FileLocalImplementation();
		
		// Create test
		localFile.createFile("TestFajl.txt", "C:\\Users\\R930\\Desktop");
		// Delete test
		localFile.deleteFile("C:\\Users\\R930\\Desktop\\TestFajl.txt");
		// Upload test
		
		// Download test
		
		// zip test...
	}

}
