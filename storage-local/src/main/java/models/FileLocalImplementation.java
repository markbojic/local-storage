package models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

import specs.FileManipulation;

public class FileLocalImplementation implements FileManipulation {

	/**
	 * Creates new file on a given path
	 * 
	 * @param name File's name
	 * @param path Path of the directory where file will be stored
	 */
	@Override
	public void createFile(String name, String path) {
		Path destPath;
		
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			destPath = Paths.get(path);
			//System.out.println(destPath);
			
			if (name != null && !name.equals("")) {
				if(Files.exists(destPath) && !Files.exists(Paths.get(destPath + File.separator + name))) {
					try {
						// provera extenzije fajla? u dependency je dodat commons-io za to 
						//String extension = FilenameUtils.getExtension(name);
						//System.out.println("File extension: " + extension);
						
						Files.createFile(Paths.get(destPath + File.separator + name));
						System.out.println("File " + name + " created successfully!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("The file already exists...");
				}
			}
			else {
				System.out.println("The file name is not valid!");
			}
		}
		
	}

	/**
	 * Deletes file on given path.
	 * 
	 * @param path
	 */
	@Override
	public void deleteFile(String path) {
		Path filePath;
		
		if (path == null || path.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			filePath = Paths.get(path);
			//System.out.println(filePath);
			try {
				Files.deleteIfExists(filePath);
				System.out.println("File " + filePath.getFileName() + " deleted!");
			} catch (IOException e) {
				System.out.println("Fail...");
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Uploads file from selected path to desired path on storage.
	 * 
	 * @param selectedPath Path of chosen file
	 * @param destinationPath Path where file will be stored
	 */
	@Override
	public void uploadFile(String selectedPath, String destinationPath) {
		// Treba posle da se doda da li sme fajl sa tom extenzijo da se upload ili ne
		Path oldPath, newPath;
		
		if (selectedPath == null || selectedPath.equals("") || destinationPath == null || destinationPath.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			oldPath = Paths.get(selectedPath);
			newPath = Paths.get(destinationPath);
			String name = selectedPath.substring(selectedPath.lastIndexOf(File.separator) + 1);
			//System.out.println("File name: " + name);
			if (Files.exists(oldPath) && Files.exists(newPath) && !Files.exists(Paths.get(newPath + File.separator + name))) {
				try {
					Files.copy(oldPath, Paths.get(newPath + File.separator + name));
					System.out.println("File " + name + " uploaded to " + newPath);
				} catch (IOException e) {
					System.out.println("Failed to upload the file...");
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Error!");
			}
		}
		
	}

	/**
	 * Downloads file to given path.
	 * 
	 * @param selectedPath File's path on storage
	 * @param destinationPath Download file on this path
	 */
	@Override
	public void downloadFile(String selectedPath, String destinationPath) {
		Path oldPath, newPath;
		
		if (selectedPath == null || selectedPath.equals("") || destinationPath == null || destinationPath.equals("")) {
			System.out.println("The path is not valid!");
		}
		else {
			oldPath = Paths.get(selectedPath);
			newPath = Paths.get(destinationPath);
			String name = selectedPath.substring(selectedPath.lastIndexOf(File.separator) + 1);
			//System.out.println("File name: " + name);
			if (Files.exists(oldPath) && Files.exists(newPath) && !Files.exists(Paths.get(newPath + File.separator + name))) {
				try {
					Files.copy(oldPath, Paths.get(newPath + File.separator + name));
					System.out.println("File " + name + " downloaded to " + newPath);
				} catch (IOException e) {
					System.out.println("Failed to download the file...");
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Error!");
			}
		}
	}

}
