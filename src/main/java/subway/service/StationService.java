package subway.service;

import subway.domain.Station;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;
import subway.repository.StationRepository;

public class StationService {
    public void registerStation(String stationName) {
        if (StationRepository.existsByName(stationName)) {
            throw SubwayException.from(ErrorMessage.ALREADY_REGISTER_STATION_NAME);
        }
        StationRepository.addStation(new Station(stationName));
    }
}
