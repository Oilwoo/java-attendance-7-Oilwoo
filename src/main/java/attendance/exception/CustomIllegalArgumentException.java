package attendance.exception;

public class CustomIllegalArgumentException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    CustomIllegalArgumentException(String message) {
        super(ERROR_PREFIX + message);
    }
}
