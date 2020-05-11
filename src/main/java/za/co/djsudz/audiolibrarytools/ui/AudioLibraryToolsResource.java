package za.co.djsudz.audiolibrarytools.ui;

import java.io.File;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import za.co.djsudz.audiolibrarytools.AudioLibraryTools;

@Path("/alt")
public class AudioLibraryToolsResource {
	
	@Inject
	@Channel("alt-message-stream")
	Publisher<String> messages;
	
	@GET
	@Path("/stream")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@SseElementType("text/plain")
	public Publisher<String> stream() {
		return messages;
	}
	
	@POST
	@Path("/process")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String process(@FormParam("libraryBasePath") String libraryBasePath,
							@FormParam("libraryOutputPath") String libraryOutputPath,
							@FormParam("requiredImageSize") int requiredImageSize) {
		AudioLibraryTools audioLibraryTools = new AudioLibraryTools(libraryBasePath, libraryOutputPath, requiredImageSize);
		audioLibraryTools.procesLibrary();
		return "done";
	}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/process")
    public String hello() {
    	AudioLibraryTools audioLibraryTools = new AudioLibraryTools(new File("D:/Temp/Music"), 700);
    	//audioLibraryTools.setLibraryBasePath(new File("D:/Temp/Music"));
    	//audioLibraryTools.setRequiredImageSize(700);
    	audioLibraryTools.procesLibrary();
        return "done";
    }
}