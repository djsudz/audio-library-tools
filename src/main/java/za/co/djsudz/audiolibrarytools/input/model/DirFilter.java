/**
 * 
 */
package za.co.djsudz.audiolibrarytools.input.model;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Sudheer
 *
 */
public final class DirFilter implements FileFilter
{
    public DirFilter()
    {

    }
    
    public static final String IDENT = "$Id$";
    
    /**
     * Determines whether or not the file is an mp3 file.  If the file is
     * a directory, whether or not is accepted depends upon the
     * allowDirectories flag passed to the constructor.
     *
     * @param file the file to test
     * @return true if this file or directory should be accepted
     */
    
    public final boolean accept(final File file)
    {
        return file.isDirectory();
    }
}