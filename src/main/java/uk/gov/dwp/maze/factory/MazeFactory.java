package uk.gov.dwp.maze.factory;

import uk.gov.dwp.maze.domain.MazeSpace;
import uk.gov.dwp.maze.domain.MazeSpaceType;

public class MazeFactory {

    public static MazeSpace buildMazeBlock(char mazeType, int x, int y) {
            switch (mazeType) {
                case 'S' :
                    return MazeSpace.builder()
                            .face(Character.toString(mazeType))
                            .blockType(MazeSpaceType.StartSpace)
                            .xCoordinate(x).yCoordinate(y).build();
                case 'F':
                    return MazeSpace.builder()
                            .face(Character.toString(mazeType))
                            .blockType(MazeSpaceType.EndSpace)
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
