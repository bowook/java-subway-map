package subway.exception;

public enum ErrorMessage {
    UNABLE_TO_CHOOSE_FUNCTION("선택할 수 없는 기능입니다."),
    ALREADY_REGISTER_STATION_NAME("이미 등록된 역 이름입니다."),
    SUBWAY_STATION_NAME_LENGTH("역 이름은 2글자 이상이어야 합니다."),
    SUBWAY_STATION_NAME_NUMBER("역 이름은 숫자가 들어갈 수 없습니다."),
    SUBWAY_STATION_EMPTY_NAME("역 이름은 비어있을 수 없습니다."),
    SUBWAY_STATION_NAME_CONTAINS_EMPTY_NAME("역 이름은 비어있는 문자열이 들어갈 수 없습니다."),
    SUBWAY_STATION_NOT_PRESENCE("역 이름이 존재하지 않습니다."),
    ALREADY_REGISTER_LINE_NAME("이미 등록된 노선 이름입니다."),
    REGISTERED_STATION_NAME("노선에 등록된 역 이름은 삭제할 수 없습니다."),
    LINE_NAME_NOT_PRESENCE("삭제할 노선이 존재하지 않습니다."),
    ORDER_IS_NOT_NUMBER("순서는 숫자를 입력하셔야 합니다."),
    ORDER_IS_NOT_MINUS("순서는 1보다 크거나 같은 수를 입력하셔야 합니다."),
    DELETE_LINE_BY_ROUTE("노선에 포함된 역이 두개 이하일 때는 역을 제거할 수 없습니다.");

    private final String errorMessage;

    ErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return "[ERROR] " + errorMessage;
    }
}
