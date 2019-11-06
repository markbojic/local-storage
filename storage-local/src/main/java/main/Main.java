package main;

import models.DirectoryLocalImplementation;
import models.FileLocalImplementation;
import models.User;

/**
 * 
 * Used for tests only 
 *
 */
public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		FileLocalImplementation localFile = new FileLocalImplementation();
		DirectoryLocalImplementation localDir = new DirectoryLocalImplementation();
		
		// File tests
		// Create test
		//localFile.createFile("TestFajl.txt", "C:\\Users\\R930\\Desktop", user);
		// Upload test
		//localFile.uploadFile("C:\\Users\\R930\\Desktop\\TestFajl.txt", "C:\\Users\\R930\\Desktop\\Test Folder", user);
		// Delete test
		//localFile.deleteFile("C:\\Users\\R930\\Desktop\\TestFajl.txt", user);
		// Download test
		//localFile.downloadFile("C:\\Users\\R930\\Desktop\\Test Folder\\TestFajl.txt", "C:\\Users\\R930\\Desktop", user);
		
		// Ovako ce da bude u test app-u
		// pita za path do storage
		// pita za username i password
		// Create user 
		boolean[] niz = { (true), (true), (true), (true) };
		User user = new User("admin", "password");
		user.setPrivileges(niz);
		// pita za zabranjene extenzije
		String[] exts = {("exe"), ("jar")};
		// pozove initStorage
		//localDir.initStorage("C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage", exts, user);
		System.out.println("-------------------------------------");
		localDir.initStorage("C:\\LocalStorage",exts, user);
		// setuje extenzije
		//localFile.setForbiddenExtensions(exts);
		// kreiranje i brisanje korisnika
		//user.createUser("test", "pass", niz, localDir.getRoot());
		//user.createUser("test", "pass2", niz, localDir.getRoot());
		//user.createUser("marko", "12345", niz, localDir.getRoot());
		//user.listAllUsers(localDir.getRoot());
		//user.deleteUser("test", localDir.getRoot());
		// other tests
		//localFile.createFile("test.txt", "C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage", user);
		//localFile.createFile("test.exe", "C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage", user);
		//localDir.createDirectory("Novi Dir", "C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage", user);
		//localFile.uploadFile("C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage\\test.txt", "C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage\\Novi Dir", user);
		//localDir.uploadDirectory("C:\\Users\\R930\\Desktop\\Test Folder\\TestDir", "C:\\Users\\R930\\Desktop\\Test Folder\\LocalStorage", user);
		
		// Dir tests
		// Create test
		//localDir.createDirectory("TestDir", "C:\\Users\\R930\\Desktop");
		// Upload test
		//localDir.uploadDirectory("C:\\Users\\R930\\Desktop\\TestDir", "C:\\Users\\R930\\Desktop\\Test Folder");
		// Delete test
		//localDir.deleteDirectory("C:\\Users\\R930\\Desktop\\TestDir");
		// Download test
		//localDir.downloadDirectory("C:\\Users\\R930\\Desktop\\Test Folder\\TestDir", "C:\\Users\\R930\\Desktop");
		/*
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
		*/
	}

}
