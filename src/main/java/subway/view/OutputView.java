package subway.view;

import java.util.List;
import subway.domain.Station;

public class OutputView {
    private final static String MAIN_SCREEN = "## 메인 화면";
    private final static String STATION_MANAGEMENT = "1. 역 관리";
    private final static String ROUTE_MANAGEMENT = "2. 노선 관리";
    private final static String LINE_MANAGEMENT = "3. 구간 관리";
    private final static String WRITE_SUBWAY_LINE = "4. 지하철 노선도 출력";
    private final static String EXIT = "Q. 종료";

    //-------------------------------------------------------------------//
    private final static String STATION_SCREEN = "## 역 관리 화면";
    private final static String STATION_REGISTER = "1. 역 등록";
    private final static String STATION_DELETE = "2. 역 삭제";
    private final static String STATION_SELECT = "3. 역 조회";
    private final static String BACK = "B. 돌아가기";
    private final static String INFO_STATION_REGISTER = "[INFO] 지하철 역이 등록되었습니다.";
    private final static String INFO_STATION_DELETE = "[INFO] 지하철 역이 삭제되었습니다.";
    private final static String INFO = "[INFO] ";

    public void writeMainScreen() {
        System.out.print(writeNewLine());
        System.out.println(MAIN_SCREEN);
        System.out.println(STATION_MANAGEMENT);
        System.out.println(ROUTE_MANAGEMENT);
        System.out.println(LINE_MANAGEMENT);
        System.out.println(WRITE_SUBWAY_LINE);
        System.out.println(EXIT);
        System.out.print(writeNewLine());
    }

    public void writeStationScreen() {
        System.out.print(writeNewLine());
        System.out.println(STATION_SCREEN);
        System.out.println(STATION_REGISTER);
        System.out.println(STATION_DELETE);
        System.out.println(STATION_SELECT);
        System.out.println(BACK);
        System.out.print(writeNewLine());
    }

    public void writeStationInventory(List<Station> stations) {
        for (Station station : stations) {
            System.out.println(INFO + station.getName());
        }
    }

    public void writeErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    private String writeNewLine() {
        return System.lineSeparator();
    }

    public void writeStationRegisterInfo() {
        System.out.print(writeNewLine());
        System.out.println(INFO_STATION_REGISTER);
    }

    public void writeStationDelete() {
        System.out.print(writeNewLine());
        System.out.println(INFO_STATION_DELETE);
    }
}
