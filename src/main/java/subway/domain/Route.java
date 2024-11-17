package subway.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Route {
    private final Map<Line, List<Station>> route = new HashMap<>();

    public Route(Line line, Station station) {
        route.putIfAbsent(line, new ArrayList<>());
        route.get(line).add(station);
    }

    public Map<Line, List<Station>> getRoute() {
        return route;
    }
}
