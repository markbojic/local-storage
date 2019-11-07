package models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonWriter;

import common.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import specs.FileManipulation;
import users.AbstractUser;

public class FileLocalImplementation implements FileManipulation {
	
	private String[] forbiddenExtensions; // Forbidden Extensions 
	private String root; // Storage Root

	/**
	 * Creates new file on a given path.
	 * 
	 * @param name File's name
	 * @param path Path of the directory where file will be stored
	 * @param user Current user
	 */
	@Override
	public void createFile(String name, String path, AbstractUser user) {
		if (user.getPrivileges()[0]) {
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
							String extension = FilenameUtils.getExtension(name);
							boolean forbidden = false;
							//System.out.println("File extension: " + extension);
							for (int i = 0 ; i < forbiddenExtensions.length ; i++) {
								if (extension.equals(forbiddenExtensions[i])) {
									forbidden = true;
									break;
								}
							}
							if (forbidden) {
								System.out.println("You can not create file with " + extension + " extension!");
							} 
							else {
								// Create file
								Files.createFile(Paths.get(destPath + File.separator + name));
								System.out.println("File " + name + " created successfully!");
								// Create meta
								CreateMetaFile(user.getUsername(), name, extension, path);
							}
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
		else {
			System.out.println("You do not have permission to create new file!");
		}
	}

	/**
	 * Deletes file on given path.
	 * 
	 * @param path
	 * @param user Current user
	 */
	@Override
	public void deleteFile(String path, AbstractUser user) {
		if (user.getPrivileges()[1]) {
			Path filePath;
			
			if (path == null || path.equals("")) {
				System.out.println("The path is not valid!");
			}
			else {
				filePath = Paths.get(path);
				//System.out.println(filePath);
				try {
					// Delete file
					Files.deleteIfExists(filePath);
					System.out.println("File " + filePath.getFileName() + " deleted!");
					// Delete meta
					String metaPath = path.substring(0, path.lastIndexOf(".")) + "-meta.json";
					//System.out.println(metaPath);
					Files.deleteIfExists(Paths.get(metaPath));
				} catch (IOException e) {
					System.out.println("Fail...");
					e.printStackTrace();
				}
			}
		}
		else {
			System.out.println("You do not have permission to delete files!");
		}
	}

	/**
	 * Uploads file from selected path to desired path on storage.
	 * 
	 * @param selectedPath Path of chosen file
	 * @param destinationPath Path where file will be stored
	 * @param user Current user
	 */
	@Override
	public void uploadFile(String selectedPath, String destinationPath, AbstractUser user) {
		if (user.getPrivileges()[2]) {
			Path oldPath, newPath;
			
			if (selectedPath == null || selectedPath.equals("") || destinationPath == null || destinationPath.equals("")) {
				System.out.println("The path is not valid!");
			}
			else {
				oldPath = Paths.get(selectedPath);
				newPath = Paths.get(destinationPath);
				String name = selectedPath.substring(selectedPath.lastIndexOf(File.separator) + 1);
				//System.out.println("File name: " + name);
				String extension = FilenameUtils.getExtension(name);
				if (Files.exists(oldPath) && Files.exists(newPath) && !Files.exists(Paths.get(newPath + File.separator + name))) {
					try {
						boolean forbidden = false;
						for (int i = 0 ; i < forbiddenExtensions.length ; i++) {
							if (extension.equals(forbiddenExtensions[i])) {
								forbidden = true;
								break;
							}
						}
						if (forbidden) {
							System.out.println("You can not upload file with " + extension + " extension!");
						} 
						else {
							Files.copy(oldPath, Paths.get(newPath + File.separator + name));
							System.out.println("File " + name + " uploaded to " + newPath);
							// Create meta
							CreateMetaFile(user.getUsername(), name, extension, destinationPath);
						}
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
		else {
			System.out.println("You do not have permission to upload files!");
		}
	}

	/**
	 * Downloads file to given path.
	 * 
	 * @param selectedPath File's path on storage
	 * @param destinationPath Download file on this path
	 * @param user Current user
	 */
	@Override
	public void downloadFile(String selectedPath, String destinationPath, AbstractUser user) {
		if (user.getPrivileges()[3]) {
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
		else {
			System.out.println("You do not have permission to download files!");
		}
	}
	
	/**
	 * Zips multiple files and uploads archive to storage.
	 * 
	 * @param filePaths List of paths for files that will be uploaded
	 * @param destinationPath Path where archive will be stored
	 * @param zipName Archive name.
	 * @param user Current user
	 */
	@SuppressWarnings("static-access")
	@Override
	public void uploadMultipleFilesZip(String[] filePaths, String destinationPath, String zipName, AbstractUser user) {
		if (user.getPrivileges()[2]) {
			FileUtil util = new FileUtil();
			util.zipFiles(filePaths, destinationPath, zipName);
		}
		else {
			System.out.println("You do not have permission to upload files!");
		}
	}
	
	/**
	 * Creates file with meta data for created/uploaded file.
	 * 
	 * @param user Who created/uploaded the file
	 * @param fileName Name of the original file
	 * @param fileType Type(extension) of the original file
	 * @param filePath Path of the directory where file will be stored
	 */
	private void CreateMetaFile(String user, String fileName, String fileType, String filePath) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		//System.out.println(formatter.format(date));
		
		int end = fileName.length() - fileType.length() - 1;
		
		try {
			JsonWriter writer = new JsonWriter(new FileWriter(filePath  + File.separator + fileName.substring(0, end) + "-meta.json"));
			writer.beginObject();
			writer.name("autor").value(user);
			writer.name("file").value(fileName.substring(0, end));
			writer.name("type").value(fileType);
			writer.name("date").value(formatter.format(date));
			writer.endObject();
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets array of forbidden extensions.
	 * 
	 * @return Array of forbidden extensions
	 */
	@Override
	public String[] getForbiddenExtensions() {
		return forbiddenExtensions;
	}

	/**
	 * Used for setting forbidden extensions.
	 * 
	 * @param forbiddenExtensions Array of forbidden extensions
	 */
	@Override
	public void setForbiddenExtensions(String[] forbiddenExtensions) {
		this.forbiddenExtensions = forbiddenExtensions;
	}
	
	/**
	 * Gets root's path.
	 * 
	 * @return Root path
	 */
	@Override
	public String getRoot() {
		return root;
	}

	/**
	 * Used for setting root's path.
	 * 
	 * @param root Root path
	 */
	@Override
	public void setRoot(String root) {
		this.root = root;
	}
	
}
