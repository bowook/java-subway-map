package subway.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class MainSelectionDTOTest {

    @Test
    @DisplayName("정상")
    void 정상() {
        //given
        String validSelection = "1";

        //when
        MainSelectionDTO mainSelectionDTO = new MainSelectionDTO(validSelection);

        //then
        assertThat(mainSelectionDTO.getSelection()).isEqualTo(validSelection);
    }

    @Test
    @DisplayName("검증 테스트")
    void 검증_테스트() {
        //given
        String invalidSelection = "5";

        //when
        try {
            MainSelectionDTO mainSelectionDTO = new MainSelectionDTO(invalidSelection);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 선택할 수 없는 기능입니다.");
        }

        //then
        assertThatThrownBy(() -> new MainSelectionDTO(invalidSelection))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.UNABLE_TO_CHOOSE_FUNCTION.getErrorMessage());
    }
}
