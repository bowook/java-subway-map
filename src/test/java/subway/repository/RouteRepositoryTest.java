package subway.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Line;
import subway.domain.Route;
import subway.domain.Station;

public class RouteRepositoryTest {

    @Test
    @DisplayName("구간 추가")
    void 구간_추가_테스트() {
        //given
        String lineName = "2호선";
        String stationName = "잠실역";
        Line line = new Line(lineName);
        Station station = new Station(stationName);
        Route route = new Route(line, station);
        RouteRepository routeRepository = new RouteRepository();

        //when
        routeRepository.addRoute(route);

        //then
        assertThat(routeRepository.findRouteByLine(line).getRoute().keySet().iterator().next().getName()).contains(
                line.getName());
    }

    @Test
    @DisplayName("구간 삭제")
    void 구간_삭제_테스트() {
        //given
        String lineName = "2호선";
        String stationName = "잠실역";
        Line line = new Line(lineName);
        Station station = new Station(stationName);
        Route route = new Route(line, station);
        String nextLineName = "4호선";
        String nextStationName = "노실역";
        Line nextline = new Line(nextLineName);
        Station nextstation = new Station(nextStationName);
        Route nextRoute = new Route(nextline, nextstation);
        RouteRepository routeRepository = new RouteRepository();

        routeRepository.addRoute(route);
        routeRepository.addRoute(nextRoute);
        //when
        routeRepository.deleteRouteByLine(nextline);

        //then
        assertThat(routeRepository.routes().stream().iterator().next().getRoute().keySet().stream().iterator().next()
                .getName()).isEqualTo(lineName);
    }

    @Test
    @DisplayName("널 반환")
    void 널_반환() {
        //given
        String lineName = "2호선";
        String stationName = "잠실역";
        Line line = new Line(lineName);
        Station station = new Station(stationName);
        Route route = new Route(line, station);

        RouteRepository routeRepository = new RouteRepository();
        routeRepository.deleteRouteByLine(line);

        //when
        Route testRoute = routeRepository.findRouteByLine(line);

        //then
        assertThat(testRoute).isEqualTo(null);
    }
}
