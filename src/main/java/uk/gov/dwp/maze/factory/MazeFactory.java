package uk.gov.dwp.maze.factory;

import uk.gov.dwp.maze.domain.EndMazeSpace;
import uk.gov.dwp.maze.domain.MazeSpace;
import uk.gov.dwp.maze.domain.MazeSpaceType;
import uk.gov.dwp.maze.domain.StartMazeSpace;

public class MazeFactory {

    public static MazeSpace buildMazeBlock(char mazeType, int x, int y) {
            switch (mazeType) {
                case 'S' :
                    return StartMazeSpace.startMazeBuilder()
                            .face(Character.toString(mazeType))
                            .xCoordinate(x).yCoordinate(y).build();
                case 'F':
                    return EndMazeSpace.exitMazeBlock()
                            .face(Character.toString(mazeType))
                            .xCoordinate(x).yCoordinate(y).build();
                case 'X':
                    return MazeSpace.builder()
                            .face(Character.toString(mazeType))
                            .blockType(MazeSpaceType.WallSpace)
                            .xCoordinate(x).yCoordinate(y).build();
                 default:
                    return MazeSpace.builder()
                            .face(Character.toString(mazeType))
                            .blockType(MazeSpaceType.emptySpace)
                            .xCoordinate(x).yCoordinate(y).build();
            }
        }
}
