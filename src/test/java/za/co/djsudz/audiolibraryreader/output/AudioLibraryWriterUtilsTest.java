package za.co.djsudz.audiolibraryreader.output;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AudioLibraryWriterUtilsTest {
	
	private static AudioFile sampleAudioFile = null;
	
	@BeforeAll
	private static void init() {
		try {
			sampleAudioFile = AudioFileIO.read(new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3"));
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetLastModified() {
		String expectedLastModified = "2020/04";
		String lastModified = AudioLibraryWriterUtils.getLastModified(sampleAudioFile.getFile());
		assertTrue(expectedLastModified.equals(lastModified));
	}

	@Test
	void testGetTargetPath() {
		String expectedTargetPath = "G:/Music/New Music 2020/04/sampleAudioMp3";
		String targetPath = AudioLibraryWriterUtils.getTargetPath(sampleAudioFile);
		assertTrue(expectedTargetPath.equals(targetPath));
	}
}
