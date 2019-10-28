package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import specs.DirectoryManipulation;

public class DirectoryLocalImplementation implements DirectoryManipulation {

	@Override
	public void createDirectory(String name, String path) {
		Path destPath;
		
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			destPath = Paths.get(path);
			
			if (name != null && !name.equals("")) {
				if(Files.exists(destPath) && !Files.exists(Paths.get(destPath + File.separator + name))) {
					try {
						Files.createDirectory(Paths.get(destPath + File.separator + name));
						System.out.println("Directory " + name + " created successfully!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Directory under given name already exists...");
				}
			}
			else {
				System.out.println("Name is not valid!");
			}
		}
	}

	@Override
	public void deleteDirectory(String path) {
		Path dirPath;
		
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			dirPath = Paths.get(path);

			try {
				Files.deleteIfExists(dirPath);
				System.out.println("Directory " + dirPath.getFileName() + " deleted!");
			} catch (IOException e) {
				System.out.println("Fail...");
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void uploadDirectory(String selectedPath, String destinationPath) {
		Path oldPath, newPath;
		
		if (selectedPath == null || selectedPath.equals("") || destinationPath == null || destinationPath.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			oldPath = Paths.get(selectedPath);
			newPath = Paths.get(destinationPath);
			String name = selectedPath.substring(selectedPath.lastIndexOf(File.separator) + 1);
			//System.out.println("Directory name: " + name);
			if (Files.exists(oldPath) && Files.exists(newPath) && !Files.exists(Paths.get(newPath + File.separator + name))) {
				try {
					Files.copy(oldPath, Paths.get(newPath + File.separator + name));
					System.out.println("Directory " + name + " uploaded to " + newPath);
				} catch (IOException e) {
					System.out.println("Failed to upload...");
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Error!");
			}
		}
	}

	@Override
	public void downloadDirectory(String selectedPath, String destinationPath) {
		Path oldPath, newPath;
		
		if (selectedPath == null || selectedPath.equals("") || destinationPath == null || destinationPath.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			oldPath = Paths.get(selectedPath);
			newPath = Paths.get(destinationPath);
			String name = selectedPath.substring(selectedPath.lastIndexOf(File.separator) + 1);
			//System.out.println("Directory name: " + name);
			if (Files.exists(oldPath) && Files.exists(newPath) && !Files.exists(Paths.get(newPath + File.separator + name))) {
				try {
					Files.copy(oldPath, Paths.get(newPath + File.separator + name));
					System.out.println("Directory " + name + " downloaded to " + newPath);
				} catch (IOException e) {
					System.out.println("Failed to download...");
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Error!");
			}
		}
	}

	@Override
	public void listAllFiles(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listFiles(String path, String extension) {
		// TODO Auto-generated method stub
		// https://howtodoinjava.com/java/io/java-io-filefilter-example-tutorial/
	}

	@Override
	public void listDirectories(String path) {
		// TODO Auto-generated method stub
		
	}

}
