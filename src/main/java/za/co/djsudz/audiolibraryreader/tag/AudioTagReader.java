/**
 * 
 */
package za.co.djsudz.audiolibraryreader.tag;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;

/**
 * @author Sudheer
 *
 */
public class AudioTagReader {

	public static Tag readAudioTag(File srcAudioFile) throws CannotReadException, IOException, 
							TagException, ReadOnlyFileException, InvalidAudioFrameException {
		if (srcAudioFile == null)
			return null;
		else {
			AudioFile audioFile = AudioFileIO.read(srcAudioFile);
			return audioFile.getTag();
		}
	}
	
	public static String getArtist(Tag tag) {		
		return tag.getFirst(FieldKey.ARTIST);
	}
	
	public static String getTitle(Tag tag) {
		return tag.getFirst(FieldKey.TITLE);
	}
	
	public static String getAlbum(Tag tag) {
		return tag.getFirst(FieldKey.ALBUM);
	}
	
	public static String getAlbumArtist(Tag tag) {
		return tag.getFirst(FieldKey.ALBUM_ARTIST);
	}
	
	public static String getGenre(Tag tag) {
		return tag.getFirst(FieldKey.GENRE);
	}
	
	public static String getComposer(Tag tag) {
		return tag.getFirst(FieldKey.COMPOSER);
	}
	
	public static String getComment(Tag tag) {
		return tag.getFirst(FieldKey.COMMENT);
	}
	
	public static String getYear(Tag tag) {
		return tag.getFirst(FieldKey.YEAR);
	}
	
	public static String getTrackNumber(Tag tag) {
		return tag.getFirst(FieldKey.TRACK);
	}
	
	public static Artwork getArtwork(Tag tag) {
		return tag.getFirstArtwork();
	}
}
