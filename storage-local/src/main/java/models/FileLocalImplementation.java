package models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

import specs.FileManipulation;

public class FileLocalImplementation implements FileManipulation {

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

	@Override
	public void uploadFile(String selectedPath, String destinationPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadFile(String selectedPath, String destinationPath) {
		// TODO Auto-generated method stub
		
	}

}
