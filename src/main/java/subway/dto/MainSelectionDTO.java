package subway.dto;

import java.util.List;

public class FunctionSelectionDTO {
    private final List<String> functionInventory = List.of("1", "2", "3", "4", "Q");
    private final String number;

    public FunctionSelectionDTO(String number) {
        validate(number);
        this.number = number;
    }

    private void validate(String number) {
    }
}
