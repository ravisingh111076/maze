package uk.gov.dwp.maze.gui.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.gov.dwp.maze.domain.MazeDataSet;
import uk.gov.dwp.maze.exception.InvalidMazeException;
import uk.gov.dwp.maze.gui.MazeSpaceComponent;
import uk.gov.dwp.maze.gui.MazeComponentDataSet;
import uk.gov.dwp.maze.gui.MazeUI;
import uk.gov.dwp.maze.service.MazeDataService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MazeUIComponentServiceImpl implements MazeUIComponentService {

    private final MazeDataService mazeDataService;

    public MazeUIComponentServiceImpl(MazeDataService mazeDataService) {
        this.mazeDataService = mazeDataService;
    }
    static final Logger LOGGER = LogManager.getLogger(MazeUI.class);
    @Override
    public Optional<MazeComponentDataSet> getMazeComponent() {
         MazeDataSet mazeDataSet;
        try {
            mazeDataSet = mazeDataService.getMazeData();
        } catch (InvalidMazeException invalidMazeException) {
            invalidMazeException.printStackTrace();
            LOGGER.error("Not a valid maze");
            return Optional.empty();
        }
        return Optional.ofNullable(transformMazeDataIntoUIComponent().apply(mazeDataSet));
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
