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

    public void deleteStation(String stationName) {
        //노선에 추가 되어있는 역이면 삭제 못하게 해야됨 추후에
        if (!StationRepository.deleteStation(stationName)) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
    }
}
