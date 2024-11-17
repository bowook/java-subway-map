package subway.controller;

import subway.domain.Line;
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
            mainSelection(selectionDTO);
        }
    }

    private void stationManagement() {
        outputView.writeStationScreen();
        StationSelectionDTO stationSelectionDTO = getValidatedStationSelection();
        stationSelection(stationSelectionDTO);
    }

    private void stationSelection(StationSelectionDTO stationSelectionDTO) {
        if (stationSelectionDTO.getSelection().equals("1")) {
            registerStation();
            return;
        }
        if (stationSelectionDTO.getSelection().equals("2")) {
            deleteStation();
            return;
        }
        if (stationSelectionDTO.getSelection().equals("3")) {
            outputView.writeStationInventory(stationService.checkStation());
        }
    }


    private void deleteStation() {
        while (true) {
            try {
                stationService.deleteStation(inputView.readDeleteStationName());
                outputView.writeStationDelete();
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void mainSelection(MainSelectionDTO selectionDTO) {
        if (selectionDTO.getSelection().equals("1")) {
            stationManagement();
            return;
        }
        if (selectionDTO.getSelection().equals("2")) {
            lineManagement();
        }
    }

    private void lineManagement() {
        outputView.writeLineScreen();
        StationSelectionDTO stationSelectionDTO = getValidatedStationSelection();
        lineSelection(stationSelectionDTO);
    }

    private void lineSelection(StationSelectionDTO stationSelectionDTO) {
        if (stationSelectionDTO.getSelection().equals("1")) {
            registerLine();
        }
    }

    private void registerLine() {
        while (true) {
            try {
                String lineName = inputView.readLineName();
                Line newLine = stationService.registerLine(lineName);
                registerStartLine(newLine);
                registerEndLine(newLine);
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void registerStartLine(Line newLine) {
        while (true) {
            try {
                stationService.registerStartLine(newLine, inputView.readStartLine());
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void registerEndLine(Line newLine) {
        while (true) {
            try {
                stationService.registerEndLine(newLine, inputView.readEndLine());
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
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
