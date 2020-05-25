# Maze Explorer Application.

## Play Rule

Use case 1:<br/>
In textArea displaying ```Object at (3,3) is S``` please change only coordinates
and click find button<br/>

Use case 2:<br/>
Use key left/right/up/down key to navigate.<br/>
Click on Button which says "Click me before resume maze"

## Known Issue
- By default focus will be on maze.
- Refocus on maze doesn't work properly. 
- Unit test don't cover all scenario.

## Assumption
- Maze1.txt file should exist under resource.
- filename, col, row lengths are configurable.
- look ```application.properties file```

## Stack
- Java 8
- Swing
- Spring boot
- Lombok
- Maven

## Architecture/Approach

Application has been built using java 8, spring boot, swing and lombok.<br/>
Here are some components/services below.

- MazeDataService
   creates maze data set.
  - Deals with reading txt data
  - Builds maze data set
  - Perform basic validation.
  - will throw InvalidMazeException.
 
- MazeUIComponentService<br/>
  create MazeComponentDataSet from MazeDataSet.
  - Consumes maze data set
  - transforms into smallest visual maze unit.(JLabel)
  
- MazeNavigator<br/>
  Responsible for handling navigation.
  
- MazeUI<br/>
  Builds Maze GUI, listens to all action.

### Compile and Run from command
```mvn clean install```

```java -jar target/maze-1.0-SNAPSHOT.jar```


  
 