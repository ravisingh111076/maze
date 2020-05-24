package uk.gov.dwp.maze.gui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
public class MazeComponentDataSet {
    private MazeSpaceComponent startMazeSpaceComponent;
    private MazeSpaceComponent exitMazeSpaceComponent;
    @Builder.Default
    private List<MazeSpaceComponent> mazeSpaceComponents = new ArrayList<>();
}
