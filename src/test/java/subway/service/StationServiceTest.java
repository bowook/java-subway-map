package subway.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Line;
import subway.domain.Station;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;
import subway.repository.LineRepository;
import subway.repository.RouteRepository;
import subway.repository.StationRepository;

public class StationServiceTest {

    private StationService stationService;
    private StationRepository stationRepository;
    private LineRepository lineRepository;
    private RouteRepository routeRepository;

    @BeforeEach
    void setUp() {
        this.stationRepository = new StationRepository();
        this.lineRepository = new LineRepository();
        this.routeRepository = new RouteRepository();
        this.stationService = new StationService(stationRepository, lineRepository, routeRepository);
    }

    @Test
    @DisplayName("역 등록 검증")
    void 역_등록_검증() {
        //given
        String stationName = "노원역";

        //when
        stationService.registerStation(stationName);

        //then
        assertThat(stationRepository.getStation(stationName).getName()).isEqualTo(stationName);
    }

    @Test
    @DisplayName("등록된 역은 등록 안됨 검증")
    void 등록되어_있는_역_예외() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);

        //when
        try {
            stationService.registerStation(stationName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 이미 등록된 역 이름입니다.");
        }

        //then
        assertThatThrownBy(() -> stationService.registerStation(stationName))
                .isInstanceOf(SubwayException.class)
                .hasMessage(ErrorMessage.ALREADY_REGISTER_STATION_NAME.getErrorMessage());
    }

    @Test
    @DisplayName("구간 삭제 노선에 포함 되어있으면 실패")
    void 구간_삭제_노선_포함_예외() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);
        String nextStationName = "상계역";
        stationService.registerStation(nextStationName);
        String thirdStationName = "창동역";
        stationService.registerStation(thirdStationName);

        String lineName = "4호선";
        Line line = stationService.registerLine(lineName);
        stationService.registerStartLine(line, stationName);
        stationService.registerEndLine(line, thirdStationName);
        Station station = stationService.registerStationByRoute(nextStationName);

        stationService.registerRoute(line, station, 2);

        //when & then
        try {
            stationService.deleteStation(nextStationName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 노선에 등록된 역 이름은 삭제할 수 없습니다.");
        }
    }

    @Test
    @DisplayName("없는 역 삭제시 예외")
    void 없는_역_삭제_예외() {
        //given
        String stationName = "노원역";

        //when & then
        try {
            stationService.deleteStation(stationName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("역 삭제 정상")
    void 역_삭제_정상() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);

        //when
        stationService.deleteStation(stationName);

        //then
        assertThat(stationService.checkStation().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("이미 등록된 노선이면 예외")
    void 이미_등록된_노선_예외() {
        //given
        String lineName = "4호선";
        stationService.registerLine(lineName);

        //when
        try {
            stationService.registerLine(lineName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 이미 등록된 노선 이름입니다.");
        }
    }

    @Test
    @DisplayName("상행 종점역 등록하는데 역이 없으면 예외")
    void 상행_종점역_역_없음_예외() {
        //given
        Line line = stationService.registerLine("4호선");

        //when & then
        try {
            stationService.registerStartLine(line, "노원역");
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("하행 종점역 등록하는데 역이 없으면 예외")
    void 하행_종점역_역_없음_예외() {
        //given
        Line line = stationService.registerLine("4호선");

        //when & then
        try {
            stationService.registerEndLine(line, "상계역");
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("노선 삭제 라인이 없으면 예외")
    void 노선_삭제_노선_없으면_예외() {
        //given
        String lineName = "1호선";

        //when & then
        try {
            stationService.deleteLine(lineName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 삭제할 노선이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("노선 삭제 정상")
    void 노선_삭제_정상() {
        //given
        String lineName = "4호선";
        String stationName = "노원역";
        String nextStationName = "상계역";
        stationService.registerStation(stationName);
        stationService.registerStation(nextStationName);
        Line line = stationService.registerLine(lineName);
        stationService.registerStartLine(line, stationName);
        stationService.registerEndLine(line, nextStationName);

        //when
        stationService.deleteLine(lineName);

        //then
        assertThat(stationService.lines().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("구간 등록하는데 라인이 없으면 예외")
    void 구간_등록_라인_없을시_예외() {
        //given
        String lineName = "4호선";

        //when & then
        try {
            stationService.registerLineByRoute(lineName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 삭제할 노선이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("구간 등록하는데 라인이 있으면 라인 반환")
    void 구간_등록_라인_반환() {
        //given
        String lineName = "4호선";
        String stationName = "노원역";
        String nextStationName = "상계역";
        stationService.registerStation(stationName);
        stationService.registerStation(nextStationName);
        Line line = stationService.registerLine(lineName);
        stationService.registerStartLine(line, stationName);
        stationService.registerEndLine(line, nextStationName);

        //when
        Line testLine = stationService.registerLineByRoute(lineName);

        //then
        assertThat(line).isEqualTo(testLine);
    }

    @Test
    @DisplayName("구간 등록하는데 역이 없으면 예외")
    void 구간_등록_역_없을시_예외() {
        //given
        String stationName = "노원역";

        //when & then
        try {
            stationService.registerStationByRoute(stationName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("순서 반환")
    void 순서_반환_정상() {
        //given
        String order = "2";

        //when
        int parsedNumber = stationService.registerOrderByRoute(order);

        //then
        assertThat(Integer.parseInt(order)).isEqualTo(parsedNumber);
    }

    @Test
    @DisplayName("순서 반환하는데 숫자가 아니면 예외")
    void 순서_숫자가_아닐시_예외() {
        //given
        String order = "O";

        //when & then
        try {
            stationService.registerOrderByRoute(order);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 순서는 숫자를 입력하셔야 합니다.");
        }
    }

    @Test
    @DisplayName("순서 반환하는데 숫자인데 0보다 작거나 같은경우 예외")
    void 숫자가_0보다_작거나_같으면_예외() {
        //given
        String order = "0";

        //when & then
        try {
            stationService.registerOrderByRoute(order);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 순서는 1보다 크거나 같은 수를 입력하셔야 합니다.");
        }
    }

    @Test
    @DisplayName("구간 삭제할 수 있으면 노선 반환")
    void 구간_삭제_가능하면_노선_반환() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);
        String nextStationName = "상계역";
        stationService.registerStation(nextStationName);
        String thirdStationName = "창동역";
        stationService.registerStation(thirdStationName);

        String lineName = "4호선";
        Line line = stationService.registerLine(lineName);
        stationService.registerStartLine(line, stationName);
        stationService.registerEndLine(line, nextStationName);
        Station station = stationService.registerStationByRoute(thirdStationName);

        stationService.registerRoute(line, station, 2);

        //when
        Line testLine = stationService.deleteLineByRoute(lineName);

        //then
        assertThat(line).isEqualTo(testLine);

    }

    @Test
    @DisplayName("구간삭제하기 위해 역이 있는지 확인")
    void 정상() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);

        //when
        Station testStation = stationService.deleteStationByRoute(stationName);

        //then
        assertThat(testStation.getName()).isEqualTo(stationName);
    }

    @Test
    @DisplayName("구간 삭제 정상")
    void 구간_삭제_정상() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);
        String nextStationName = "상계역";
        stationService.registerStation(nextStationName);
        String thirdStationName = "창동역";
        stationService.registerStation(thirdStationName);

        String lineName = "4호선";
        Line line = stationService.registerLine(lineName);
        stationService.registerStartLine(line, stationName);
        stationService.registerEndLine(line, nextStationName);
        Station station = stationService.registerStationByRoute(thirdStationName);

        stationService.registerRoute(line, station, 2);

        //when
        stationService.deleteRoute(line, station);

        //then
        assertThat(stationService.routes().stream().iterator().next().getRoute().values().stream().iterator().next()
                .stream().iterator().next().getName()).isEqualTo(stationName, nextStationName);
    }

    @Test
    @DisplayName("구간 삭제하는데 역이 없으면 예외")
    void 예외() {
        //given
        String stationName = "노원역";

        //when
        try {
            stationService.deleteStationByRoute(stationName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 역 이름이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("노선에 포함된 역이 2개 이하 삭제 실패")
    void 노선_포함_역_2개_이하_예외() {
        //given
        String stationName = "노원역";
        stationService.registerStation(stationName);
        String nextStationName = "상계역";
        stationService.registerStation(nextStationName);

        String lineName = "4호선";
        Line line = stationService.registerLine(lineName);
        stationService.registerStartLine(line, stationName);
        stationService.registerEndLine(line, nextStationName);

        //when & then
        try {
            stationService.deleteLineByRoute(lineName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 노선에 포함된 역이 두개 이하일 때는 역을 제거할 수 없습니다.");
        }
    }

    @Test
    @DisplayName("노선을 삭제하려고하는데 노선이 없음")
    void 노선_없는데_삭제시_예외() {
        //given
        String lineName = "없음";

        //when & then
        try {
            stationService.deleteLineByRoute(lineName);
        } catch (SubwayException subwayException) {
            assertThat(subwayException.getMessage()).isEqualTo("[ERROR] 삭제할 노선이 존재하지 않습니다.");
        }
    }

}
