/**
 * 
 */
package za.co.djsudz.audiolibraryreader.tag;

import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

/**
 * @author Sudheer
 *
 */
public class AudioTagUpdater {
	
	public static Tag updateArtist(Tag tag, String newArtist) {	
		try {
			tag.setField(FieldKey.ARTIST, newArtist);
		} catch (KeyNotFoundException | FieldDataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}

	public static Tag updateAlbumArtist(Tag tag, String newAlbumArtist) {
		try {
			tag.setField(FieldKey.ALBUM_ARTIST, newAlbumArtist);
		} catch (KeyNotFoundException | FieldDataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}
	
	public static Tag updateArtwork(Tag tag, Artwork artwork) {
		try {
			tag.setField(artwork);
		} catch (FieldDataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}
}
