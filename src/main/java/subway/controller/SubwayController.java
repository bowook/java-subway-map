package subway.controller;

import subway.dto.MainSelectionDTO;
import subway.exception.SubwayException;
import subway.service.SelectionService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final SelectionService selectionService;

    public SubwayController(final InputView inputView, final OutputView outputView, final SelectionService service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.selectionService = service;
    }

    public void start() {
        while (true) {
            outputView.writeMainScreen();
            MainSelectionDTO selectionDTO = getValidatedSelection();
            if (selectionDTO.getSelection().equals("Q")) {
                break;
            }

        }
    }

    private MainSelectionDTO getValidatedSelection() {
        while (true) {
            try {
                return new MainSelectionDTO(inputView.readHopeFunction());
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

}
