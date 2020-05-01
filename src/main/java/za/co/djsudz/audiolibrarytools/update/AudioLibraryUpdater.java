/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update;

import org.jaudiotagger.audio.AudioFile;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryUpdater {
	
	private AudioLibrary fAudioLibrary;
	
	public AudioLibraryUpdater() {
		//Default Constructor
	}
	
	public AudioLibraryUpdater(AudioLibrary audioLibrary) {
		this.fAudioLibrary = audioLibrary;
	}
	
	public void updateLibrary() {
		for (AudioFile audioFile : getAudioLibrary().getAudioFiles()) {
			updateAudioFile(audioFile);
		}
	}
	
	private void updateAudioFile(AudioFile audioFile) {
		//TODO: Get Tag
		
		//TODO: Update Artist to be the same as the Album Artist
		
		//TODO: Resize Album Art
		
		//TODO: Update Album Art to resized Artwork

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
