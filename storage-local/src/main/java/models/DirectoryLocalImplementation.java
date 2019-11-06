package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import common.FileUtil;
import specs.DirectoryManipulation;
import users.AbstractUser;

//LOCAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class DirectoryLocalImplementation implements DirectoryManipulation {
	
	private String root; // Local storage root directory path
	private String[] forbidden;//Saamo za uplaod zip
	

	public String[] getForbidden() {
		return forbidden;
	}

	public void setForbidden(String[] forbidden) {
		this.forbidden = forbidden;
	}

	/**
	 * Creates new directory on given path.
	 * 
	 * @param name Directory name
	 * @param path Dir's path on the storage
	 * @param user Current user
	 */
	@Override
	public void createDirectory(String name, String path, AbstractUser user) {
		if (user.getPrivileges()[0]) {
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
		else {
			System.out.println("You do not have permission to create new directory!");
		}
		
	}

	/**
	 * Deletes directory on given path.
	 * 
	 * @param path Dir's path on the storage
	 * @param user Current user
	 */
	@Override
	public void deleteDirectory(String path, AbstractUser user) {
		if (user.getPrivileges()[1]) {
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
		else {
			System.out.println("You do not have permission to delete directories!");
		}
	}

	/**
	 * Uploads directory from chosen path to given path on storage.
	 * 
	 * @param selectedPath Path of the chosen directory
	 * @param destinationPath Path on the storage where directory will be uploaded to
	 * @param user Current user
	 */
	@Override
	public void uploadDirectory(String selectedPath, String destinationPath, AbstractUser user) {
		if (user.getPrivileges()[2]) {
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
						//Files.copy(oldPath, Paths.get(newPath + File.separator + name));
						FileUtils.copyDirectory(oldPath.toFile(), Paths.get(newPath + File.separator + name).toFile()); // Copy directory with contents
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
		else {
			System.out.println("You do not have permission to upload!");
		}
	}

	/**
	 * Downloads directory to given path.
	 * 
	 * @param selectedPath Path of the directory on storage
	 * @param destinationPath Path where directory will be downloaded to
	 * @param user Current user
	 */
	@Override
	public void downloadDirectory(String selectedPath, String destinationPath, AbstractUser user) {
		if (user.getPrivileges()[3]) {
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
						//Files.copy(oldPath, Paths.get(newPath + File.separator + name));
						FileUtils.copyDirectory(oldPath.toFile(), Paths.get(newPath + File.separator + name).toFile());
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
		else {
			System.out.println("You do not have permission to download!");
		}
	}
	
	/**
	 * Zips and uploads zipped directory.
	 * 
	 * @param selectedPath Path of the chosen directory
	 * @param destinationPath Path on the storage where zipped directory will be uploaded to
	 * @param user Current user
	 */
	@SuppressWarnings("static-access")
	@Override
	public void uploadZipDirectory(String selectedPath, String destinationPath, AbstractUser user) {
		if (user.getPrivileges()[2]) {
			FileUtil util = new FileUtil();
			String name = selectedPath.substring(selectedPath.lastIndexOf(File.separator) + 1);
			util.zipDirectory(new File(selectedPath), destinationPath, name);
		}
		else {
			System.out.println("You do not have permission to upload!");
		}
	}

	/**
	 * Prints names of all files from given directory and it's sub directories.
	 * 
	 * @param path Path of the chosen directory
	 */
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
	
	/**
	 * Prints names of files with given extension from given directory.
	 * If @param extension equals "all" then function should print all files from given directory.
	 * 
	 * @param path Path of the chosen directory
	 * @param extension Chosen extension for display
	 */
	@Override
	public void listFiles(String path, String extension) {
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

	/**
	 * Prints names of all directories from chosen path.
	 * 
	 * @param path Path of the chosen directory
	 */
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
	
	/**
	 * Initializes storage, creates root directory on given path...
	 * 
	 * @param path Path of the directory that will be used as storage root
	 * @param forbiddenExtensions Array of strings that represents forbidden extensions for files
	 * @param user Creator/Admin of the storage
	 */
	public void initStorage(String path, String[] forbiddenExtensions, AbstractUser user) {
		// Create root directory
		System.out.println(path.substring(0, path.lastIndexOf(File.separator)));
		String parPath = path.substring(0, path.lastIndexOf(File.separator)); // path where root will be created
		
		String rootName = path.substring(path.lastIndexOf(File.separator) + 1);
		createDirectory(rootName, parPath, user);
		setRoot(path);
		
		// Set user to admin
		user.setAdmin(true);
		
		// Create file with forbidden extensions and username of storage creator
		File fileInfo = new File(path + File.separator + "storage-info.txt");
		try {
			FileOutputStream fos = new FileOutputStream(fileInfo, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(user.getUsername());
			bw.newLine();
			for (int i = 0 ; i < forbiddenExtensions.length ; i++) {
				bw.write(forbiddenExtensions[i]);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create file with all users
		File fileAccs = new File(path + File.separator + "accounts.log");
		try {
			FileOutputStream fos = new FileOutputStream(fileAccs, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			// add creator to the file
			bw.write(user.getUsername() + "/" + user.getPassword() + "/" + true + "/" + true + "/" + true + "/" + true);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets path of the root directory.
	 * @return Path of the root directory
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * Used for setting root's path.
	 * @param Root's path
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	@Override
	public void initStorage(String storageName, AbstractUser user) {
		// TODO Auto-generated method stub
		
	}

}