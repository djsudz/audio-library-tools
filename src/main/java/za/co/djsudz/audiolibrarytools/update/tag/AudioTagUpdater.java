/**
 * 
 */
package za.co.djsudz.audiolibrarytools.update.tag;

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
	
	private Tag fTag;
	
	public AudioTagUpdater(Tag tag) {
		this.fTag = tag;
	}
	
	public void updateArtist(String newArtist) {	
		try {
			getTag().setField(FieldKey.ARTIST, newArtist);
		} catch (KeyNotFoundException | FieldDataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAlbumArtist(String newAlbumArtist) {
		try {
			getTag().setField(FieldKey.ALBUM_ARTIST, newAlbumArtist);
		} catch (KeyNotFoundException | FieldDataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateArtwork(Artwork artwork) {
		try {
			getTag().deleteArtworkField();
			getTag().setField(artwork);
		} catch (FieldDataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the tag
	 */
	public Tag getTag() {
		return fTag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Tag tag) {
		this.fTag = tag;
	}
}
