package uk.gov.dwp.maze.gui.service;

import uk.gov.dwp.maze.domain.MazeDataSet;
import uk.gov.dwp.maze.gui.MazeComponentDataSet;

import java.util.Optional;
import java.util.function.Function;

/**
 * Contract implementation should call provide maze data
 * to build display components
 */
public interface MazeUIComponentService {
    Optional<MazeComponentDataSet> getMazeComponent();
    interface TransformMazeDataIntoUIComponent extends Function<MazeDataSet, MazeComponentDataSet>{};
}
