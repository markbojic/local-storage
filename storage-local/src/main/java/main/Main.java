package main;

import models.FileLocalImplementation;

public class Main {

	public static void main(String[] args) {
		
		// Test
		FileLocalImplementation localFile = new FileLocalImplementation();
		
		// Create test
		localFile.createFile("TestFajl.txt", "C:\\Users\\R930\\Desktop");
		// Upload test
		localFile.uploadFile("C:\\Users\\R930\\Desktop\\TestFajl.txt", "C:\\Users\\R930\\Desktop\\Test Folder");
		// Delete test
		localFile.deleteFile("C:\\Users\\R930\\Desktop\\TestFajl.txt");
		// Download test
		localFile.downloadFile("C:\\Users\\R930\\Desktop\\Test Folder\\TestFajl.txt", "C:\\Users\\R930\\Desktop");
		// zip test dodaj iznad da prvo zipuje pa download zipovano
	}

}
