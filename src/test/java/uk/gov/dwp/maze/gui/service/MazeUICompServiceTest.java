package uk.gov.dwp.maze.gui.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.dwp.maze.domain.MazeDataSet;
import uk.gov.dwp.maze.domain.MazeSpace;
import uk.gov.dwp.maze.domain.MazeSpaceType;
import uk.gov.dwp.maze.factory.MazeFactory;
import uk.gov.dwp.maze.gui.MazeComponentDataSet;
import uk.gov.dwp.maze.service.MazeDataService;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MazeUICompServiceTest {

    @Mock
    MazeDataService mazeDataService;

    @InjectMocks
    MazeUIComponentServiceImpl mazeUIComponentComponentService;

    @Test
    public void testGetMazeComponent_WhenMazeDataServiceReturnValidMaze() {
        MazeDataSet maze = new MazeDataSet();
        MazeSpace startSpace = MazeFactory.buildMazeBlock(
                MazeSpaceType.EndSpace.getValue(), 0, 14);
        MazeSpace endSpace = MazeFactory.buildMazeBlock(
                MazeSpaceType.StartSpace.getValue(), 0, 0);
        maze.setExitMazeSpace(endSpace);
        maze.setStartMazeSpace(startSpace);
        maze.getMazeSpaces().addAll(Arrays.asList(startSpace,endSpace));
        when(mazeDataService.getMazeData()).thenReturn(maze);
        MazeComponentDataSet component = mazeUIComponentComponentService.getMazeComponent().get();
        assertEquals(2, component.getMazeSpaceComponents().size());
    }
}
