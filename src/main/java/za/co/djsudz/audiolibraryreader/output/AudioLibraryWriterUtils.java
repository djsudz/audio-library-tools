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
	
	public static String getTargetPath(AudioFile audioFile, String basePath) {
		
		File file = audioFile.getFile();
		String filename = file.getName();
		String filenameNoExt = filename.substring(0, filename.lastIndexOf("."));
		String lastModified = getLastModified(file);
		return basePath + "/" + AudioLibraryWriterConstants.NEW_MUSIC + "/" + lastModified + "/" + filenameNoExt;
	}

	
	public static String getLastModified(File file) {
		long fileLastModified = file.lastModified();
		Date fileLastModifiedDate = new Date(fileLastModified);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
		return dateFormat.format(fileLastModifiedDate);
	}
	
	public static boolean createTargetPathDirectories(String targetPath) {
		String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
		File pathDir = new File(path);
		return pathDir.mkdirs();
	}
}
