package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import subway.domain.Line;
import subway.domain.Route;

public class RouteRepository {
    private static final List<Route> routes = new ArrayList<>();

    public static List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public static Route findRouteByLine(Line line) {
        for (Route route : routes) {
            if (route.getRoute().containsKey(line)) {
                return route;
            }
        }
        return null;
    }

    public static void addRoute(Route route) {
        routes.add(route);
    }
}
