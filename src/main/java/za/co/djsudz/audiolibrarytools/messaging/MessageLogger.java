/**
 * 
 */
package za.co.djsudz.audiolibrarytools.messaging;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Sudheer
 *
 */
@ApplicationScoped
public class MessageLogger {

	public void logMessage(String message) {
		System.out.println("[ALT] " + message);
	}
}
