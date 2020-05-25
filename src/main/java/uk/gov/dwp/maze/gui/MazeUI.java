package uk.gov.dwp.maze.gui;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.gov.dwp.maze.MazeNavigator;
import uk.gov.dwp.maze.config.MazeConfiguration;
import uk.gov.dwp.maze.gui.service.MazeUIComponentService;
import uk.gov.dwp.maze.domain.MazeSpace;
import uk.gov.dwp.maze.domain.MazeSpaceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;


@Component
public class MazeUI extends JFrame implements ActionListener {
    //TODO - need to improve.
    private JTextField coordinateField;
    private String displayNote = "Object at (%s,%s) is %s";
    private MazeNavigator path;
    //service builds maze display component
    private final MazeUIComponentService mazeUIComponentService;
    private MazeComponentDataSet mazeComponentDataSet;
    MazeKeyAction mazeKeyAction;
    Integer gridCol;
    Integer gridRow;
    static final Logger LOGGER = LogManager.getLogger(MazeUI.class);

    public MazeUI(MazeUIComponentService mazeUIComponentService,
                  MazeConfiguration configuration) {
        this.mazeUIComponentService = mazeUIComponentService;
        this.mazeKeyAction = new MazeKeyAction();
        this.addKeyListener(mazeKeyAction);
        this.gridCol = Integer.parseInt(configuration.getCol());
        this.gridRow = Integer.parseInt(configuration.getRow());
    }

    private JPanel buildMazePanel() {
        //Maze area
        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(gridRow, gridCol));
        mazeComponentDataSet.getMazeSpaceComponents().stream().forEachOrdered(mazeBlock -> {
                    mazePanel.add(mazeBlock);
                }
        );
        return mazePanel;
    }

    private JPanel buildUserInputPanel() {

        JPanel userPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Replace (X,Y),start(0,0)");

        coordinateField = new JTextField(format(new String[]{
                String.valueOf(mazeComponentDataSet.getStartMazeSpaceComponent().getMazeSpace().getXCoordinate()),
                String.valueOf(mazeComponentDataSet.getStartMazeSpaceComponent().getMazeSpace().getYCoordinate()),
                mazeComponentDataSet.getStartMazeSpaceComponent().getMazeSpace().getFace()}));

        userPanel.add(label, BorderLayout.BEFORE_LINE_BEGINS);
        userPanel.add(coordinateField, BorderLayout.CENTER);
        JButton findButton = new JButton("Find");
        JButton goButton = new JButton("Click me before resume maze");
        findButton.addActionListener(this);
        goButton.addActionListener(this);
        userPanel.add(findButton, BorderLayout.EAST);
        userPanel.add(goButton, BorderLayout.SOUTH);
        return userPanel;
    }

    public void display() {
        Optional<MazeComponentDataSet> optionalMazeComponentDataSet = mazeUIComponentService.getMazeComponent();
        if(!optionalMazeComponentDataSet.isPresent()) {
            LOGGER.error("Not a valid maze");
            this.dispose();
            System.exit(1);
        }
        this.mazeComponentDataSet = optionalMazeComponentDataSet.get();
        this.path = new MazeNavigator(this.mazeComponentDataSet, gridCol, gridRow);
        JPanel outerPanel = new JPanel(new BorderLayout());
        //create user input area
        outerPanel.add(buildUserInputPanel(), BorderLayout.BEFORE_FIRST_LINE);
        //create maze
        outerPanel.add(buildMazePanel());
        this.add(outerPanel);
        this.setPreferredSize(new Dimension(gridCol*40, gridRow*40));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);
        JOptionPane.showMessageDialog(this,
                "Use Keyboard up/down/left/right to navigate",
                "Maze Message",
                JOptionPane.PLAIN_MESSAGE);
    }

    private int[] extractXYFromText() {
        int result[] = new int[2];
        String xy = coordinateField.getText().
                substring(coordinateField.getText().indexOf("(") + 1,
                        coordinateField.getText().indexOf(")"));
        String[] coord = xy.split(",");
        result[0] = Integer.parseInt(coord[0]);
        result[1] = Integer.parseInt(coord[1]);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (((JButton) e.getSource()).getText().equals("Find")) {
                int[] xy = extractXYFromText();
                if(!(between(xy[0], 0,gridCol-1) &&  between(xy[1], 0,gridRow-1))) {
                    JOptionPane.showMessageDialog(this,
                            "Not valid coordinate max("+ gridCol +"," +gridRow+")",
                            "Maze Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int blockIndex = xy[0] + xy[1] * gridCol;
                MazeSpace mazeSpace = mazeComponentDataSet.getMazeSpaceComponents().get(blockIndex).getMazeSpace();
                String displayText = mazeSpace.getFace();
                if (mazeSpace.getBlockType().equals(MazeSpaceType.emptySpace))
                    displayText = "EmptySpace";
                coordinateField.setText(format(new String[]{
                        String.valueOf(mazeSpace.getXCoordinate()),
                        String.valueOf(mazeSpace.getYCoordinate()),
                        displayText}));
            } else {
                this.requestFocus();
                this.repaint();
            }
        }
    }

    private String format(String[] str) {
        return String.format(displayNote, str);
    }

    void checkAndActOnEndSpaceOrWallSpace(Optional<MazeSpaceComponent> mazeUnitComp) {
        if(mazeUnitComp.isPresent()) {
            if(mazeUnitComp.get().getMazeSpace().getBlockType().equals(MazeSpaceType.EndSpace)) {
                JOptionPane.showMessageDialog(this,
                        "Congratulation, Found exit.",
                        "Maze Message",
                        JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Move not allowed",
                    "Maze Message",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        return (i >= minValueInclusive && i <= maxValueInclusive);
    }

    class MazeKeyAction implements KeyListener {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                checkAndActOnEndSpaceOrWallSpace(path.moveUp());
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                checkAndActOnEndSpaceOrWallSpace(path.moveDown());
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                checkAndActOnEndSpaceOrWallSpace(path.moveRight());
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                checkAndActOnEndSpaceOrWallSpace(path.moveLeft());
            }
            repaint();
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }
}
