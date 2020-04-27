/**
 * 
 */
package za.co.djsudz.audiolibraryreader.output;

import java.io.File;

/**
 * @author Sudheer
 *
 */
public class AudioLibaryWriter {
	
	public void writeLibrary(File[] audioFiles) {
		for (File audioFile : audioFiles) {
			writeAudioFile(audioFile);
		}
	}
	
	private void writeAudioFile(File audioFile) {
		
	}

}
