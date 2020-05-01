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
		for (AudioFile audioFile : getAudioLibrary().getAudioFiles()) {
			writeAudioFile(audioFile, destinationPath);
		}
	}
	
	private void writeAudioFile(AudioFile audioFile, String destinationPath) {
		try {
			//String basePath = "C:/Temp/Music";
			String targetPath = AudioLibraryWriterUtils.getTargetPath(audioFile, destinationPath);
			boolean created = AudioLibraryWriterUtils.createTargetPathDirectories(targetPath);
			if (created)
				AudioFileIO.writeAs(audioFile, targetPath);
		} catch (CannotWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
