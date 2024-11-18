package subway.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class StationSelectionDTOTest {

    @Test
    @DisplayName("정상")
    void 정상() {
        //given
        String validSelection = "1";

        //when
        StationSelectionDTO stationSelectionDTO = new StationSelectionDTO(validSelection);

        //then
        assertThat(stationSelectionDTO.getSelection()).isEqualTo(validSelection);
    }

    @Test
    @DisplayName("검증")
    void 검증() {
        //given
        String invalidSelection = "4";

        //when
        try {
            StationSelectionDTO stationSelectionDTO = new StationSelectionDTO(invalidSelection);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 선택할 수 없는 기능입니다.");
        }

        //then
        assertThatThrownBy(() -> new StationSelectionDTO(invalidSelection))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.UNABLE_TO_CHOOSE_FUNCTION.getErrorMessage());
    }
}
