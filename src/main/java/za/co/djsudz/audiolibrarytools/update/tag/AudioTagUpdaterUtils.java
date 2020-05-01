/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update.tag;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

/**
 * @author Sudheer
 *
 */
public class AudioTagUpdaterUtils {
	
	public static int getTagCount(Tag tag) {
		return tag.getFieldCount();
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
