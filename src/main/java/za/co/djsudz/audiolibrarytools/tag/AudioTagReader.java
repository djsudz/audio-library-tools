/**
 * 
 */
package za.co.djsudz.audiolibrarytools.tag;

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
	
	private File srcFile;
	private Tag tag;
	
	public AudioTagReader() {
		//Default Constructor
	}
	
	public AudioTagReader(File srcFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		this.srcFile = srcFile;
		if (srcFile == null)
			this.tag = null;
		else {
			AudioFile audioFile = AudioFileIO.read(srcFile);
			this.tag = audioFile.getTag();
		}
	}
	
	public int getTagCount() {
		return getTag().getFieldCount();
	}
	
	public String getArtist() {		
		return getTag().getFirst(FieldKey.ARTIST);
	}
	
	public String getTitle() {
		return getTag().getFirst(FieldKey.TITLE);
	}
	
	public String getAlbum() {
		return getTag().getFirst(FieldKey.ALBUM);
	}
	
	public String getAlbumArtist() {
		return getTag().getFirst(FieldKey.ALBUM_ARTIST);
	}
	
	public String getGenre() {
		return getTag().getFirst(FieldKey.GENRE);
	}
	
	public String getComposer() {
		return getTag().getFirst(FieldKey.COMPOSER);
	}
	
	public String getComment() {
		return getTag().getFirst(FieldKey.COMMENT);
	}
	
	public String getYear() {
		return getTag().getFirst(FieldKey.YEAR);
	}
	
	public String getTrackNumber() {
		return getTag().getFirst(FieldKey.TRACK);
	}
	
	public Artwork getArtwork() {
		return getTag().getFirstArtwork();
	}

	/**
	 * @return the srcFile
	 */
	public File getSrcFile() {
		return srcFile;
	}

	/**
	 * @param srcFile the srcFile to set
	 */
	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}

	/**
	 * @return the tag
	 */
	public Tag getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
