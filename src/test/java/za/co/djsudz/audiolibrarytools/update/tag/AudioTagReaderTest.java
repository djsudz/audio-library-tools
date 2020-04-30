package za.co.djsudz.audiolibrarytools.update.tag;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import za.co.djsudz.audiolibrarytools.update.tag.AudioTagReader;

class AudioTagReaderTest {
	
	private static AudioTagReader audioTagReader = null;
	
	@BeforeAll
	private static void init() {
		File sampleAudioFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		
		//Check Sample Files Exist
		assertTrue(sampleAudioFile.exists());
				
		try {
			audioTagReader = new AudioTagReader(sampleAudioFile);
			//audioTag = AudioTagReader.readAudioTag(sampleAudioFile);
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Test
	void testReadAudioTag() {	
		//Check Tag Field Count
		assertEquals(10, audioTagReader.getTagCount());			
	}
	
	@Test
	void testGetArtist() {
		assertTrue("Sample Artist".equals(audioTagReader.getArtist()));
	}
	
	@Test
	void testGetTitle() {
		assertTrue("Sample Title".equals(audioTagReader.getTitle()));
	}
	
	@Test
	void testGetAlbum() {
		assertTrue("Sample Album".equals(audioTagReader.getAlbum()));
	}
	
	@Test
	void testGetAlbumArtist() {
		assertTrue("Sample Album Artist".equals(audioTagReader.getAlbumArtist()));
	}
	
	@Test
	void testGetComposer() {
		assertTrue("Sample Composer".equals(audioTagReader.getComposer()));
	}
	
	@Test
	void testGetGenre() {
		assertTrue("Sample Genre".equals(audioTagReader.getGenre()));
	}
	
	@Test
	void testGetYear() {
		assertTrue("2020".equals(audioTagReader.getYear()));
	}
	
	@Test
	void testGetTrackNumber() {
		assertTrue("01".equals(audioTagReader.getTrackNumber()));
	}
	
	@Test
	void testGetComment() {
		assertTrue("Sample Comment".equals(audioTagReader.getComment()));

	}
	
	@Test
	void testGetArtwork() {
		File sampleArtworkFile = new File("src/test/resources/sampleAudioDirectory/sample_album_art_700x700.jpg");

		assertTrue(sampleArtworkFile.exists());

		//Check Tag Artwork
		Artwork sampleArtwork;
		try {
			sampleArtwork = ArtworkFactory.createArtworkFromFile(sampleArtworkFile);
			Artwork tagArtwork = audioTagReader.getArtwork();
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
