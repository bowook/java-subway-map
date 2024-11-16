package subway.controller;

import subway.dto.MainSelectionDTO;
import subway.dto.StationSelectionDTO;
import subway.exception.SubwayException;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final StationService stationService;

    public SubwayController(final InputView inputView, final OutputView outputView, final StationService service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stationService = service;
    }

    public void start() {
        while (true) {
            outputView.writeMainScreen();
            MainSelectionDTO selectionDTO = getValidatedSelection();
            if (selectionDTO.getSelection().equals("Q")) {
                break;
            }
            transmissionSelection(selectionDTO);
        }
    }

    private void stationManagement() {
        while (true) {
            outputView.writeStationScreen();
            StationSelectionDTO stationSelectionDTO = getValidatedStationSelection();
            if (stationSelectionDTO.getSelection().equals("B")) {
                break;
            }
            stationSelection(stationSelectionDTO);
        }
    }

    private void stationSelection(StationSelectionDTO stationSelectionDTO) {
        if (stationSelectionDTO.getSelection().equals("1")) {
            registerStation();
        }
    }

    private void transmissionSelection(MainSelectionDTO selectionDTO) {
        if (selectionDTO.getSelection().equals("1")) {
            stationManagement();
        }
    }

    private void registerStation() {
        while (true) {
            try {
                stationService.registerStation(inputView.readStationName());
                outputView.writeStationRegisterInfo();
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private StationSelectionDTO getValidatedStationSelection() {
        while (true) {
            try {
                return new StationSelectionDTO(inputView.readHopeFunction());
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
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
