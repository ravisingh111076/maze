package uk.gov.dwp.maze.gui;

import lombok.Builder;
import uk.gov.dwp.maze.domain.MazeSpace;

import javax.swing.*;
import java.awt.*;

public class MazeSpaceComponent extends JLabel {

    final MazeSpace mazeSpace;
    private Color color;

    @Override
    public void setForeground(Color color) {
        this.color = color;
        super.setForeground(color);
    }

    public MazeSpace getMazeSpace() {
        return mazeSpace;
    }

    @Builder
    public MazeSpaceComponent(MazeSpace mazeSpace, Color color) {
        super(mazeSpace.getFace());
        super.setForeground(color);
        this.mazeSpace = mazeSpace;
    }
}
