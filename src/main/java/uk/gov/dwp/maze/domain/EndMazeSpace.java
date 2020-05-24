package uk.gov.dwp.maze.domain;

import lombok.Builder;
public class EndMazeSpace extends MazeSpace {

    @Builder(builderMethodName = "exitMazeBlock")
    public EndMazeSpace(String face,
                        int xCoordinate,
                        int yCoordinate) {
        super(face,xCoordinate,yCoordinate, MazeSpaceType.EndSpace);
    }
}
