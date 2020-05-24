package uk.gov.dwp.maze.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class MazeConfiguration {
    @Setter
    @Getter
    String filePath;
    @Getter
    @Setter
    String col;
    @Getter
    @Setter
    String row;
}