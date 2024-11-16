package subway.view;

public class OutputView {
    private final static String MAIN_SCREEN = "## 메인 화면";
    private final static String STATION_MANAGEMENT = "1. 역 관리";
    private final static String ROUTE_MANAGEMENT = "2. 노선 관리";
    private final static String PANEL_MANAGEMENT = "3. 구간 관리";
    private final static String WRITE_SUBWAY_ROUTE = "4. 지하철 노선도 출력";
    private final static String EXIT = "Q. 종료";

    public void writeMainScreen() {
        System.out.println(MAIN_SCREEN);
        System.out.println(STATION_MANAGEMENT);
        System.out.println(ROUTE_MANAGEMENT);
        System.out.println(PANEL_MANAGEMENT);
        System.out.println(WRITE_SUBWAY_ROUTE);
        System.out.println(EXIT);
        System.out.print(writeNewLine());
    }

    public void writeErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    private String writeNewLine() {
        return System.lineSeparator();
    }
}
