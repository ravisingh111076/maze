package uk.gov.dwp.maze.gui;

import uk.gov.dwp.maze.gui.MazeSpaceComponent;
import uk.gov.dwp.maze.gui.MazeComponentDataSet;
import uk.gov.dwp.maze.domain.MazeSpaceType;

import java.awt.*;
import java.util.Optional;

public class MazeNavigator {
    MazeSpaceComponent currentMazeComp;
    MazeComponentDataSet mazeComponentDataSet;
    private String leftSign ="<-";
    private String rightSign = "->";
    private String generalSign = "|";
    private Integer gridCol;
    private Integer gridRow;

    public MazeNavigator(MazeComponentDataSet mazeComponentDataSet, Integer gridCol, Integer gridRow) {
        this.currentMazeComp = mazeComponentDataSet.getStartMazeSpaceComponent();
        this.mazeComponentDataSet = mazeComponentDataSet;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
    }

    public Optional<MazeSpaceComponent> moveLeft() {
        MazeSpaceComponent leftMazeSpaceComponent = mazeComponentDataSet.getMazeSpaceComponents().get(currentIndex() - 1);
        return canMove(leftMazeSpaceComponent) ?
                Optional.of(setCurrentMazeComp(leftMazeSpaceComponent, leftSign, Color.red)):Optional.empty();
    }

    public Optional<MazeSpaceComponent> moveRight() {
        MazeSpaceComponent rightMazeSpaceComponent = mazeComponentDataSet.getMazeSpaceComponents().get(currentIndex() + 1);
        return canMove(rightMazeSpaceComponent) ?
                Optional.of(setCurrentMazeComp(rightMazeSpaceComponent, rightSign, Color.red)):Optional.empty();
    }

    public Optional<MazeSpaceComponent> moveUp() {
        MazeSpaceComponent UpMaze = mazeComponentDataSet.getMazeSpaceComponents().get(currentMazeComp.getMazeSpace().getXCoordinate() +
                (currentMazeComp.getMazeSpace().getYCoordinate()-1) * gridCol);
        return canMove(UpMaze) ? Optional.of(setCurrentMazeComp(UpMaze, generalSign, Color.red)): Optional.empty();
    }

    public Optional<MazeSpaceComponent> moveDown() {
        MazeSpaceComponent unit = mazeComponentDataSet.getMazeSpaceComponents().get(currentMazeComp.getMazeSpace().getXCoordinate() +
                (currentMazeComp.getMazeSpace().getYCoordinate() + 1) * gridCol);
        return canMove(unit) ? Optional.of(setCurrentMazeComp(unit, generalSign, Color.red)): Optional.empty();
    }

    private MazeSpaceComponent setCurrentMazeComp(MazeSpaceComponent mazeSpaceComponent, String text, Color color) {
        currentMazeComp.setForeground(Color.BLACK);
        currentMazeComp = mazeSpaceComponent;
        mazeSpaceComponent.setForeground(color);
        //if reached end exit dont change
        if(mazeSpaceComponent.getMazeSpace().getBlockType().equals(MazeSpaceType.EndSpace)) {
            return mazeSpaceComponent;
        }
        mazeSpaceComponent.setText(text);
        return mazeSpaceComponent;
    }

    private int currentIndex() {
        return currentMazeComp.getMazeSpace().getXCoordinate() +
                currentMazeComp.getMazeSpace().getYCoordinate() * gridCol;
    }

    private boolean canMove(MazeSpaceComponent mazeSpaceComponent) {
        return mazeSpaceComponent.getMazeSpace().getBlockType().equals(MazeSpaceType.emptySpace) ||
                mazeSpaceComponent.getMazeSpace().getBlockType().equals(MazeSpaceType.EndSpace);
    }
}
