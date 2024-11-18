package subway.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Station;

public class StationRepositoryTest {

    @Test
    @DisplayName("역 추가")
    void 역_추가() {
        //given
        String stationName = "노원역";
        Station station = new Station(stationName);
        StationRepository stationRepository = new StationRepository();
        //when
        stationRepository.addStation(station);

        //then
        assertThat(stationRepository.getStation(stationName).getName()).isEqualTo(station.getName());
    }

    @Test
    @DisplayName("역 삭제")
    void 역_삭제() {
        //given
        String stationName = "노원역";
        Station station = new Station(stationName);
        String nextStationName = "상계역";
        Station nextStation = new Station(nextStationName);
        StationRepository stationRepository = new StationRepository();
        stationRepository.addStation(station);
        stationRepository.addStation(nextStation);
        //when
        stationRepository.deleteStation(nextStationName);

        //then
        assertThat(stationRepository.stations().stream().iterator().next()).isEqualTo(
                stationRepository.getStation(stationName));
    }
}
