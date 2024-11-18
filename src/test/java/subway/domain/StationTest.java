package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class StationTest {
    @Test
    @DisplayName("정상 테스트")
    void 정상() {
        //given
        String validName = "노원역";

        //when
        Station station = new Station(validName);

        //then
        assertThat(station.getName()).isEqualTo(validName);
    }

    @Test
    @DisplayName("비어있는 이름 테스트")
    void 비어있는_이름_테스트() {
        //given
        String invalidName = "";

        //when
        try {
            Station station = new Station(invalidName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름은 비어있을 수 없습니다.");
        }

        //then
        assertThatThrownBy(() -> new Station(invalidName))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.SUBWAY_STATION_EMPTY_NAME.getErrorMessage());
    }

    @Test
    @DisplayName("길이 테스트")
    void 길이_예외_테스트() {
        //given
        String invalidName = "상";

        //when
        try {
            Station station = new Station(invalidName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름은 2글자 이상이어야 합니다.");
        }

        //then
        assertThatThrownBy(() -> new Station(invalidName))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.SUBWAY_STATION_NAME_LENGTH.getErrorMessage());
    }
}
