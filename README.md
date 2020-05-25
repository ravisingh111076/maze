# Maze Explorer Application.

### Play Rule
Use case 1:
replace coordinate in the text area, dont change text, click find button
Use case 2:
Use key left/right/up/down key to navigate.

### Known Issue
-By default focus will be on maze.
-Refocus on maze doesn't work properly.
-CLick on button to navigation on maze. 
- Unit test don't cover all scenario.

### Assumption
- Maze txt file should exist under resource.
- you can change file name.
- look ```application.properties file```

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
  - Deals with reading txt data
  - Builds maze data set
  - Perform basic validation.
  - will throw InvalidMazeException.
 
- MazeUIComponentService
  Create MazeComponentDataSet from MazeDataSet.
  - Consumes maze data set
  - transforms into smallest visual maze unit.(JLabel)
  
- MazeNavigator
  Responsible handling navigation.
  
- MazeUI
  Builds Maze GUI, listens to all action.

### Compile and Run from command
```mvn clean install```

```java -jar target/maze-1.0-SNAPSHOT.jar```


  
 