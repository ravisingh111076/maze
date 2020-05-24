package uk.gov.dwp.maze.service;

import org.springframework.stereotype.Service;
import uk.gov.dwp.maze.config.MazeConfiguration;
import uk.gov.dwp.maze.domain.MazeDataSet;
import uk.gov.dwp.maze.domain.MazeSpace;
import uk.gov.dwp.maze.exception.InvalidMazeException;
import uk.gov.dwp.maze.factory.MazeFactory;
import uk.gov.dwp.maze.domain.MazeSpaceType;
import java.util.Scanner;

@Service
public class MazeDataServiceImpl implements MazeDataService {

    MazeConfiguration mazeConfiguration;
    Integer col;
    Integer row;

    MazeDataServiceImpl(MazeConfiguration configuration) {
        this.mazeConfiguration = configuration;
        this.col= Integer.parseInt(configuration.getCol());
        this.row= Integer.parseInt(configuration.getRow());
    }

    @Override
    public MazeDataSet getMazeData() {
        return transformTextDataToMaze()
                .apply(buildInputStream()
                        .apply("/"+mazeConfiguration.getFilePath()));
    }

    TransformTextDataToMaze transformTextDataToMaze() {
        return (inputStream) -> {
            MazeDataSet maze = new MazeDataSet();
            int rowCounter = 0;
            int colCounter = 0;
            try {
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    // Read in a single character
                    MazeSpace mazeSpace = MazeFactory.buildMazeBlock(
                            scanner.findInLine(".").charAt(0), colCounter, rowCounter);
                    validateBlockAndAssignStartAndEndMazeSpace(mazeSpace,maze);
                    maze.getMazeSpaces().add(mazeSpace);
                    colCounter++;
                    if (colCounter == col) {
                        // Consume the line break
                        if (rowCounter < row - 1)
                            scanner.nextLine();
                        // Move to the next row
                        rowCounter++;
                        colCounter = 0;
                    }
                }
                if (maze.getStartMazeSpace() == null || maze.getExitMazeSpace() == null)
                    throw new InvalidMazeException("Bad Maze file, either start or end missing");
            } catch (Exception e) {
                throw new InvalidMazeException("Exception while reading from file" + e.getMessage());
            }
            return maze;
        };
    }

    BuildInputStream buildInputStream() {
        return filePath -> MazeDataServiceImpl.class.getResourceAsStream(filePath);
    }

    private void validateBlockAndAssignStartAndEndMazeSpace(MazeSpace mazeSpace, MazeDataSet maze) {
        if(mazeSpace.getBlockType().equals(MazeSpaceType.StartSpace)) {
            if(maze.getStartMazeSpace() !=null) throw new InvalidMazeException("multiple start found, bad file");
            else maze.setStartMazeSpace(mazeSpace);
        } else if(mazeSpace.getBlockType().equals(MazeSpaceType.EndSpace)) {
            if(maze.getExitMazeSpace()!=null) throw new InvalidMazeException("multiple end found, bad file");
            else maze.setExitMazeSpace(mazeSpace);
        }
    }
}
