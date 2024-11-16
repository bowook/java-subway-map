package subway.view;

import java.util.Scanner;

public class InputView {
    private final static String CHOOSE_HOPE_FUNCTION = "## 원하는 기능을 선택하세요.";
    private final static String REGISTER_STATION = "## 등록할 역 이름을 입력하세요.";

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
}
