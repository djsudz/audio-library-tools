/**
 * 
 */
package za.co.djsudz.audiolibraryreader.output;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotWriteException;

import za.co.djsudz.audiolibraryreader.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
public class AudioLibaryWriter {
	
	public void writeLibrary(AudioLibrary audioLibrary) {
		for (AudioFile audioFile : audioLibrary.getAudioFiles()) {
			writeAudioFile(audioFile);
		}
	}
	
	private void writeAudioFile(AudioFile audioFile) {
		try {
			String targetPath = AudioLibraryWriterUtils.getTargetPath(audioFile);
			AudioFileIO.writeAs(audioFile, targetPath);
		} catch (CannotWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
