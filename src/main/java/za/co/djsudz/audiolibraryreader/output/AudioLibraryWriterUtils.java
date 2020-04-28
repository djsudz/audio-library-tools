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
		String filename = file.getName();
		String filenameNoExt = filename.substring(0, filename.lastIndexOf("."));
		String lastModified = getLastModified(file);
		return "G:/Music/New Music " + lastModified + "/" + filenameNoExt;
	}

	
	public static String getLastModified(File file) {
		long fileLastModified = file.lastModified();
		Date fileLastModifiedDate = new Date(fileLastModified);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
		return dateFormat.format(fileLastModifiedDate);
	}
}
