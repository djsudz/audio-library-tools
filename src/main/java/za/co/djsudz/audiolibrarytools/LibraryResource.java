package za.co.djsudz.audiolibrarytools;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class LibraryResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	AudioLibraryTools audioLibraryTools = new AudioLibraryTools();
    	audioLibraryTools.libraryBasePath = new File("D:/Temp/Music");
    	audioLibraryTools.requiredImageSize = 700;
    	audioLibraryTools.procesLibrary();
        return "done";
    }
}