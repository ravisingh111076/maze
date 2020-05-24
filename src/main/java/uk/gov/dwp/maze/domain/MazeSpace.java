package uk.gov.dwp.maze.domain;
import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
public class MazeSpace {
    private String face;
    private int xCoordinate;
    private int yCoordinate;
    MazeSpaceType blockType;

    public MazeSpace(String face, int x, int y, MazeSpaceType mazeBlock) {
        this.face = face;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.blockType = mazeBlock;
    }
}
