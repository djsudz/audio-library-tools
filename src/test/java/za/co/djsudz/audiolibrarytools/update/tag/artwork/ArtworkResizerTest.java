/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update.tag.artwork;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Sudheer
 *
 */
class ArtworkResizerTest {
	
	private static Tag tag;
	
	@BeforeAll
	private static void init() {
		File sampleFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		
		//Check Sample Files Exist
		assertTrue(sampleFile.exists());
				
		try {
			AudioFile sampleAudioFile = AudioFileIO.read(sampleFile);
			tag = sampleAudioFile.getTag();
			assertNotNull(tag);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.update.tag.artwork.ArtworkResizer#resizeArtwork(org.jaudiotagger.tag.images.Artwork, int)}.
	 */
	@Test
	void testResizeArtwork() {
		Artwork artwork = tag.getFirstArtwork();
		
		artwork.setImageFromData();
		assertEquals(1297, artwork.getHeight());
		assertEquals(1297, artwork.getWidth());
		
		try {
			ArtworkResizer.resizeArtwork(artwork, 500);
			
			artwork.setImageFromData();
			
			assertEquals(500, artwork.getHeight());
			assertEquals(500, artwork.getWidth());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
