package subway.config;

import subway.controller.SubwayController;
import subway.repository.LineRepository;
import subway.repository.RouteRepository;
import subway.repository.StationRepository;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class Config {

    public SubwayController subwayController() {
        return new SubwayController(inputView(), outputView(), stationService());
    }

    private StationService stationService() {

        return new StationService(stationRepository(), lineRepository(), routeRepository());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private StationRepository stationRepository() {
        return new StationRepository();
    }

    private LineRepository lineRepository() {
        return new LineRepository();
    }

    private RouteRepository routeRepository() {
        return new RouteRepository();
    }
}
