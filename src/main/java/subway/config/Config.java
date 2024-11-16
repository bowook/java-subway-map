package subway.config;

import subway.controller.SubwayController;
import subway.service.SelectionService;
import subway.view.InputView;
import subway.view.OutputView;

public class Config {

    public SubwayController subwayController() {
        return new SubwayController(inputView(), outputView(), selectionService());
    }

    private SelectionService selectionService() {
        return new SelectionService();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
