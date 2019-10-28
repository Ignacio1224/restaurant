package utilidades;

/**
 *
 * @author Ignacio
 */
public class CustomException extends Exception {
    private String message;

    public CustomException(String message) {
        super(message);
    }
}
