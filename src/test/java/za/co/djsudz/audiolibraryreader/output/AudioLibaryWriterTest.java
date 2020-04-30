/**
 * 
 */
package za.co.djsudz.audiolibraryreader.output;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import za.co.djsudz.audiolibrarytools.model.AudioLibrary;
import za.co.djsudz.audiolibrarytools.output.AudioLibaryWriter;
import za.co.djsudz.audiolibrarytools.output.AudioLibraryWriterConstants;

/**
 * @author Sudheer
 *
 */
class AudioLibaryWriterTest {

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.output.AudioLibaryWriter#writeLibrary(za.co.djsudz.audiolibrarytools.model.AudioLibrary)}.
	 */
	@Test
	void testWriteLibrary() {
		String audioLibraryPath = "src/test/resources/sampleAudioDirectory";
		String destinationPath = "D:/Temp/Music";
		AudioLibrary audioLibrary = new AudioLibrary(audioLibraryPath, true);
		
		AudioLibaryWriter audioLibraryWriter = new AudioLibaryWriter();
		audioLibraryWriter.writeLibrary(audioLibrary, destinationPath);
		
		File outputFile = new File(destinationPath + "/" + AudioLibraryWriterConstants.NEW_MUSIC + "/2020/04/sampleAudioMp3.mp3");
		assertTrue(outputFile.exists());
		while (!outputFile.getName().equals("Music")) {
			outputFile.delete();
			outputFile = outputFile.getParentFile();
		}
	}

}
