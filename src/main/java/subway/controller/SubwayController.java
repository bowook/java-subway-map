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

    private void routeManagement() {
        outputView.writeRouteScreen();
        RouteSelectionDTO routeSelectionDTO = getValidatedRouteSelection();
        routeSelection(routeSelectionDTO);
    }

    private void routeSelection(RouteSelectionDTO routeSelectionDTO) {
        if (routeSelectionDTO.getSelection().equals("1")) {
            registerRoute();
        }
        if (routeSelectionDTO.getSelection().equals("2")) {
            deleteRoute();
        }
    }

    private void deleteRoute() {
        while (true) {
            try {
                Line line = stationService.deleteLineByRoute(inputView.readDeleteLineNameByRoute());
                Station station = deleteStationByRoute();
                stationService.deleteRoute(line, station);
                outputView.writeRouteDelete();
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private Station deleteStationByRoute() {
        while (true) {
            try {
                return stationService.deleteStationByRoute(inputView.readDeleteStationNameByRoute());
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private void registerRoute() {
        while (true) {
            try {
                Line line = stationService.registerLineByRoute(inputView.readLineByRoute());
                Station station = registerStationByRoute();
                int order = registerOrderByRoute();
                stationService.registerRoute(line, station, order);
                outputView.writeRouteRegister();
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private int registerOrderByRoute() {
        while (true) {
            try {
                return stationService.registerOrderByRoute(inputView.readOrderByRoute());
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
        }
    }

    private Station registerStationByRoute() {
        while (true) {
            try {
                return stationService.registerStationByRoute(inputView.readStationByRoute());
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

    private void lineManagement() {
        outputView.writeLineScreen();
        StationSelectionDTO stationSelectionDTO = getValidatedStationSelection();
        lineSelection(stationSelectionDTO);
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

    private void deleteLine() {
        while (true) {
            try {
                stationService.deleteLine(inputView.readDeleteLineName());
                outputView.writeLineDelete();
                break;
            } catch (SubwayException subwayException) {
                outputView.writeErrorMessage(subwayException.getMessage());
            }
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
