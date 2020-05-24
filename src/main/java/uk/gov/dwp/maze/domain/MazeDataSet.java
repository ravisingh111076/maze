package uk.gov.dwp.maze.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MazeDataSet {
    @Setter
    @Getter
    private MazeSpace startMazeSpace;
    @Setter
    @Getter
    private MazeSpace exitMazeSpace;

    @Getter
    private List<MazeSpace> mazeSpaces = new ArrayList<>();
}
