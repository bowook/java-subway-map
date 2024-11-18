package subway.service;

import java.util.List;
import subway.domain.Line;
import subway.domain.Route;
import subway.domain.Station;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;
import subway.repository.LineRepository;
import subway.repository.RouteRepository;
import subway.repository.StationRepository;

public class StationService {
    private final StationRepository stationRepository;
    private final LineRepository lineRepository;
    private final RouteRepository routeRepository;

    public StationService(StationRepository stationRepository, LineRepository lineRepository,
                          RouteRepository routeRepository) {
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
        this.routeRepository = routeRepository;
    }

    public void registerStation(String stationName) {
        if (stationRepository.getStation(stationName) != null) {
            throw SubwayException.from(ErrorMessage.ALREADY_REGISTER_STATION_NAME);
        }
        stationRepository.addStation(new Station(stationName));
    }

    public void deleteStation(String stationName) {
        Station station = stationRepository.getStation(stationName);
        for (Route route : routeRepository.routes()) {
            isLineStation(station, route);
        }
        if (!stationRepository.deleteStation(stationName)) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
    }

    private void isLineStation(Station station, Route route) {
        for (List<Station> stations : route.getRoute().values()) {
            if (stations.contains(station)) {
                throw SubwayException.from(ErrorMessage.REGISTERED_STATION_NAME);
            }
        }
    }

    public List<Station> checkStation() {
        return stationRepository.stations();
    }

    public Line registerLine(String lineName) {
        if (lineRepository.findLineByName(lineName) != null) {
            throw SubwayException.from(ErrorMessage.ALREADY_REGISTER_LINE_NAME);
        }
        Line newLine = new Line(lineName);
        lineRepository.addLine(newLine);
        return newLine;
    }

    public void registerStartLine(Line line, String startStationName) {
        Station station = stationRepository.getStation(startStationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        routeRepository.addRoute(new Route(line, station));
    }

    public void registerEndLine(Line line, String endStationName) {
        Station station = stationRepository.getStation(endStationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        Route route = routeRepository.findRouteByLine(line);
        route.getRoute().get(line).add(station);
    }

    public void deleteLine(String lineName) {
        Line line = lineRepository.findLineByName(lineName);
        if (line == null) {
            throw SubwayException.from(ErrorMessage.LINE_NAME_NOT_PRESENCE);
        }
        routeRepository.deleteRouteByLine(line);
        lineRepository.deleteLineByName(lineName);
    }

    public Line registerLineByRoute(String lineName) {
        Line line = lineRepository.findLineByName(lineName);
        if (line == null) {
            throw SubwayException.from(ErrorMessage.LINE_NAME_NOT_PRESENCE);
        }
        return line;
    }

    public Station registerStationByRoute(String stationName) {
        Station station = stationRepository.getStation(stationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        return station;
    }

    public int registerOrderByRoute(String order) {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(order);
        } catch (NumberFormatException numberFormatException) {
            throw SubwayException.from(ErrorMessage.ORDER_IS_NOT_NUMBER);
        }
        if (parsedNumber <= 0) {
            throw SubwayException.from(ErrorMessage.ORDER_IS_NOT_MINUS);
        }
        return parsedNumber;
    }

    public void registerRoute(Line line, Station station, int order) {
        Route route = routeRepository.findRouteByLine(line);
        route.addByOrder(station, order);
    }

    public Line deleteLineByRoute(String lineName) {
        Line line = lineRepository.findLineByName(lineName);
        if (line == null) {
            throw SubwayException.from(ErrorMessage.LINE_NAME_NOT_PRESENCE);
        }
        Route route = routeRepository.findRouteByLine(line);
        for (List<Station> stations : route.getRoute().values()) {
            if (stations.size() <= 2) {
                throw SubwayException.from(ErrorMessage.DELETE_LINE_BY_ROUTE);
            }
        }
        return line;
    }

    public Station deleteStationByRoute(String stationName) {
        Station station = stationRepository.getStation(stationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        return station;
    }

    public void deleteRoute(Line line, Station station) {
        Route route = routeRepository.findRouteByLine(line);
        route.deleteStation(station);
    }

    public List<Line> lines() {
        return lineRepository.lines();
    }

    public List<Route> routes() {
        return routeRepository.routes();
    }
}
