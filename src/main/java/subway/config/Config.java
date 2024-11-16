package subway.config;

import subway.controller.SubwayController;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class Config {

    public SubwayController subwayController() {
        return new SubwayController(inputView(), outputView(), stationService());
    }

    private StationService stationService() {
        return new StationService();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
