/**
 * 
 */
package za.co.djsudz.audiolibrarytools.input;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
class AudioLibraryReaderTest {

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.input.AudioLibraryReader#readAudioLibrary(java.io.File)}.
	 */
	@Test
	void testAudioLibraryReaderString() {
		MessageLogger messageLogger = new MessageLogger();
		File audioLibraryPath = new File("src/test/resources/sampleAudioDirectory");
		AudioLibrary audioLibrary = AudioLibraryReader.readAudioLibrary(audioLibraryPath, messageLogger);
		assertNotNull(audioLibrary);
		assertEquals(1, audioLibrary.getAudioFileTotal());
	}

}
