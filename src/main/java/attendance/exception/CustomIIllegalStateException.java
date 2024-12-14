package attendance.exception;

public class CustomIIllegalStateException extends IllegalStateException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    CustomIIllegalStateException(String message) {
        super(ERROR_PREFIX + message);
    }
}
