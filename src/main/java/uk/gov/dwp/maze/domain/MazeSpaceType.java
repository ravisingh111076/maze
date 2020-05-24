package uk.gov.dwp.maze.domain;

public enum MazeSpaceType {
    emptySpace {
        public char getValue() {
            return ' ';
        }
    }, WallSpace {
        public char getValue() {
            return 'X';
        }
    }, StartSpace {
        public char getValue() {
            return 'S';
        }
    }, EndSpace {
        public char getValue() {
            return 'F';
        }
    };
    public abstract char getValue();
}
