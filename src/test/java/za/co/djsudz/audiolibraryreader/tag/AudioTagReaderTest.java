package za.co.djsudz.audiolibraryreader.tag;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AudioTagReaderTest {
	
	private static Tag audioTag = null;
	
	@BeforeAll
	private static void init() {
		File sampleAudioFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		
		//Check Sample Files Exist
		assertTrue(sampleAudioFile.exists());
				
		try {
			audioTag = AudioTagReader.readAudioTag(sampleAudioFile);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Test
	void testReadAudioTag() {	
		//Check Tag Field Count
		assertEquals(10, audioTag.getFieldCount());			
	}
	
	@Test
	void testGetArtist() {
		assertTrue("Sample Artist".equals(AudioTagReader.getArtist(audioTag)));
	}
	
	@Test
	void testGetTitle() {
		assertTrue("Sample Title".equals(AudioTagReader.getTitle(audioTag)));
	}
	
	@Test
	void testGetAlbum() {
		assertTrue("Sample Album".equals(AudioTagReader.getAlbum(audioTag)));
	}
	
	@Test
	void testGetAlbumArtist() {
		assertTrue("Sample Album Artist".equals(AudioTagReader.getAlbumArtist(audioTag)));
	}
	
	@Test
	void testGetComposer() {
		assertTrue("Sample Composer".equals(AudioTagReader.getComposer(audioTag)));
	}
	
	@Test
	void testGetGenre() {
		assertTrue("Sample Genre".equals(AudioTagReader.getGenre(audioTag)));
	}
	
	@Test
	void testGetYear() {
		assertTrue("2020".equals(AudioTagReader.getYear(audioTag)));
	}
	
	@Test
	void testGetTrackNumber() {
		assertTrue("01".equals(AudioTagReader.getTrackNumber(audioTag)));
	}
	
	@Test
	void testGetComment() {
		assertTrue("Sample Comment".equals(AudioTagReader.getComment(audioTag)));

	}
	
	@Test
	void testGetArtwork() {
		File sampleArtworkFile = new File("src/test/resources/sampleAudioDirectory/sample_album_art.jpg");

		assertTrue(sampleArtworkFile.exists());

		//Check Tag Artwork
		Artwork sampleArtwork;
		try {
			sampleArtwork = ArtworkFactory.createArtworkFromFile(sampleArtworkFile);
			Artwork tagArtwork = AudioTagReader.getArtwork(audioTag);
			assertEquals(sampleArtwork.getBinaryData().length, tagArtwork.getBinaryData().length);
			assertEquals(sampleArtwork.getHeight(), tagArtwork.getHeight());
			assertEquals(sampleArtwork.getWidth(), tagArtwork.getWidth());
			assertTrue(sampleArtwork.getMimeType().equals(tagArtwork.getMimeType()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
