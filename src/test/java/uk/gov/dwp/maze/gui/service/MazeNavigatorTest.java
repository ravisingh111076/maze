package uk.gov.dwp.maze.gui.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.dwp.maze.domain.MazeSpace;
import uk.gov.dwp.maze.domain.MazeSpaceType;
import uk.gov.dwp.maze.gui.MazeComponentDataSet;
import uk.gov.dwp.maze.gui.MazeNavigator;
import uk.gov.dwp.maze.gui.MazeSpaceComponent;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;

/**
 * 2*2 matrix no wall
 * S
 * F
 */

@RunWith(MockitoJUnitRunner.class)
public class MazeNavigatorTest {

    MazeNavigator mazeNavigator;

    @Test
    public void testNavigation_WhenRightAndDownEmptySpace() {
        //START EMPTY
        //END EMPTY
        MazeSpaceComponent mazeStartComp = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder().xCoordinate(0).yCoordinate(0)
                        .blockType(MazeSpaceType.StartSpace).build()).build();

        MazeSpaceComponent mazeEmptyComp1 = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder()
                        .blockType(MazeSpaceType.emptySpace).xCoordinate(1).yCoordinate(0).build()).build();
        MazeSpaceComponent mazeEmptyComp2 = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder()
                        .blockType(MazeSpaceType.emptySpace).xCoordinate(1).yCoordinate(1).build()).build();

        MazeSpaceComponent mazeEndComp = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder().xCoordinate(0).yCoordinate(1)
                        .blockType(MazeSpaceType.EndSpace).build()).build();

        MazeComponentDataSet mazeComponentDataSet =
                MazeComponentDataSet.builder()
                        .startMazeSpaceComponent(mazeStartComp)
                        .exitMazeSpaceComponent(mazeEndComp)
                        .mazeSpaceComponents(Arrays.asList(mazeStartComp,mazeEmptyComp1,mazeEmptyComp2,mazeEndComp))
                        .build();

        mazeNavigator = new MazeNavigator(mazeComponentDataSet,2,2);
        assertTrue(mazeNavigator.moveRight().isPresent());
        assertTrue(mazeNavigator.moveDown().isPresent());
    }

    @Test
    public void testNavigation_WhenLeftAndDownEmptySpace() {
        //END EMPTY
        //EMPTY S
        MazeSpaceComponent mazeEndComp = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder().xCoordinate(0).yCoordinate(0)
                        .blockType(MazeSpaceType.EndSpace).build()).build();

        MazeSpaceComponent mazeEmptyComp1 = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder()
                        .blockType(MazeSpaceType.emptySpace).xCoordinate(1).yCoordinate(0).build()).build();
        MazeSpaceComponent mazeEmptyComp2 = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder()
                        .blockType(MazeSpaceType.emptySpace).xCoordinate(0).yCoordinate(1).build()).build();

        MazeSpaceComponent mazeStartComp = MazeSpaceComponent.builder()
                .mazeSpace(MazeSpace.builder().xCoordinate(1).yCoordinate(1)
                        .blockType(MazeSpaceType.EndSpace).build()).build();

                MazeComponentDataSet mazeComponentDataSet =
                MazeComponentDataSet.builder()
                        .startMazeSpaceComponent(mazeStartComp)
                        .exitMazeSpaceComponent(mazeEndComp)
                        .mazeSpaceComponents(Arrays.asList(mazeEndComp,mazeEmptyComp1,mazeEmptyComp2,mazeStartComp))
                        .build();
        mazeNavigator = new MazeNavigator(mazeComponentDataSet,2,2);
        assertTrue(mazeNavigator.moveLeft().isPresent());
        assertTrue(mazeNavigator.moveUp().isPresent());
    }
}
