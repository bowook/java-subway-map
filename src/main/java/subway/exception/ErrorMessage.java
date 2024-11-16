package subway.exception;

public enum ErrorMessage {
    UNABLE_TO_CHOOSE_FUNCTION("선택할 수 없는 기능입니다."),
    ALREADY_REGISTER_STATION_NAME("이미 등록된 역 이름입니다."),
    SUBWAY_STATION_NAME_LENGTH("역 이름은 2글자 이상이어야 합니다."),
    SUBWAY_STATION_NAME_NUMBER("역 이름은 숫자가 들어갈 수 없습니다."),
    SUBWAY_STATION_EMPTY_NAME("역 이름은 비어있을 수 없습니다."),
    SUBWAY_STATION_NAME_CONTAINS_EMPTY_NAME("역 이름은 비어있는 문자열이 들어갈 수 없습니다."),
    SUBWAY_STATION_NOT_PRESENCE("역 이름이 존재하지 않습니다.");

    private final String errorMessage;

    ErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return "[ERROR] " + errorMessage;
    }
}
