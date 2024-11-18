package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import subway.domain.Line;
import subway.domain.Route;

public class RouteRepository {
    private final List<Route> routes = new ArrayList<>();

    public List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public Route findRouteByLine(Line line) {
        for (Route route : routes) {
            if (route.getRoute().containsKey(line)) {
                return route;
            }
        }
        return null;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void deleteRouteByLine(Line line) {
        routes.removeIf(route -> route.getRoute().containsKey(line));
    }
}
