/**
 * 
 */
package za.co.djsudz.audiolibrarytools.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import za.co.djsudz.audiolibrarytools.messaging.MessageEmitter;
import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;

/**
 * @author Sudheer
 *
 */
class AudioLibraryTest {
	
	static MessageLogger messageLogger;
	
	@BeforeAll
	static void init() {
		MessageEmitter messageEmitterMock = Mockito.mock(MessageEmitter.class);
		Mockito.doNothing().when(messageEmitterMock).send(ArgumentMatchers.anyString());
		
		messageLogger = new MessageLogger(messageEmitterMock);
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.model.AudioLibrary#AudioLibrary()}.
	 */
	@Test
	void testAudioLibrary() {
		AudioLibrary audioLibrary = new AudioLibrary(messageLogger);
		assertTrue(audioLibrary.getAudioFiles().isEmpty());
		assertEquals(0, audioLibrary.getAudioFileTotal());
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.model.AudioLibrary#AudioLibrary(za.co.djsudz.audiolibrarytools.messaging.MessageLogger)}.
	 */
	@Test
	void testAudioLibraryFile() {
		File sampleAudioDirectory = new File("src/test/resources/sampleAudioDirectory");
		assertTrue(sampleAudioDirectory.exists());
		
		AudioLibrary audioLibary = new AudioLibrary(messageLogger);
		audioLibary.setAudioFilesFromSourceDir(sampleAudioDirectory, false);
		
		assertNotNull(audioLibary.getAudioFiles());
		assertEquals(1, audioLibary.getAudioFiles().size());
		assertEquals(1, audioLibary.getAudioFileTotal());
		
		AudioFile sampleAudioFile = audioLibary.getAudioFiles().get(0);
		String sampleAudioFileName = "sampleAudioMp3.mp3";
		assertTrue(sampleAudioFileName.equals(sampleAudioFile.getFile().getName()));
	}

}
