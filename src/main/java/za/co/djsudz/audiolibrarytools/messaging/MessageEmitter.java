/**
 * 
 */
package za.co.djsudz.audiolibrarytools.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

/**
 * @author Sudheer
 *
 */
@ApplicationScoped
public class MessageEmitter {
	@Inject
	@Channel("alt-message-stream")
	Emitter<String> emitter;
	
	public void send(String message) {
		emitter.send(message);
	}
}
