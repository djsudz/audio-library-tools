/**
 * 
 */
package za.co.djsudz.audiolibrarytools.output;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

/**
 * @author Sudheer
 *
 */
class AudioLibaryWriterTest {

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.output.AudioLibraryWriter#writeLibrary(za.co.djsudz.audiolibrarytools.model.AudioLibrary)}.
	 */
	@Test
	void testWriteLibrary() {
		MessageLogger messageLogger = new MessageLogger();
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
