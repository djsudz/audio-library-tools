package za.co.djsudz.audiolibraryreader.tag;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.junit.jupiter.api.Test;

class AudioTagReaderTest {

	@Test
	void testReadAudioTag() {
		File sampleAudioFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		assertTrue(sampleAudioFile.exists());
		
		AudioTagReader audioTagReader = new AudioTagReader();
		
		try {
			Tag audioTag = audioTagReader.readAudioTag(sampleAudioFile);
			System.out.println("Tag Field Count: " + audioTag.getFieldCount());
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
