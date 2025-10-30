package globalexceptions;
/**
 * exception thrown when there is an invalid argument passed to a method
 */
public class InvalidArgumentException extends RuntimeException {
    /**
     * exception which is thrown if an invalid argument is passed to a method
     *
     * @param message error message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
