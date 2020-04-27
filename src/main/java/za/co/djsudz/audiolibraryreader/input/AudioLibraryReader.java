/**
 * 
 */
package za.co.djsudz.audiolibraryreader.input;

import java.io.File;

import za.co.djsudz.audiolibraryreader.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryReader {
	
	private AudioLibrary fAudioLibrary;
	
	public AudioLibraryReader() {
		//Default Constructor
	}
	
	public AudioLibraryReader(File audioLibraryDirectory) {
		this.fAudioLibrary = new AudioLibrary(audioLibraryDirectory);
	}
	
	public AudioLibraryReader(String audioLibraryPath) {
		this.fAudioLibrary = new AudioLibrary(audioLibraryPath);
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
