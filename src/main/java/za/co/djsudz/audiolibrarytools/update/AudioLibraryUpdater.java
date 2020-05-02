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
	private int fRequiredImageSize;
	
	public AudioLibraryUpdater() {
		//Default Constructor
	}
	
	public AudioLibraryUpdater(AudioLibrary audioLibrary) {
		this.fAudioLibrary = audioLibrary;
		this.fRequiredImageSize = 500;
	}
	
	public void updateLibrary() {
		System.out.println("----------------------");
		System.out.println("Updating Audio Library");
		System.out.println("----------------------");
		for (AudioFile audioFile : getAudioLibrary().getAudioFiles()) {
			updateAudioFile(audioFile);
		}
		System.out.println("---------------------------");
		System.out.println("Done Updating Audio Library");
		System.out.println("---------------------------");
	}
	
	private void updateAudioFile(AudioFile audioFile) {
		System.out.println();
		System.out.println("Updating Audio File: " + audioFile.getFile().getName());
		
		//Get Tag
		Tag tag = audioFile.getTag();
		AudioTagUpdater audioTagUpdater = new AudioTagUpdater(tag);
		
		System.out.println("Checking Album Artist...");
		//Update Album Artist to be the same as the Artist
		String artist = AudioTagUpdaterUtils.getArtist(tag);
		String albumArtist = AudioTagUpdaterUtils.getAlbumArtist(tag);
		
		System.out.println("Current Artist: " + artist + ", Current Album Artist: " + albumArtist);
		
		if (artist.equals(albumArtist)) {
			System.out.println("Artist and Album Artist are the same, skipping");
		}
		else {
			System.out.println("Updating Album Artist to match Artist");
			
			audioTagUpdater.updateAlbumArtist(AudioTagUpdaterUtils.getArtist(tag));
		}
		
		//Resize Album Art
		System.out.println("Checking Artwork Size...");
		Artwork artwork = AudioTagUpdaterUtils.getArtwork(tag);
		if (artwork != null) {
			try {
				artwork.setImageFromData();
				int w = artwork.getWidth();
				int h = artwork.getHeight();
				System.out.println("Current Artwork size: " + w + "x" + h);
				if (w > getRequiredImageSize() || h > getRequiredImageSize()) {
					System.out.println("Resizing Artwork to " + getRequiredImageSize() + "x" + getRequiredImageSize());
					ArtworkResizer.resizeArtwork(artwork, getRequiredImageSize());
					audioTagUpdater.updateArtwork(artwork);
				}
				else {
					System.out.println("Artwork is smaller than required size, skipping resizing.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No Artwork found, skipping resizing.");
		}
		
		System.out.println("=============================================================================");
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

	/**
	 * @return the requiredImageSize
	 */
	public int getRequiredImageSize() {
		return fRequiredImageSize;
	}

	/**
	 * @param requiredImageSize the requiredImageSize to set
	 */
	public void setRequiredImageSize(int requiredImageSize) {
		this.fRequiredImageSize = requiredImageSize;
	}

}
