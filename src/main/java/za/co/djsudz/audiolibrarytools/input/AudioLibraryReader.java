/**
 * 
 */
package za.co.djsudz.audiolibrarytools.input;

import java.io.File;

import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryReader {
	public static AudioLibrary readAudioLibrary(File audioLibraryPath) {
		return new AudioLibrary(audioLibraryPath);
	}
}
