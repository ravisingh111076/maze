package uk.gov.dwp.maze.gui.service;

import org.springframework.stereotype.Component;
import uk.gov.dwp.maze.gui.MazeSpaceComponent;
import uk.gov.dwp.maze.gui.MazeComponentDataSet;
import uk.gov.dwp.maze.service.MazeDataService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MazeUIComponentServiceImpl implements MazeUIComponentService {

    private final MazeDataService mazeDataService;

    public MazeUIComponentServiceImpl(MazeDataService mazeDataService) {
        this.mazeDataService = mazeDataService;
    }

    @Override
    public MazeComponentDataSet getMazeComponent() {
        return transformMazeDataIntoUIComponent().apply(mazeDataService.getMazeData());
    }

    TransformMazeDataIntoUIComponent transformMazeDataIntoUIComponent() {
        return (maze->{
            List<MazeSpaceComponent> blocks = new ArrayList<>();
            maze.getMazeSpaces().stream().forEachOrdered(mazeBlock -> {
                blocks.add(MazeSpaceComponent.builder().mazeSpace(mazeBlock).color(Color.BLACK).build());
            });
            return MazeComponentDataSet.builder()
                    .startMazeSpaceComponent(MazeSpaceComponent.builder().mazeSpace(maze.getStartMazeSpace())
                            .color(Color.red).build())
                    .exitMazeSpaceComponent(MazeSpaceComponent.builder().mazeSpace(maze.getExitMazeSpace())
                            .color(Color.blue).build())
                    .mazeSpaceComponents(blocks).build();
        });
    }
}
