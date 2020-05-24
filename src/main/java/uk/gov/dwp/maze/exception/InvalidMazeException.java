package uk.gov.dwp.maze.exception;

public class InvalidMazeException extends RuntimeException {
    public InvalidMazeException(String errorMessage) {
        super(errorMessage);
    }
}
