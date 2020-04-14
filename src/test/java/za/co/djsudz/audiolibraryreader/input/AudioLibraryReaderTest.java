/**
 * 
 */
package za.co.djsudz.audiolibraryreader.input;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * @author Sudheer
 *
 */
class AudioLibraryReaderTest {

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.input.AudioLibraryReader#getAudioFiles(java.io.File)}.
	 */
	@Test
	void testGetAudioFiles() {
		File sampleAudioDirectory = new File("src/test/resources/sampleAudioDirectory");
		//System.out.println("Dir Name: " + sampleAudioDirectory.getName());
		assertTrue(sampleAudioDirectory.exists());
		
		AudioLibraryReader audioLibaryReader = new AudioLibraryReader();
		File[] audioFiles = audioLibaryReader.getAudioFiles(sampleAudioDirectory);

		assertEquals(1, audioFiles.length);
		File sampleAudioFile = audioFiles[0];
		String sampleAudioFileName = "sampleAudioMp3.mp3";
		assertTrue(sampleAudioFileName.equals(sampleAudioFile.getName()));
	}

}
