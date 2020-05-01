package za.co.djsudz.audiolibrarytools.update;

import static org.junit.jupiter.api.Assertions.*;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.junit.jupiter.api.Test;

import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

class AudioLibraryUpdaterTest {

	@Test
	void testUpdateLibrary() {
		String audioLibraryPath = "src/test/resources/sampleAudioDirectory";
		
		AudioLibrary audioLibrary = new AudioLibrary(audioLibraryPath, true);
		assertNotNull(audioLibrary);
		assertTrue(audioLibrary.getAudioFiles().size() == 1);
		
		//Check Artist and Album Artist before updating
		AudioFile sampleAudioFile = audioLibrary.getAudioFiles().get(0);
		Tag tag = sampleAudioFile.getTag();
		assertTrue("Sample Artist".equals(tag.getFirst(FieldKey.ARTIST)));
		assertTrue("Sample Album Artist".equals(tag.getFirst(FieldKey.ALBUM_ARTIST)));
		
		//Check Artwork size before update
		Artwork artwork = tag.getFirstArtwork();
		artwork.setImageFromData();
		assertEquals(700, artwork.getWidth());
		assertEquals(700, artwork.getHeight());
		
		//Update Library
		AudioLibraryUpdater audioLibraryUpdater = new AudioLibraryUpdater(audioLibrary);
		audioLibraryUpdater.updateLibrary();
		
		//Check Updated Library
		assertNotNull(audioLibrary);
		assertTrue(audioLibrary.getAudioFiles().size() == 1);
		
		//Check Artist and Album Artist after updating
		AudioFile updatedSampleAudioFile = audioLibrary.getAudioFiles().get(0);
		Tag updatedTag = updatedSampleAudioFile.getTag();
		assertTrue("Sample Artist".equals(updatedTag.getFirst(FieldKey.ARTIST)));
		assertTrue("Sample Artist".equals(updatedTag.getFirst(FieldKey.ALBUM_ARTIST)));
		
		//Check Artwork size after update
		Artwork updatedArtwork = updatedTag.getFirstArtwork();
		updatedArtwork.setImageFromData();
		assertEquals(500, updatedArtwork.getWidth());
		assertEquals(500, updatedArtwork.getHeight());
	}

}
