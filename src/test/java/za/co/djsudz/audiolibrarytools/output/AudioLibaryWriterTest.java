/**
 * 
 */
package za.co.djsudz.audiolibrarytools.output;

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
class AudioLibaryWriterTest {
	
	static MessageLogger messageLogger;
	
	@BeforeAll
	static void init() {
		MessageEmitter messageEmitterMock = Mockito.mock(MessageEmitter.class);
		Mockito.doNothing().when(messageEmitterMock).send(ArgumentMatchers.anyString());
		
		messageLogger = new MessageLogger(messageEmitterMock);
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.output.AudioLibraryWriter#writeLibrary(za.co.djsudz.audiolibrarytools.model.AudioLibrary)}.
	 */
	@Test
	void testWriteLibrary() {
		File audioLibraryPath = new File("src/test/resources/sampleAudioDirectory");
		String destinationPath = "src/test/resources/sampleOutputDirectory/Music";
		AudioLibrary audioLibrary = new AudioLibrary(messageLogger);
		audioLibrary.setAudioFilesFromSourceDir(audioLibraryPath, true);
		
		AudioLibraryWriter audioLibraryWriter = new AudioLibraryWriter(messageLogger);
		audioLibraryWriter.writeLibrary(audioLibrary, destinationPath);
		
		File outputFile = new File(destinationPath + "/" + AudioLibraryWriterConstants.NEW_MUSIC + "/2020/05/sampleAudioMp3.mp3");
		assertTrue(outputFile.exists());
		while (!outputFile.getName().equals("Music")) {
			outputFile.delete();
			outputFile = outputFile.getParentFile();
		}
	}

}
