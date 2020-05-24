package uk.gov.dwp.maze.gui.service;

import uk.gov.dwp.maze.domain.MazeDataSet;
import uk.gov.dwp.maze.gui.MazeComponent;

import java.util.function.Function;

/**
 * Contract implementation should call provide maze data
 * to build display components
 */
public interface MazeUIComponentService {
    MazeComponent getMazeComponent();
    interface TransformMazeDataIntoUIComponent extends Function<MazeDataSet,MazeComponent>{};
}
