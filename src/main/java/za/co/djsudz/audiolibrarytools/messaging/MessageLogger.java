/**
 * 
 */
package za.co.djsudz.audiolibrarytools.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Sudheer
 *
 */
@ApplicationScoped
public class MessageLogger {
	
	MessageEmitter messageEmitter;
	
	@Inject 
	public MessageLogger(MessageEmitter messageEmitter) {
		this.messageEmitter = messageEmitter;
	}

	public void logMessage(String message) {
		System.out.println("[ALT] " + message);
		messageEmitter.send(message);
	}
}
