/**
 * 
 */
package za.co.djsudz.audiolibraryreader.tag;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/**
 * @author Sudheer
 *
 */
public class AudioTagReader {

	public Tag readAudioTag(File srcAudioFile) throws CannotReadException, IOException, 
							TagException, ReadOnlyFileException, InvalidAudioFrameException {
		if (srcAudioFile == null)
			return null;
		else {
			AudioFile audioFile = AudioFileIO.read(srcAudioFile);
			return audioFile.getTag();
		}
	}
}
