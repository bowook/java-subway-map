package subway.dto;

import java.util.List;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class RouteSelectionDTO {
    private final List<String> functionInventory = List.of("1", "2", "B");
    private final String selection;

    public RouteSelectionDTO(String selection) {
        validate(selection);
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    private void validate(String selection) {
        if (!functionInventory.contains(selection)) {
            throw SubwayException.from(ErrorMessage.UNABLE_TO_CHOOSE_FUNCTION);
        }
    }
}
