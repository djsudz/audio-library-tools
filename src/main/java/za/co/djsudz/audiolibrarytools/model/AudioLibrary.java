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
	
	public AudioLibrary(File audioLibraryDirectory) {
		this(audioLibraryDirectory, true);
	}
	
	public AudioLibrary(File audioLibaryDirectory, boolean recurse) {
		setAudioFiles(getAudioFilesFromSourceDir(audioLibaryDirectory, recurse));
		setAudioFileTotal(getAudioFiles().size());
	}
	
	public AudioLibrary(String audioLibaryPath) {
		this(audioLibaryPath, true);
	}
	
	public AudioLibrary(String audioLibraryPath, boolean recurse) {
		this(new File(audioLibraryPath), recurse);
	}
	
	public ArrayList<AudioFile> getAudioFilesFromSourceDir(File audioLibraryDirectory, boolean recurse) {
		ArrayList<AudioFile> audioFiles = new ArrayList<>();
		
		System.out.println("-------------------------------");
		System.out.println("Reading Audio Library Directory");
		System.out.println("-------------------------------");
		File[] files = audioLibraryDirectory.listFiles(new AudioFileFilter(recurse));
		for (File file : files) {
			try {
				System.out.println("Found Audio File: " + file.getName());
				audioFiles.add(AudioFileIO.read(file));
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e) {
				System.out.println("Unable to read file: " + file.getAbsolutePath() + ", Skipping it...");
				e.printStackTrace();
			}
		}
		System.out.println("\nTotal Audio Files: " + audioFiles.size());
		System.out.println("------------------------------------");
		System.out.println("Done Reading Audio Library Directory");
		System.out.println("------------------------------------");
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
