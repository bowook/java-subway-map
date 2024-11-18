package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class LineTest {

    @Test
    @DisplayName("생성 테스트")
    void 생성_테스트() {
        //given
        String validName = "2호선";

        //when
        Line line = new Line(validName);

        //then
        assertThat(line.getName()).isEqualTo(validName);
    }

    @Test
    @DisplayName("비어있는 라인 테스트")
    void 비어있는_예외_테스트() {
        //given
        String invalidName = "";

        //when
        try {
            Line line = new Line(invalidName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름은 비어있을 수 없습니다.");
        }

        //then
        assertThatThrownBy(() -> new Line(invalidName))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.SUBWAY_STATION_EMPTY_NAME.getErrorMessage());
    }

    @Test
    @DisplayName("글자 수 예외 테스트")
    void 글자수_예외_테스트() {
        //given
        String invalidName = "1";

        //when
        try {
            Line line = new Line(invalidName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름은 2글자 이상이어야 합니다.");
        }

        //then
        assertThatThrownBy(() -> new Line(invalidName))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.SUBWAY_STATION_NAME_LENGTH.getErrorMessage());
    }
}
