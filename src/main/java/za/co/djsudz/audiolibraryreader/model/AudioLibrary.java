/**
 * 
 */
package za.co.djsudz.audiolibraryreader.model;

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

/**
 * @author Sudheer
 *
 */
public class AudioLibrary {
	
	private ArrayList<AudioFile> fAudioFiles;
	private int fAudioFileTotal;
	
	public AudioLibrary() {
		//Default Constructor
		this.fAudioFiles = new ArrayList<>();
		this.fAudioFileTotal = 0;
	}
	
	public AudioLibrary(File audioLibaryDirectory) {
		setAudioFiles(getAudioFilesFromSourceDir(audioLibaryDirectory));
		setAudioFileTotal(this.fAudioFiles.size());
	}
	
	public AudioLibrary(String audioLibaryPath) {
		this(new File(audioLibaryPath));
	}
	
	private ArrayList<AudioFile> getAudioFilesFromSourceDir(File audioLibaryDirectory) {
		ArrayList<AudioFile> audioFiles = new ArrayList<>();
		
		File[] files = audioLibaryDirectory.listFiles(new AudioFileFilter(false));
		for (File file : files) {
			try {
				audioFiles.add(AudioFileIO.read(file));
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e) {
				System.out.println("Unable to read file: " + file.getAbsolutePath() + ", Skipping it...");
				e.printStackTrace();
			}
		}
		return audioFiles;
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
