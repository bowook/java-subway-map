package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RouteTest {

    @Test
    @DisplayName("정상 테스트")
    void 정상() {
        //given
        String lineName = "4호선";
        String stationName = "노원역";
        Line line = new Line(lineName);
        Station station = new Station(stationName);

        //when
        Route route = new Route(line, station);

        //then
        assertThat(route.getRoute().keySet()).contains(line);
        assertThat(route.getRoute().values().stream().iterator().next()).containsExactly(station);
    }

    @Test
    @DisplayName("역을 순서를 기준으로 추가")
    void 역_순서_기준_추가_테스트() {
        //given
        String lineName = "4호선";
        String stationName = "노원역";
        Line line = new Line(lineName);
        Station station = new Station(stationName);
        Route route = new Route(line, station);

        String testStationName = "상계역";
        Station testStation = new Station(testStationName);
        int order = 1;

        //when
        route.addByOrder(testStation, order);

        //then
        assertThat(route.getRoute().values().stream().iterator().next()).containsExactly(testStation, station);
    }

    @Test
    @DisplayName("역 삭제 테스트")
    void 역_삭제_테스트() {
        //given
        String lineName = "4호선";
        String stationName = "노원역";
        Line line = new Line(lineName);
        Station station = new Station(stationName);
        Route route = new Route(line, station);

        String testStationName = "상계역";
        Station testStation = new Station(testStationName);
        int order = 1;
        route.addByOrder(testStation, order);

        //when
        route.deleteStation(station);

        //then
        assertThat(route.getRoute().values().stream().iterator().next()).containsExactly(testStation);
    }
}
