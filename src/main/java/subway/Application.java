package subway;

import subway.config.Config;
import subway.controller.SubwayController;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        SubwayController subwayController = config.subwayController();
        subwayController.start();
    }
}
