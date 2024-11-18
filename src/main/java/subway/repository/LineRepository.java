package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.domain.Line;

public class LineRepository {
    private final List<Line> lines = new ArrayList<>();

    public List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public Line findLineByName(String lineName) {
        return lines.stream()
                .filter(line -> line.getName().equals(lineName))
                .findFirst()
                .orElse(null);
    }
}
