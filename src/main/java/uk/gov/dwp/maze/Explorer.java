package uk.gov.dwp.maze;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context
        .ApplicationContext;
import uk.gov.dwp.maze.gui.MazeUI;

import java.awt.*;

@SpringBootApplication
public class Explorer {
    public static void main(String [] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(Explorer.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            MazeUI mazeUI = ctx.getBean(MazeUI.class);
            mazeUI.display();
        });
    }
}
