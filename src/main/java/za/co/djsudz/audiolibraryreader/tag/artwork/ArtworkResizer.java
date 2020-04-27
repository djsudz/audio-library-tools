/**
 * 
 */
package za.co.djsudz.audiolibraryreader.tag.artwork;

import java.io.IOException;

import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.StandardImageHandler;

/**
 * @author Sudheer
 *
 */
public class ArtworkResizer {
	
	public static void resizeArtwork(Artwork artwork, int size) throws IOException {
		StandardImageHandler.getInstanceOf().makeSmaller(artwork, size);
	}

}
