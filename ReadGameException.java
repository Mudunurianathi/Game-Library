/**
 * Challenge Game Library
 * Custom exception for invalid game data.
 *
 * @author Anathi Mudunuri, CS 18000 
 *
 * @version October 23, 2026
 *
 */
 
public class ReadGameException extends Exception {
    
    public ReadGameException() {
        super();
    }

    public ReadGameException(String message) {
        super(message);
    }
}
