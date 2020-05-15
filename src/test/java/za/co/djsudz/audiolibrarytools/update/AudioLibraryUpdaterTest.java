package za.co.djsudz.audiolibrarytools.update;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import za.co.djsudz.audiolibrarytools.messaging.MessageEmitter;
import za.co.djsudz.audiolibrarytools.messaging.MessageLogger;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;

class AudioLibraryUpdaterTest {
	
	static MessageLogger messageLogger;
	
	@BeforeAll
	static void init() {
		MessageEmitter messageEmitterMock = Mockito.mock(MessageEmitter.class);
		Mockito.doNothing().when(messageEmitterMock).send(ArgumentMatchers.anyString());
		
		messageLogger = new MessageLogger(messageEmitterMock);
	}

	@Test
	void testUpdateLibrary() {
		
		File audioLibraryPath = new File("src/test/resources/sampleAudioDirectory");
		
		AudioLibrary audioLibrary = new AudioLibrary(messageLogger);
		audioLibrary.setAudioFilesFromSourceDir(audioLibraryPath, true);
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
		assertEquals(1297, artwork.getWidth());
		assertEquals(1297, artwork.getHeight());
		
		//Update Library
		AudioLibraryUpdater audioLibraryUpdater = new AudioLibraryUpdater(messageLogger);
		audioLibraryUpdater.setRequiredImageSize(700);
		audioLibraryUpdater.updateLibrary(audioLibrary);
		
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
		assertEquals(700, updatedArtwork.getWidth());
		assertEquals(700, updatedArtwork.getHeight());
	}

}
