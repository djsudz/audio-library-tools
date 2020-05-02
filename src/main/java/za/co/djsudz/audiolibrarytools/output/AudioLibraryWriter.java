/**
 * 
 */
package za.co.djsudz.audiolibrarytools.output;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotWriteException;

import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryWriter {
	
	private AudioLibrary fAudioLibrary;
	
	public AudioLibraryWriter() {
		//Default Constructor
	}
	
	public AudioLibraryWriter(AudioLibrary audioLibrary) {
		this.fAudioLibrary = audioLibrary;
	}
	
	public void writeLibrary(String destinationPath) {
		System.out.println("----------------------");
		System.out.println("Writing Audio Library");
		System.out.println("----------------------");
		
		System.out.println("Destination Path is: " + destinationPath);
		for (AudioFile audioFile : getAudioLibrary().getAudioFiles()) {
			writeAudioFile(audioFile, destinationPath);
		}
		System.out.println("--------------------------");
		System.out.println("Done Writing Audio Library");
		System.out.println("--------------------------");
	}
	
	private void writeAudioFile(AudioFile audioFile, String destinationPath) {
		try {
			System.out.println("Writing Audio File: " + audioFile.getFile().getName());
			
			String targetPath = AudioLibraryWriterUtils.getTargetPath(audioFile, destinationPath);
			System.out.println("Target Path is: " + targetPath);
			
			boolean created = AudioLibraryWriterUtils.createTargetPathDirectories(targetPath);
			if (created) {
				System.out.println("Created Target Path");
			}		
			AudioFileIO.writeAs(audioFile, targetPath);
			System.out.println("Writing Complete.");
		} catch (CannotWriteException e) {
			System.out.println("An error occured while attempting to write '" + audioFile.getFile().getName() + "', skipping.");
		}
		System.out.println("===================================================================");
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
