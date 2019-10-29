package main;

import models.DirectoryLocalImplementation;
import models.FileLocalImplementation;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		// Test
		FileLocalImplementation localFile = new FileLocalImplementation();
		
		// Create test
		//localFile.createFile("TestFajl.txt", "C:\\Users\\R930\\Desktop");
		// Upload test
		//localFile.uploadFile("C:\\Users\\R930\\Desktop\\TestFajl.txt", "C:\\Users\\R930\\Desktop\\Test Folder");
		// Delete test
		//localFile.deleteFile("C:\\Users\\R930\\Desktop\\TestFajl.txt");
		// Download test
		//localFile.downloadFile("C:\\Users\\R930\\Desktop\\Test Folder\\TestFajl.txt", "C:\\Users\\R930\\Desktop");
		// zip test dodaj iznad da prvo zipuje pa download zipovano
		
		DirectoryLocalImplementation localDir = new DirectoryLocalImplementation();
		
		// Create test
		//localDir.createDirectory("TestDir", "C:\\Users\\R930\\Desktop");
		// Upload test
		//localDir.uploadDirectory("C:\\Users\\R930\\Desktop\\TestDir", "C:\\Users\\R930\\Desktop\\Test Folder");
		// Delete test
		//localDir.deleteDirectory("C:\\Users\\R930\\Desktop\\TestDir");
		// Download test
		//localDir.downloadDirectory("C:\\Users\\R930\\Desktop\\Test Folder\\TestDir", "C:\\Users\\R930\\Desktop");
		
		// ListFiles test
		System.out.println("ListFiles - given extension");
		localDir.listFiles("C:\\Users\\R930\\Desktop\\Test Folder", ".html");
		System.out.println("ListFiles - all extensions");
		localDir.listFiles("C:\\Users\\R930\\Desktop\\Test Folder\\TestDir", "all");
		localDir.listFiles("C:\\Users\\R930\\Desktop\\Test Folder\\nekifolder", "all");
		System.out.println("--------------------");
		// ListDirectories test
		System.out.println("ListDirectories");
		localDir.listDirectories("C:\\Users\\R930\\Desktop\\Test Folder");
		localDir.listDirectories("C:\\Users\\R930\\Desktop\\Test Folder\\TestDir");
		System.out.println("--------------------");
		// ListAllFiles test
		System.out.println("ListAllFiles");
		localDir.listAllFiles("C:\\Users\\R930\\Desktop\\Test Folder");
	}

}
