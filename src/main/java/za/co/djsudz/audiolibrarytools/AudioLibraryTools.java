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
	
	public void procesLibrary(String libraryBasePath, String libraryOutputPath, int requiredImageSize) {
		long startTime = System.currentTimeMillis();
		//Read Audio Library
		AudioLibrary audioLibrary = AudioLibraryReader.readAudioLibrary(new File(libraryBasePath), messageLogger);
		
		//Update Library
		AudioLibraryUpdater audioLibraryUpdater = new AudioLibraryUpdater(messageLogger);
		audioLibraryUpdater.updateLibrary(audioLibrary, requiredImageSize);
		
		//Write Library
		AudioLibraryWriter audioLibraryWriter = new AudioLibraryWriter(messageLogger);
		audioLibraryWriter.writeLibrary(audioLibrary, libraryOutputPath);
		
		long endTime = System.currentTimeMillis();
		
		long totalExecTime = (endTime - startTime) / 1000;
		
		messageLogger.logMessage("Total Execution Time: " + totalExecTime + "s");
	}
}