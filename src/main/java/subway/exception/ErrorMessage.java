package subway.exception;

public enum ErrorMessage {
    UNABLE_TO_CHOOSE_FUNCTION("선택할 수 없는 기능입니다."),
    ALREADY_REGISTER_STATION_NAME("이미 등록된 역 이름입니다.");

    private final String errorMessage;

    ErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return "[ERROR] " + errorMessage;
    }
}
