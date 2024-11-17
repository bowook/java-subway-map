package subway.view;

import java.util.Scanner;

public class InputView {
    private final static String CHOOSE_HOPE_FUNCTION = "## 원하는 기능을 선택하세요.";
    private final static String REGISTER_STATION = "## 등록할 역 이름을 입력하세요.";
    private final static String DELETE_STATION = "## 삭제할 역 이름을 입력하세요.";
    private final static String REGISTER_LINE = "## 등록할 노선 이름을 입력하세요.";
    private final static String REGISTER_START_LINE = "## 등록할 노선의 상행 종점역 이름을 입력하세요.";
    private final static String REGISTER_END_LINE = "## 등록할 노선의 하행 종점역 이름을 입력하세요.";

    public String readStartLine() {
        System.out.print(System.lineSeparator());
        System.out.println(REGISTER_START_LINE);
        return scannerInput();
    }

    public String readEndLine() {
        System.out.print(System.lineSeparator());
        System.out.println(REGISTER_END_LINE);
        return scannerInput();
    }

    public String readHopeFunction() {
        System.out.println(CHOOSE_HOPE_FUNCTION);
        return scannerInput();
    }

    public String readStationName() {
        System.out.print(System.lineSeparator());
        System.out.println(REGISTER_STATION);
        return scannerInput();
    }

    private String scannerInput() {
        return getScanner().nextLine();
    }

    private Scanner getScanner() {
        return new Scanner(System.in);
    }

    private void scannerClose() {
        getScanner().close();
    }

    public String readDeleteStationName() {
        System.out.print(System.lineSeparator());
        System.out.println(DELETE_STATION);
        return scannerInput();
    }

    public String readLineName() {
        System.out.print(System.lineSeparator());
        System.out.println(REGISTER_LINE);
        return scannerInput();
    }
}
