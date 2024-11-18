package subway.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Line;

public class LineRepositoryTest {
    @Test
    @DisplayName("라인 추가 테스트")
    void 라인_추가_테스트() {
        //given
        String lineName = "2호선";
        Line line = new Line(lineName);
        LineRepository lineRepository = new LineRepository();

        //when
        lineRepository.addLine(line);

        //then
        assertThat(lineRepository.findLineByName(lineName).getName()).isEqualTo(line.getName());
    }

    @Test
    @DisplayName("라인 삭제 테스트")
    void 라인_삭제_테스트() {
        //given
        String lineName = "2호선";
        Line line = new Line(lineName);
        String nextLineName = "4호선";
        Line nextLine = new Line(nextLineName);
        LineRepository lineRepository = new LineRepository();
        lineRepository.addLine(line);
        lineRepository.addLine(nextLine);
        //when
        lineRepository.deleteLineByName(nextLineName);

        //then
        assertThat(lineRepository.lines().stream().iterator().next()).isEqualTo(lineRepository.findLineByName("2호선"));
    }
}
