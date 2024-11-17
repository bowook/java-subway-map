package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.domain.Station;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static Station getStation(String name) {
        return stations.stream()
                .filter(station -> Objects.equals(station.getName(), name))
                .findFirst()
                .orElse(null);
    }

}
