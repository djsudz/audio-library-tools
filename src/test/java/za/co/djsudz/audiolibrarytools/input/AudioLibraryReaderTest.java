/**
 * 
 */
package za.co.djsudz.audiolibrarytools.input;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import za.co.djsudz.audiolibrarytools.messaging.MessageEmitter;
import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
class AudioLibraryReaderTest {
	
	static MessageLogger messageLogger;
	
	@BeforeAll
	static void init() {
		MessageEmitter messageEmitterMock = Mockito.mock(MessageEmitter.class);
		Mockito.doNothing().when(messageEmitterMock).send(ArgumentMatchers.anyString());
		messageLogger = new MessageLogger(messageEmitterMock);
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibraryreader.input.AudioLibraryReader#readAudioLibrary(java.io.File)}.
	 */
	@Test
	void testAudioLibraryReaderString() {
		File audioLibraryPath = new File("src/test/resources/sampleAudioDirectory");
		AudioLibrary audioLibrary = AudioLibraryReader.readAudioLibrary(audioLibraryPath, messageLogger);
		assertNotNull(audioLibrary);
		assertEquals(1, audioLibrary.getAudioFileTotal());
	}

}
