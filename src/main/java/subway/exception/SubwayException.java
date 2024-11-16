package subway.exception;

public class SubwayException extends IllegalArgumentException {
    private SubwayException(final ErrorMessage errorMessage) {
        super(errorMessage.getErrorMessage());
    }

    public static SubwayException from(final ErrorMessage errorMessage) {
        return new SubwayException(errorMessage);
    }
}
