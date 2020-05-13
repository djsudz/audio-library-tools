/**
 * 
 */
package za.co.djsudz.audiolibrarytools;

import java.io.File;

import za.co.djsudz.audiolibrarytools.input.AudioLibraryReader;
import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;
import za.co.djsudz.audiolibrarytools.output.AudioLibraryWriter;
import za.co.djsudz.audiolibrarytools.update.AudioLibraryUpdater;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryTools {
	
	MessageLogger messageLogger;
	
	public AudioLibraryTools(MessageLogger messageLogger) {
		this.messageLogger = messageLogger;
	}
	
	//private File fLibraryBasePath;
	//private File fLibraryOutputPath;
	//private int fRequiredImageSize = 500;
	
	//private static final int DEFAULT_IMAGE_SIZE = 500;
	
	/*
	 * public AudioLibraryTools(File libraryBasePath, File libraryOutputPath, int
	 * requiredImageSize) { this.fLibraryBasePath = libraryBasePath;
	 * this.fLibraryOutputPath = libraryOutputPath; this.fRequiredImageSize =
	 * requiredImageSize; }
	 * 
	 * public AudioLibraryTools(File libraryBasePath, File libraryOutputPath) {
	 * this(libraryBasePath, libraryOutputPath, DEFAULT_IMAGE_SIZE); }
	 * 
	 * public AudioLibraryTools(File libraryBasePath) { this(libraryBasePath,
	 * libraryBasePath, DEFAULT_IMAGE_SIZE); }
	 * 
	 * public AudioLibraryTools(File libraryBasePath, int requiredImageSize) {
	 * this(libraryBasePath, libraryBasePath, requiredImageSize); }
	 * 
	 * public AudioLibraryTools(String libraryBasePath, String libraryOutputPath,
	 * int requiredImageSize) { this(new File(libraryBasePath), new
	 * File(libraryOutputPath), requiredImageSize); }
	 */
	
	public void procesLibrary(String libraryBasePath, String libraryOutputPath, int requiredImageSize) {
		long startTime = System.currentTimeMillis();
		//Read Audio Library
		AudioLibrary audioLibrary = AudioLibraryReader.readAudioLibrary(new File(libraryBasePath), messageLogger);
		
		//Update Library
		AudioLibraryUpdater audioLibraryUpdater = new AudioLibraryUpdater(messageLogger);
		audioLibraryUpdater.setRequiredImageSize(requiredImageSize);
		audioLibraryUpdater.updateLibrary(audioLibrary);
		
		//Write Library
		AudioLibraryWriter audioLibraryWriter = new AudioLibraryWriter(messageLogger);
		audioLibraryWriter.writeLibrary(audioLibrary, libraryOutputPath);
		
		long endTime = System.currentTimeMillis();
		
		long totalExecTime = (endTime - startTime) / 1000;
		
		messageLogger.logMessage("Total Execution Time: " + totalExecTime + "s");
		//System.out.println("Total Execution Time: " + totalExecTime + "s");
	}

//	/**
//	 * @return the libraryBasePath
//	 */
//	public File getLibraryBasePath() {
//		return fLibraryBasePath;
//	}
//
//	/**
//	 * @param libraryBasePath the libraryBasePath to set
//	 */
//	public void setLibraryBasePath(File libraryBasePath) {
//		this.fLibraryBasePath = libraryBasePath;
//	}
//
//	/**
//	 * @return the libraryOutputPath
//	 */
//	public File getLibraryOutputPath() {
//		return fLibraryOutputPath;
//	}
//
//	/**
//	 * @param libraryOutputPath the libraryOutputPath to set
//	 */
//	public void setLibraryOutputPath(File libraryOutputPath) {
//		this.fLibraryOutputPath = libraryOutputPath;
//	}
//
//	/**
//	 * @return the requiredImageSize
//	 */
//	public int getRequiredImageSize() {
//		return fRequiredImageSize;
//	}
//
//	/**
//	 * @param requiredImageSize the requiredImageSize to set
//	 */
//	public void setRequiredImageSize(int requiredImageSize) {
//		this.fRequiredImageSize = requiredImageSize;
//	}

}
