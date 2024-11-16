package subway.view;

import java.util.Scanner;

public class InputView {
    private final static String CHOOSE_HOPE_FUNCTION = "## 원하는 기능을 선택하세요.";

    public String readHopeFunction() {
        System.out.println(CHOOSE_HOPE_FUNCTION);
        return scannerInput();
    }

    private String scannerInput() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
