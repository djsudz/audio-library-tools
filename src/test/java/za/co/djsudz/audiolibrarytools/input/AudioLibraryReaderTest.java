/**
 * 
 */
package za.co.djsudz.audiolibrarytools.input;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * @author Sudheer
 *
 */
class AudioLibraryReaderTest {

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.input.AudioLibraryReader(java.lang.String)}.
	 */
	@Test
	void testAudioLibraryReaderString() {
		String sampleAudioPath = "src/test/resources/sampleAudioDirectory";
		
		AudioLibraryReader audioLibaryReader = new AudioLibraryReader(sampleAudioPath);

		assertEquals(1, audioLibaryReader.getAudioLibrary().getAudioFileTotal());
		File sampleAudioFile = audioLibaryReader.getAudioLibrary().getAudioFiles().get(0).getFile();
		String sampleAudioFileName = "sampleAudioMp3.mp3";
		assertTrue(sampleAudioFileName.equals(sampleAudioFile.getName()));
	}
	
	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.input.AudioLibraryReader(java.io.File)}.
	 */
	@Test
	void testAudioLibraryReaderFile() {
		File sampleAudioDirectory = new File("src/test/resources/sampleAudioDirectory");
		
		AudioLibraryReader audioLibaryReader = new AudioLibraryReader(sampleAudioDirectory);

		assertEquals(1, audioLibaryReader.getAudioLibrary().getAudioFileTotal());
		File sampleAudioFile = audioLibaryReader.getAudioLibrary().getAudioFiles().get(0).getFile();
		String sampleAudioFileName = "sampleAudioMp3.mp3";
		assertTrue(sampleAudioFileName.equals(sampleAudioFile.getName()));
	}

}
