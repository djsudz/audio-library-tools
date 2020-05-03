/**
 * 
 */
package za.co.djsudz.audiolibrarytools.output;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

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
		String audioLibraryPath = "src/test/resources/sampleAudioDirectory";
		String destinationPath = "src/test/resources/sampleOutputDirectory/Music";
		AudioLibrary audioLibrary = new AudioLibrary(audioLibraryPath, true);
		
		AudioLibraryWriter audioLibraryWriter = new AudioLibraryWriter(audioLibrary);
		audioLibraryWriter.writeLibrary(destinationPath);
		
		File outputFile = new File(destinationPath + "/" + AudioLibraryWriterConstants.NEW_MUSIC + "/2020/05/sampleAudioMp3.mp3");
		assertTrue(outputFile.exists());
		while (!outputFile.getName().equals("Music")) {
			outputFile.delete();
			outputFile = outputFile.getParentFile();
		}
	}

}
