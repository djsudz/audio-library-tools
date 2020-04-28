/**
 * 
 */
package za.co.djsudz.audiolibraryreader.output;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jaudiotagger.audio.AudioFile;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryWriterUtils {
	
	public static String getTargetPath(AudioFile audioFile) {
		
		File file = audioFile.getFile();
		
		long fileLastModified = file.lastModified();
		Date fileLastModifiedDate = new Date(fileLastModified);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
		return dateFormat.format(fileLastModifiedDate);
	}

}
