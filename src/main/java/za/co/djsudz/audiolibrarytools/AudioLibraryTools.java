/**
 * 
 */
package za.co.djsudz.audiolibrarytools;

import java.io.File;

import za.co.djsudz.audiolibrarytools.input.AudioLibraryReader;
import za.co.djsudz.audiolibrarytools.model.AudioLibrary;
import za.co.djsudz.audiolibrarytools.output.AudioLibraryWriter;
import za.co.djsudz.audiolibrarytools.update.AudioLibraryUpdater;

/**
 * @author Sudheer
 *
 */
public class AudioLibraryTools {
	
	File libraryBasePath;
	int requiredImageSize = 500;
	
	public void checkArgs(String[] args) {
        if (args.length == 0)
        {
            System.err.println("usage AudioLibraryTools <AudioLibPath> <RequiredImageSize>");
            System.err.println("      You must enter the audio library directory and required image size");
            System.exit(1);
        }
        else if (args.length > 2)
        {
            System.err.println("usage AudioLibraryTools <AudioLibPath> <RequiredImageSize>");
            System.err.println("      Only two parameters accepted");
            System.exit(1);
        }
        libraryBasePath = new File(args[0]);
        if (args.length == 2) {
	        try {
	        	requiredImageSize = Integer.parseInt(args[1]);
	        }
	        catch(NumberFormatException ex) {
	        	System.err.println("usage AudioLibraryTools <AudioLibPath> <RequiredImageSize>");
	        	System.err.println("RequiredImageSize paramater must be an Integer value");
	        	System.exit(1);
	        }
        }
        
        if (!libraryBasePath.isDirectory())
        {
            System.err.println("usage AudioLibraryTools <AudioLibPath> <RequiredImageSize>");
            System.err.println("      AudioLibPath " + args[0] + " could not be found");
            System.exit(1);
        }
	}
	
	public void procesLibrary() {
		long startTime = System.currentTimeMillis();
		//Read Audio Library
		AudioLibrary audioLibrary = AudioLibraryReader.readAudioLibrary(this.libraryBasePath);
		
		//Update Library
		AudioLibraryUpdater audioLibraryUpdater = new AudioLibraryUpdater(audioLibrary);
		audioLibraryUpdater.setRequiredImageSize(this.requiredImageSize);
		audioLibraryUpdater.updateLibrary();
		
		//Write Library
		AudioLibraryWriter audioLibraryWriter = new AudioLibraryWriter(audioLibrary);
		audioLibraryWriter.writeLibrary(libraryBasePath.getAbsolutePath());
		
		long endTime = System.currentTimeMillis();
		
		long totalExecTime = (endTime - startTime) / 1000;
		System.out.println("Total Execution Time: " + totalExecTime + "s");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AudioLibraryTools audioLibraryTools = new AudioLibraryTools();
		audioLibraryTools.checkArgs(args);
		audioLibraryTools.procesLibrary();
	}

}
