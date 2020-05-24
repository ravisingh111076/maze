package uk.gov.dwp.maze.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartMazeSpace extends MazeSpace {
    @Builder(builderMethodName = "startMazeBuilder")
    public StartMazeSpace(String face,
                          int xCoordinate,
                          int yCoordinate) {
        super(face, xCoordinate, yCoordinate, MazeSpaceType.StartSpace);
    }
}
