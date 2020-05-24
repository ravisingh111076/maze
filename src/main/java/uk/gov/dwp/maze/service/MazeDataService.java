package uk.gov.dwp.maze.service;
import uk.gov.dwp.maze.domain.Maze;
import java.io.InputStream;
import java.util.function.Function;

public interface MazeDataService {
    Maze getMazeData();
    interface BuildInputStream extends Function<String,InputStream>{};
    interface TransformTextDataToMaze extends Function<InputStream, Maze> {};

}
