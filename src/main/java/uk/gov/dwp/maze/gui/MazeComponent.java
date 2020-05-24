package uk.gov.dwp.maze.gui;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
public class MazeComponent {

    private MazeSpaceComponent startMazeSpaceComponent;

    private MazeSpaceComponent exitMazeSpaceComponent;

    private List<MazeSpaceComponent> mazeSpaceComponents = new ArrayList<>();
}
