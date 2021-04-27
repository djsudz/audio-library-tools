/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update;

import java.io.IOException;
import java.util.ArrayList;

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
	
	private MessageLogger messageLogger;

	public AudioLibraryUpdater(MessageLogger messageLogger) {
		this.messageLogger = messageLogger;
	}
	
	public void updateLibrary(AudioLibrary audioLibrary, int requiredImageSize) {
		messageLogger.logMessage("----------------------");
		messageLogger.logMessage("Updating Audio Library");
		messageLogger.logMessage("----------------------");
		int audioFileTotal = audioLibrary.getAudioFileTotal();
		for (int count = 0; count < audioFileTotal; count ++) {
			ArrayList<AudioFile> audioFiles = audioLibrary.getAudioFiles();
			updateAudioFile(count + 1, audioFileTotal, audioFiles.get(count), requiredImageSize);
		}
		messageLogger.logMessage("---------------------------");
		messageLogger.logMessage("Done Updating Audio Library");
		messageLogger.logMessage("---------------------------");
	}
	
	private void updateAudioFile(int audioFileNumber, int audioFileTotal, AudioFile audioFile, int requiredImageSize) {
		messageLogger.logMessage("Audio File #" + audioFileNumber + " of " + audioFileTotal);
		messageLogger.logMessage("Updating Audio File: " + audioFile.getFile().getName());
		
		//Get Tag
		Tag tag = audioFile.getTag();
		AudioTagUpdater audioTagUpdater = new AudioTagUpdater(tag);
		
		messageLogger.logMessage("Checking Album Artist...");
		//Update Album Artist to be the same as the Artist
		String artist = AudioTagUpdaterUtils.getArtist(tag);
		String albumArtist = AudioTagUpdaterUtils.getAlbumArtist(tag);
		
		messageLogger.logMessage("Current Artist: " + artist + ", Current Album Artist: " + albumArtist);
		
		if (artist.equals(albumArtist)) {
			messageLogger.logMessage("Artist and Album Artist are the same, skipping");
		}
		else {
			messageLogger.logMessage("Updating Album Artist to match Artist");	
			audioTagUpdater.updateAlbumArtist(AudioTagUpdaterUtils.getArtist(tag));
		}
		
		//Resize Album Art
		messageLogger.logMessage("Checking Artwork Size...");
		Artwork artwork = AudioTagUpdaterUtils.getArtwork(tag);
		if (artwork != null) {
			try {
				artwork.setImageFromData();
				int w = artwork.getWidth();
				int h = artwork.getHeight();
				messageLogger.logMessage("Current Artwork size: " + w + "x" + h);
				if (w > requiredImageSize || h > requiredImageSize) {
					messageLogger.logMessage("Resizing Artwork to " + requiredImageSize + "x" + requiredImageSize);
					ArtworkResizer.resizeArtwork(artwork, requiredImageSize);
					audioTagUpdater.updateArtwork(artwork);
				}
				else {
					messageLogger.logMessage("Artwork is smaller than required size, skipping resizing.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			messageLogger.logMessage("No Artwork found, skipping resizing.");
		}
		
		messageLogger.logMessage("=============================================================================");
	}
}
