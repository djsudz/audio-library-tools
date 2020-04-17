package za.co.djsudz.audiolibraryreader.tag;

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
import org.junit.jupiter.api.Test;

class AudioTagReaderTest {

	@Test
	void testReadAudioTag() {
		File sampleAudioFile = new File("src/test/resources/sampleAudioDirectory/sampleAudioMp3.mp3");
		File sampleArtworkFile = new File("src/test/resources/sampleAudioDirectory/sample_album_art.jpg");
		
		//Check Sample Files Exist
		assertTrue(sampleAudioFile.exists());
		assertTrue(sampleArtworkFile.exists());
		
		AudioTagReader audioTagReader = new AudioTagReader();
		
		try {
			Tag audioTag = audioTagReader.readAudioTag(sampleAudioFile);
			//Check Tag Field Count
			assertEquals(10, audioTag.getFieldCount());
			
			//Check Tag Fields
			assertTrue("Sample Artist".equals(audioTag.getFirst(FieldKey.ARTIST)));
			assertTrue("Sample Title".equals(audioTag.getFirst(FieldKey.TITLE)));
			assertTrue("Sample Album".equals(audioTag.getFirst(FieldKey.ALBUM)));
			assertTrue("Sample Album Artist".equals(audioTag.getFirst(FieldKey.ALBUM_ARTIST)));
			assertTrue("Sample Genre".equals(audioTag.getFirst(FieldKey.GENRE)));
			assertTrue("Sample Composer".equals(audioTag.getFirst(FieldKey.COMPOSER)));
			assertTrue("Sample Comment".equals(audioTag.getFirst(FieldKey.COMMENT)));
			assertTrue("2020".equals(audioTag.getFirst(FieldKey.YEAR)));
			assertTrue("01".equals(audioTag.getFirst(FieldKey.TRACK)));
			
			//Check Tag Artwork
			Artwork sampleArtwork = ArtworkFactory.createArtworkFromFile(sampleArtworkFile);
			Artwork tagArtwork = audioTag.getFirstArtwork();
			assertEquals(sampleArtwork.getBinaryData().length, tagArtwork.getBinaryData().length);
			assertEquals(sampleArtwork.getHeight(), tagArtwork.getHeight());
			assertEquals(sampleArtwork.getWidth(), tagArtwork.getWidth());
			assertTrue(sampleArtwork.getMimeType().equals(tagArtwork.getMimeType()));
			
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
