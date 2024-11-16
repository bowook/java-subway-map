package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final List<Station> route;

    public Route() {
        route = new ArrayList<>();
    }

    public List<Station> getRoute() {
        return route;
    }
}
