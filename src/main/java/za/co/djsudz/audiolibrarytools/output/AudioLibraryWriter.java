/**
 * 
 */
package za.co.djsudz.audiolibrarytools.output;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotWriteException;

import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryWriter {
	
	private MessageLogger messageLogger;
	private AudioLibrary fAudioLibrary;
	
	public AudioLibraryWriter() {
		//Default Constructor
	}
	
	public AudioLibraryWriter(MessageLogger messageLogger) {
		this.messageLogger = messageLogger;
	}
	
	public AudioLibraryWriter(AudioLibrary audioLibrary) {
		this.fAudioLibrary = audioLibrary;
	}
	
	public void writeLibrary(AudioLibrary audioLibrary, String destinationPath) {
		this.messageLogger.logMessage("----------------------");
		this.messageLogger.logMessage("Writing Audio Library");
		this.messageLogger.logMessage("----------------------");
		
		this.messageLogger.logMessage("Destination Path is: " + destinationPath);
		for (AudioFile audioFile : audioLibrary.getAudioFiles()) {
			writeAudioFile(audioFile, destinationPath);
		}
		this.messageLogger.logMessage("--------------------------");
		this.messageLogger.logMessage("Done Writing Audio Library");
		this.messageLogger.logMessage("--------------------------");
	}
	
	private void writeAudioFile(AudioFile audioFile, String destinationPath) {
		try {
			this.messageLogger.logMessage("Writing Audio File: " + audioFile.getFile().getName());
			
			String targetPath = AudioLibraryWriterUtils.getTargetPath(audioFile, destinationPath);
			this.messageLogger.logMessage("Target Path is: " + targetPath);
			
			boolean created = AudioLibraryWriterUtils.createTargetPathDirectories(targetPath);
			if (created) {
				this.messageLogger.logMessage("Created Target Path");
			}		
			AudioFileIO.writeAs(audioFile, targetPath);
			this.messageLogger.logMessage("Writing Complete.");
		} catch (CannotWriteException e) {
			this.messageLogger.logMessage("An error occured while attempting to write '" + audioFile.getFile().getName() + "', skipping.");
		}
		this.messageLogger.logMessage("===================================================================");
	}

	/**
	 * @return the audioLibrary
	 */
	public AudioLibrary getAudioLibrary() {
		return fAudioLibrary;
	}

	/**
	 * @param audioLibrary the audioLibrary to set
	 */
	public void setAudioLibrary(AudioLibrary audioLibrary) {
		this.fAudioLibrary = audioLibrary;
	}
}
