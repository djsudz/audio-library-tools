package za.co.djsudz.audiolibrarytools.update.tag;

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
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AudioTagUpdaterUtilsTest {
	
	//private static AudioTagUpdaterUtils audioTagReader = null;
	private static Tag tag;
	
	@BeforeAll
	private static void init() {
		File sampleFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		
		//Check Sample Files Exist
		assertTrue(sampleFile.exists());
				
		try {
			AudioFile sampleAudioFile = AudioFileIO.read(sampleFile);
			tag = sampleAudioFile.getTag();
			//audioTagReader = new AudioTagUpdaterUtils(sampleAudioFile);
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
		assertEquals(10, AudioTagUpdaterUtils.getTagCount(tag));			
	}
	
	@Test
	void testGetArtist() {
		assertTrue("Sample Artist".equals(AudioTagUpdaterUtils.getArtist(tag)));
	}
	
	@Test
	void testGetTitle() {
		assertTrue("Sample Title".equals(AudioTagUpdaterUtils.getTitle(tag)));
	}
	
	@Test
	void testGetAlbum() {
		assertTrue("Sample Album".equals(AudioTagUpdaterUtils.getAlbum(tag)));
	}
	
	@Test
	void testGetAlbumArtist() {
		assertTrue("Sample Album Artist".equals(AudioTagUpdaterUtils.getAlbumArtist(tag)));
	}
	
	@Test
	void testGetComposer() {
		assertTrue("Sample Composer".equals(AudioTagUpdaterUtils.getComposer(tag)));
	}
	
	@Test
	void testGetGenre() {
		assertTrue("Sample Genre".equals(AudioTagUpdaterUtils.getGenre(tag)));
	}
	
	@Test
	void testGetYear() {
		assertTrue("2020".equals(AudioTagUpdaterUtils.getYear(tag)));
	}
	
	@Test
	void testGetTrackNumber() {
		assertTrue("01".equals(AudioTagUpdaterUtils.getTrackNumber(tag)));
	}
	
	@Test
	void testGetComment() {
		assertTrue("Sample Comment".equals(AudioTagUpdaterUtils.getComment(tag)));

	}
	
	@Test
	void testGetArtwork() {
		File sampleArtworkFile = new File("src/test/resources/sampleAudioDirectory/sample_album_art_700x700.jpg");

		assertTrue(sampleArtworkFile.exists());

		//Check Tag Artwork
		Artwork sampleArtwork;
		try {
			sampleArtwork = ArtworkFactory.createArtworkFromFile(sampleArtworkFile);
			Artwork tagArtwork = AudioTagUpdaterUtils.getArtwork(tag);
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
