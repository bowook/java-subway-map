package subway.controller;

import subway.domain.Line;
import subway.domain.Station;
import subway.dto.MainSelectionDTO;
import subway.dto.RouteSelectionDTO;
import subway.dto.StationSelectionDTO;
import subway.exception.SubwayException;
import subway.repository.LineRepository;
import subway.repository.RouteRepository;
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

    private void mainSelection(MainSelectionDTO selectionDTO) {
        if (selectionDTO.getSelection().equals("1")) {
            stationManagement();
        }
        if (selectionDTO.getSelection().equals("2")) {
            lineManagement();
        }
        if (selectionDTO.getSelection().equals("3")) {
            routeManagement();
        }
        if (selectionDTO.getSelection().equals("4")) {
            outputView.writeAllRoutes(RouteRepository.routes());
        }
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

    private void lineSelection(StationSelectionDTO stationSelectionDTO) {
        if (stationSelectionDTO.getSelection().equals("1")) {
            registerLine();
            return;
        }
        if (stationSelectionDTO.getSelection().equals("2")) {
            deleteLine();
            return;
        }
        if (stationSelectionDTO.getSelection().equals("3")) {
            outputView.writeLineCheck(LineRepository.lines());
        }
    }

    private void routeSelection(RouteSelectionDTO routeSelectionDTO) {
        if (routeSelectionDTO.getSelection().equals("1")) {
            registerRoute();
        }
        if (routeSelectionDTO.getSelection().equals("2")) {
            deleteRoute();
        }
    }

    private void stationManagement() {
        while (true) {
            try {
                outputView.writeStationScreen();
                StationSelectionDTO stationSelectionDTO = getValidatedStationSelection();
                stationSelection(stationSelectionDTO);
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void lineManagement() {
        while (true) {
            try {
                outputView.writeLineScreen();
                StationSelectionDTO stationSelectionDTO = getValidatedStationSelection();
                lineSelection(stationSelectionDTO);
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void routeManagement() {
        while (true) {
            try {
                outputView.writeRouteScreen();
                RouteSelectionDTO routeSelectionDTO = getValidatedRouteSelection();
                routeSelection(routeSelectionDTO);
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void registerStation() {
        stationService.registerStation(inputView.readStationName());
        outputView.writeStationRegisterInfo();
    }

    private void registerLine() {
        String lineName = inputView.readLineName();
        Line newLine = stationService.registerLine(lineName);
        stationService.registerStartLine(newLine, inputView.readStartLine());
        stationService.registerEndLine(newLine, inputView.readEndLine());
    }

    private void registerRoute() {
        Line line = stationService.registerLineByRoute(inputView.readLineByRoute());
        Station station = stationService.registerStationByRoute(inputView.readStationByRoute());
        int order = stationService.registerOrderByRoute(inputView.readOrderByRoute());
        stationService.registerRoute(line, station, order);
        outputView.writeRouteRegister();
    }

    private void deleteStation() {
        stationService.deleteStation(inputView.readDeleteStationName());
        outputView.writeStationDelete();
    }

    private void deleteLine() {
        stationService.deleteLine(inputView.readDeleteLineName());
        outputView.writeLineDelete();
    }

    private void deleteRoute() {
        Line line = stationService.deleteLineByRoute(inputView.readDeleteLineNameByRoute());
        Station station = stationService.deleteStationByRoute(inputView.readDeleteStationNameByRoute());
        stationService.deleteRoute(line, station);
        outputView.writeRouteDelete();
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

    private StationSelectionDTO getValidatedStationSelection() {
        while (true) {
            try {
                return new StationSelectionDTO(inputView.readHopeFunction());
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private RouteSelectionDTO getValidatedRouteSelection() {
        while (true) {
            try {
                return new RouteSelectionDTO(inputView.readHopeFunction());
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

}
