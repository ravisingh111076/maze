package uk.gov.dwp.maze.service;
import uk.gov.dwp.maze.domain.MazeDataSet;
import java.io.InputStream;
import java.util.function.Function;

public interface MazeDataService {
    MazeDataSet getMazeData();
    interface BuildInputStream extends Function<String,InputStream>{};
    interface TransformTextDataToMazeDataSet extends Function<InputStream, MazeDataSet> {};

}
