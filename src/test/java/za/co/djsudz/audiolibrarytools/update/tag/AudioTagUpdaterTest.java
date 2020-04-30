/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update.tag;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import za.co.djsudz.audiolibrarytools.update.tag.AudioTagReader;
import za.co.djsudz.audiolibrarytools.update.tag.AudioTagUpdater;

/**
 * @author Sudheer
 *
 */
class AudioTagUpdaterTest {
	
	private static Tag tag;
	
	@BeforeAll
	private static void init() {
		File sampleAudioFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		
		//Check Sample Files Exist
		assertTrue(sampleAudioFile.exists());
				
		try {
			AudioTagReader audioTagReader = new AudioTagReader(sampleAudioFile);
			tag = audioTagReader.getTag();
			assertNotNull(tag);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.update.tag.AudioTagUpdater#updateArtist(org.jaudiotagger.tag.Tag, java.lang.String)}.
	 */
	@Test
	void testUpdateArtist() {
		String oldArtist = "Sample Artist";
		String newArtist = "New Artist";
		
		assertTrue(oldArtist.equals(tag.getFirst(FieldKey.ARTIST)));
		AudioTagUpdater.updateArtist(tag, newArtist);
		String newTagArtist = tag.getFirst(FieldKey.ARTIST);
		assertTrue(newTagArtist.equals(newArtist));
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.update.tag.AudioTagUpdater#updateAlbumArtist(org.jaudiotagger.tag.Tag, java.lang.String)}.
	 */
	@Test
	void testUpdateAlbumArtist() {
		String oldAlbumArtist = "Sample Album Artist";
		String newAlbumArtist = "New Album Artist";
		
		assertTrue(oldAlbumArtist.equals(tag.getFirst(FieldKey.ALBUM_ARTIST)));
		AudioTagUpdater.updateArtist(tag, newAlbumArtist);
		String newTagArtist = tag.getFirst(FieldKey.ARTIST);
		assertTrue(newTagArtist.equals(newAlbumArtist));
	}

	/**
	 * Test method for {@link za.co.djsudz.audiolibrarytools.update.tag.AudioTagUpdater#updateArtwork(org.jaudiotagger.tag.Tag, org.jaudiotagger.tag.images.Artwork)}.
	 */
	@Test
	void testUpdateArtwork() {
		File oldArtworkFile = new File("src/test/resources/sampleAudioDirectory/sample_album_art_700x700.jpg");
		File newArtworkFile = new File("src/test/resources/sampleAudioDirectory/sample_album_art_180x180.png");
		
		Artwork oldArtwork;
		Artwork newArtwork;
		
		try {
			oldArtwork = ArtworkFactory.createArtworkFromFile(oldArtworkFile);
			newArtwork = ArtworkFactory.createArtworkFromFile(newArtworkFile);
			
			Artwork tagArtwork = tag.getFirstArtwork();
			
			assertEquals(oldArtwork.getBinaryData().length, tagArtwork.getBinaryData().length);
			assertEquals(oldArtwork.getHeight(), tagArtwork.getHeight());
			assertEquals(oldArtwork.getWidth(), tagArtwork.getWidth());
			assertTrue(oldArtwork.getMimeType().equals(tagArtwork.getMimeType()));

			assertTrue(oldArtworkFile.exists());
			AudioTagUpdater.updateArtwork(tag, newArtwork);
			
			Artwork newTagArtwork = tag.getFirstArtwork();
			assertEquals(newArtwork.getBinaryData().length, newTagArtwork.getBinaryData().length);
			assertEquals(newArtwork.getHeight(), newTagArtwork.getHeight());
			assertEquals(newArtwork.getWidth(), newTagArtwork.getWidth());
			assertTrue(newArtwork.getMimeType().equals(newTagArtwork.getMimeType()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
