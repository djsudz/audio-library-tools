/**
 * 
 */
package za.co.djsudz.audiolibraryreader.input;

import java.io.File;

import org.jaudiotagger.audio.AudioFileFilter;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryReader {

	public File[] getAudioFiles(File audioLibraryDirectory) {
		if (audioLibraryDirectory == null) 
			return null;
		else 
			return audioLibraryDirectory.listFiles(new AudioFileFilter(false));
	}
}
