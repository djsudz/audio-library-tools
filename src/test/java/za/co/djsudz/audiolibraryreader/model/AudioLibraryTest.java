/**
 * 
 */
package za.co.djsudz.audiolibraryreader.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.junit.jupiter.api.Test;

/**
 * @author Sudheer
 *
 */
class AudioLibraryTest {

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.model.AudioLibrary#AudioLibrary()}.
	 */
	@Test
	void testAudioLibrary() {
		AudioLibrary audioLibrary = new AudioLibrary();
		assertTrue(audioLibrary.getAudioFiles().isEmpty());
		assertEquals(0, audioLibrary.getAudioFileTotal());
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.model.AudioLibrary#AudioLibrary(java.io.File)}.
	 */
	@Test
	void testAudioLibraryFile() {
		File sampleAudioDirectory = new File("src/test/resources/sampleAudioDirectory");
		assertTrue(sampleAudioDirectory.exists());
		
		AudioLibrary audioLibary = new AudioLibrary(sampleAudioDirectory);
		
		assertNotNull(audioLibary.getAudioFiles());
		assertEquals(1, audioLibary.getAudioFiles().size());
		assertEquals(1, audioLibary.getAudioFileTotal());
		
		AudioFile sampleAudioFile = audioLibary.getAudioFiles().get(0);
		String sampleAudioFileName = "sampleAudioMp3.mp3";
		assertTrue(sampleAudioFileName.equals(sampleAudioFile.getFile().getName()));
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.model.AudioLibrary#AudioLibrary(java.lang.String)}.
	 */
	@Test
	void testAudioLibraryString() {
		String sampleAudioPath = "src/test/resources/sampleAudioDirectory";
		
		AudioLibrary audioLibary = new AudioLibrary(sampleAudioPath);
		
		assertNotNull(audioLibary.getAudioFiles());
		assertEquals(1, audioLibary.getAudioFiles().size());
		assertEquals(1, audioLibary.getAudioFileTotal());
		
		AudioFile sampleAudioFile = audioLibary.getAudioFiles().get(0);
		String sampleAudioFileName = "sampleAudioMp3.mp3";
		assertTrue(sampleAudioFileName.equals(sampleAudioFile.getFile().getName()));
	}

}
