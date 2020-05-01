/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update;

import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import za.co.djsudz.audiolibrarytools.model.AudioLibrary;
import za.co.djsudz.audiolibrarytools.update.tag.AudioTagUpdater;
import za.co.djsudz.audiolibrarytools.update.tag.AudioTagUpdaterUtils;
import za.co.djsudz.audiolibrarytools.update.tag.artwork.ArtworkResizer;

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
		//Get Tag
		Tag tag = audioFile.getTag();
		
		//Update Album Artist to be the same as the Artist
		AudioTagUpdater audioTagUpdater = new AudioTagUpdater(tag);
		audioTagUpdater.updateAlbumArtist(AudioTagUpdaterUtils.getArtist(tag));
		
		//Resize Album Art
		try {
			Artwork artwork = AudioTagUpdaterUtils.getArtwork(tag);
			ArtworkResizer.resizeArtwork(artwork, 500);
			audioTagUpdater.updateArtwork(artwork);
		} catch (IOException e) {
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
