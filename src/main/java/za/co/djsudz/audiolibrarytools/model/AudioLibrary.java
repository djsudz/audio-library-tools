/**
 * 
 */
package za.co.djsudz.audiolibrarytools.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileFilter;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;

/**
 * @author Sudheer
 *
 */
public class AudioLibrary {
	
	private ArrayList<AudioFile> fAudioFiles;
	private int fAudioFileTotal;
	private MessageLogger messageLogger;
	
	public AudioLibrary(MessageLogger messageLogger) {
		this.fAudioFiles = new ArrayList<>();
		this.fAudioFileTotal = 0;
		this.messageLogger = messageLogger;
	}
	
	public void setAudioFilesFromSourceDir(File audioLibraryDirectory, boolean recurse) {	
		messageLogger.logMessage("-------------------------------");
		messageLogger.logMessage("Reading Audio Library Directory");
		messageLogger.logMessage("-------------------------------");
		File[] files = audioLibraryDirectory.listFiles(new AudioFileFilter(recurse));
		for (File file : files) {
			try {
				messageLogger.logMessage("Found Audio File: " + file.getName());
				this.fAudioFiles.add(AudioFileIO.read(file));
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e) {
				messageLogger.logMessage("Unable to read file: " + file.getAbsolutePath() + ", Skipping it...");
				e.printStackTrace();
			}
		}
		setAudioFileTotal(this.fAudioFiles.size());
		messageLogger.logMessage("Total Audio Files: " + this.fAudioFileTotal);
		messageLogger.logMessage("------------------------------------");
		messageLogger.logMessage("Done Reading Audio Library Directory");
		messageLogger.logMessage("------------------------------------");
	}
	
	/**
	 * @return the audioFiles
	 */
	public ArrayList<AudioFile> getAudioFiles() {
		return fAudioFiles;
	}
	/**
	 * @param audioFiles the audioFiles to set
	 */
	public void setAudioFiles(ArrayList<AudioFile> audioFiles) {
		this.fAudioFiles = audioFiles;
	}
	/**
	 * @return the audioFileTotal
	 */
	public int getAudioFileTotal() {
		return fAudioFileTotal;
	}
	/**
	 * @param audioFileTotal the audioFileTotal to set
	 */
	private void setAudioFileTotal(int audioFileTotal) {
		this.fAudioFileTotal = audioFileTotal;
	}
}
