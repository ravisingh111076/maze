# Maze Explorer Application.

### Play Rule
Use key left/right/up/down key to navigate.

### Known Issue
Refocus doesn't work properly.Restart for navigation if needed. 

### Assumption
- Maze txt file should exist under resource.

### Stack
- Java 8
- Swing
- Spring boot
- Lombok
- Maven

## Architecture/Approach

Application has been built using java 8, spring boot, swing and lombok,
which provides some of key features such as dependency injection, builders etc out of box.
Here is some components below.

- MazeDataService
  Purpose - Creates maze data set.
  - deals with reading txt data
  - Builds maze data set
  - Perform basic validation.
  - will throw InvalidMazeException.
 
- MazeUIComponentService
  Responsible for created Swing based MazeVisualComponent.
  - consumes maze data set
  - transforms into smallest maze ui unit.
  
- MazeNavigator
  Responsible handling navigation.

### Compile and Run from command
```mvn clean install```

```java -jar target/maze-1.0-SNAPSHOT.jar```


  
 