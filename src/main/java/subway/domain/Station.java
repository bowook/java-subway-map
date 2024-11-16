package subway.domain;

import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class Station {
    private String name;

    public Station(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        validateEmpty(name);
        validateLength(name);
    }

    private void validateLength(String name) {
        if (name.length() < 2) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NAME_LENGTH);
        }
    }

    private void validateEmpty(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_EMPTY_NAME);
        }
        if (name.contains(" ")) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NAME_CONTAINS_EMPTY_NAME);
        }
    }

}
