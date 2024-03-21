package common;

public class ComonException extends RuntimeException {

    public ComonException() {

    }

    public ComonException(String message) {
        super(message);
    }

    public ComonException(Throwable t) {
        super(t);
    }

    public ComonException(String message, Throwable t) {
        super(message, t);
    }
}
