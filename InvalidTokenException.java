package globalexceptions;
/**
 * exception thrown when there is an invalid token being read
 */
public class InvalidTokenException extends RuntimeException {
    /**
     * exception which is thrown if an invalid argument is passed to a method
     *
     * @param message error message
     */
    public InvalidTokenException(String message) {
        super(message);
    }
}
