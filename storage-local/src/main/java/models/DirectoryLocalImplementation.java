package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

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
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			File dir = new File(path);
			Collection<File> allFiles = FileUtils.listFiles(dir, null, true);
			System.out.println(Arrays.toString(allFiles.toArray()));
		}
	}
	
	@Override
	public void listFiles(String path, String extension) {
		// TODO dodati da proveri extenziju ako treba
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			File dir = new File(path);
			File[] listOfFiles = dir.listFiles();
			boolean empty = true;
			//System.out.println(listOfFiles.length);
			// List all files
			if (extension.equals("all")) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						System.out.println("File: " + listOfFiles[i].getName());
						empty = false;
					}
				}
				if (empty) System.out.println("Directory " + dir.getName() + " does not contain any files.");
			}
			// List files with given extension
			else {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						if (listOfFiles[i].getName().endsWith(extension)) {
							System.out.println("File: " + listOfFiles[i].getName());
							empty = false;
						}
					}
				}
				if (empty) System.out.println("Directory " + dir.getName() + " does not contain any files with given extension.");
			}
			
		}
	}

	@Override
	public void listDirectories(String path) {
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			File dir = new File(path);
			File[] listOfDirs = dir.listFiles();
			boolean empty = true;
			
			for (int i = 0; i < listOfDirs.length; i++) {
				if (listOfDirs[i].isDirectory()) {
					System.out.println("Dir: " + listOfDirs[i].getName());
					empty = false;
				}
			}
			if (empty) System.out.println("Directory " + dir.getName() + " does not contain any subdirectory.");
		}
	}

}
