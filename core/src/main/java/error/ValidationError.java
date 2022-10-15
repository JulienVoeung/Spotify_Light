package error;

public class ValidationError {

    private final String message;

    public ValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
