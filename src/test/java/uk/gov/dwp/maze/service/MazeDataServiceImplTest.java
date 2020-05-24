package uk.gov.dwp.maze.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.dwp.maze.config.MazeConfiguration;
import uk.gov.dwp.maze.domain.MazeDataSet;
import uk.gov.dwp.maze.exception.InvalidMazeException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MazeDataServiceImplTest {

    @Mock
    MazeConfiguration mazeConfiguration;
    MazeDataServiceImpl mazeDataService;

    @Test
    public void getMazeData_WhenDataIsCorrectThenReturnMaze() {
        when(mazeConfiguration.getCol()).thenReturn("15");
        when(mazeConfiguration.getRow()).thenReturn("15");
        when(mazeConfiguration.getFilePath()).thenReturn("Maze1.txt");
        mazeDataService = new MazeDataServiceImpl(mazeConfiguration);
        MazeDataSet maze = mazeDataService.getMazeData();
        assertEquals(225, maze.getMazeSpaces().size());
        assertNotNull(maze.getExitMazeSpace());
        assertNotNull(maze.getStartMazeSpace());
    }

    @Test(expected = InvalidMazeException.class)
    public void getMazeData_WhenNoStartSpaceExistThenReturnException() {
        when(mazeConfiguration.getCol()).thenReturn("15");
        when(mazeConfiguration.getRow()).thenReturn("15");
        when(mazeConfiguration.getFilePath()).thenReturn("BadMaze.txt");
        mazeDataService = new MazeDataServiceImpl(mazeConfiguration);
        mazeDataService.getMazeData();
    }
    @Test(expected = InvalidMazeException.class)
    public void getMazeData_WhenMoreThanOneStartSpaceExistThenReturnException() {
        when(mazeConfiguration.getCol()).thenReturn("15");
        when(mazeConfiguration.getRow()).thenReturn("15");
        when(mazeConfiguration.getFilePath()).thenReturn("BadMaze_MutipleStart.txt");
        mazeDataService = new MazeDataServiceImpl(mazeConfiguration);
        mazeDataService.getMazeData();
    }
}
