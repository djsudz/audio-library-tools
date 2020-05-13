/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update;

import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
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
	private MessageLogger messageLogger;
	
	public AudioLibraryUpdater() {
		//Default Constructor
	}
	
	public AudioLibraryUpdater(MessageLogger messageLogger) {
		this.messageLogger = messageLogger;
		this.fRequiredImageSize = 500;
	}
	
//	public AudioLibraryUpdater(AudioLibrary audioLibrary) {
//		this.fAudioLibrary = audioLibrary;
//		this.fRequiredImageSize = 500;
//	}
	
	public void updateLibrary(AudioLibrary audioLibrary) {
		this.messageLogger.logMessage("----------------------");
		this.messageLogger.logMessage("Updating Audio Library");
		this.messageLogger.logMessage("----------------------");
		for (AudioFile audioFile : audioLibrary.getAudioFiles()) {
			updateAudioFile(audioFile);
		}
		this.messageLogger.logMessage("---------------------------");
		this.messageLogger.logMessage("Done Updating Audio Library");
		this.messageLogger.logMessage("---------------------------");
	}
	
	private void updateAudioFile(AudioFile audioFile) {
		this.messageLogger.logMessage("");
		this.messageLogger.logMessage("Updating Audio File: " + audioFile.getFile().getName());
		
		//Get Tag
		Tag tag = audioFile.getTag();
		AudioTagUpdater audioTagUpdater = new AudioTagUpdater(tag);
		
		this.messageLogger.logMessage("Checking Album Artist...");
		//Update Album Artist to be the same as the Artist
		String artist = AudioTagUpdaterUtils.getArtist(tag);
		String albumArtist = AudioTagUpdaterUtils.getAlbumArtist(tag);
		
		this.messageLogger.logMessage("Current Artist: " + artist + ", Current Album Artist: " + albumArtist);
		
		if (artist.equals(albumArtist)) {
			this.messageLogger.logMessage("Artist and Album Artist are the same, skipping");
		}
		else {
			this.messageLogger.logMessage("Updating Album Artist to match Artist");	
			audioTagUpdater.updateAlbumArtist(AudioTagUpdaterUtils.getArtist(tag));
		}
		
		//Resize Album Art
		this.messageLogger.logMessage("Checking Artwork Size...");
		Artwork artwork = AudioTagUpdaterUtils.getArtwork(tag);
		if (artwork != null) {
			try {
				artwork.setImageFromData();
				int w = artwork.getWidth();
				int h = artwork.getHeight();
				this.messageLogger.logMessage("Current Artwork size: " + w + "x" + h);
				if (w > getRequiredImageSize() || h > getRequiredImageSize()) {
					this.messageLogger.logMessage("Resizing Artwork to " + getRequiredImageSize() + "x" + getRequiredImageSize());
					ArtworkResizer.resizeArtwork(artwork, getRequiredImageSize());
					audioTagUpdater.updateArtwork(artwork);
				}
				else {
					this.messageLogger.logMessage("Artwork is smaller than required size, skipping resizing.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			this.messageLogger.logMessage("No Artwork found, skipping resizing.");
		}
		
		this.messageLogger.logMessage("=============================================================================");
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
