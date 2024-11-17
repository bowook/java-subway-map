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
    public void registerStation(String stationName) {
        if (StationRepository.getStation(stationName) != null) {
            throw SubwayException.from(ErrorMessage.ALREADY_REGISTER_STATION_NAME);
        }
        StationRepository.addStation(new Station(stationName));
    }

    public void deleteStation(String stationName) {
        Station station = StationRepository.getStation(stationName);
        for (Route route : RouteRepository.routes()) {
            isLineStation(station, route);
        }
        if (!StationRepository.deleteStation(stationName)) {
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
        return StationRepository.stations();
    }

    public Line registerLine(String lineName) {
        if (LineRepository.findLineByName(lineName) != null) {
            throw SubwayException.from(ErrorMessage.ALREADY_REGISTER_LINE_NAME);
        }
        Line newLine = new Line(lineName);
        LineRepository.addLine(newLine);
        return newLine;
    }

    public void registerStartLine(Line line, String startStationName) {
        Station station = StationRepository.getStation(startStationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        RouteRepository.addRoute(new Route(line, station));
    }

    public void registerEndLine(Line line, String endStationName) {
        Station station = StationRepository.getStation(endStationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        Route route = RouteRepository.findRouteByLine(line);
        route.getRoute().get(line).add(station);
    }

    public void deleteLine(String lineName) {
        Line line = LineRepository.findLineByName(lineName);
        if (line == null) {
            throw SubwayException.from(ErrorMessage.LINE_NAME_NOT_PRESENCE);
        }
        RouteRepository.deleteRouteByLine(line);
        LineRepository.deleteLineByName(lineName);
    }

    public Line registerLineByRoute(String lineName) {
        Line line = LineRepository.findLineByName(lineName);
        if (line == null) {
            throw SubwayException.from(ErrorMessage.LINE_NAME_NOT_PRESENCE);
        }
        return line;
    }

    public Station registerStationByRoute(String stationName) {
        Station station = StationRepository.getStation(stationName);
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
        Route route = RouteRepository.findRouteByLine(line);
        route.addByOrder(station, order);
    }

    public Line deleteLineByRoute(String lineName) {
        Line line = LineRepository.findLineByName(lineName);
        if (line == null) {
            throw SubwayException.from(ErrorMessage.LINE_NAME_NOT_PRESENCE);
        }
        Route route = RouteRepository.findRouteByLine(line);
        for (List<Station> stations : route.getRoute().values()) {
            if (stations.size() <= 2) {
                throw SubwayException.from(ErrorMessage.DELETE_LINE_BY_ROUTE);
            }
        }
        return line;
    }

    public Station deleteStationByRoute(String stationName) {
        Station station = StationRepository.getStation(stationName);
        if (station == null) {
            throw SubwayException.from(ErrorMessage.SUBWAY_STATION_NOT_PRESENCE);
        }
        return station;
    }

    public void deleteRoute(Line line, Station station) {
        Route route = RouteRepository.findRouteByLine(line);
        route.deleteStation(station);
    }
}
