/**
 * 
 */
package za.co.djsudz.audiolibrarytools.input;

import java.io.File;

import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryReader {
	public static AudioLibrary readAudioLibrary(File audioLibraryPath, MessageLogger messageLogger) {
		AudioLibrary audioLibrary = new AudioLibrary(messageLogger);
		audioLibrary.setAudioFilesFromSourceDir(audioLibraryPath, false);
		return audioLibrary;
	}
}
