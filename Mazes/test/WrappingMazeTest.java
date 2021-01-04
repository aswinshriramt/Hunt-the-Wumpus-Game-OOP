import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import maze.Maze;
import maze.WrappingMaze;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the WrappingMaze class. Here any functionality
 * related to the WrappingMaze class both perfect and non perfect are tested.
 */
public class WrappingMazeTest {

  Maze mazePerfect;
  Maze mazeImPerfect;

  /**
   * This is where the objects of the WrappingMaze class that needs to be tested is instantiated.
   */
  @Before
  public void setUp() throws Exception {
    mazePerfect = new WrappingMaze(3, 3);
    mazeImPerfect = new WrappingMaze(3, 3, 2);
  }

  /** This is a JUnit test to see if the Generate method is working correctly for a Perfect Maze. */
  @Test
  public void testGenerate() {
    mazePerfect.generate();
    assertEquals(
        "(0, 0): { Characters: None; Treasures: None }\n"
            + "(0, 1): { Characters: None; Treasures: None }\n"
            + "(0, 2): { Characters: None; Treasures: None }\n"
            + "(1, 0): { Characters: None; Treasures: None }\n"
            + "(1, 1): { Characters: None; Treasures: None }\n"
            + "(1, 2): { Characters: None; Treasures: None }\n"
            + "(2, 0): { Characters: None; Treasures: None }\n"
            + "(2, 1): { Characters: None; Treasures: None }\n"
            + "(2, 2): { Characters: None; Treasures: None }\n",
        mazePerfect.toString());
  }

  /**
   * This is a JUnit test to see if the GenerateMazeObjects method is working correctly for a
   * Perfect Maze.
   */
  @Test
  public void testGenerateMazeObjects() {
    mazePerfect.generate();
    mazePerfect.setStartLoc(0, 0);
    mazePerfect.setGoalLoc(2, 2);
    mazePerfect.generateMazeObjects();
    assertEquals(
        "(0, 0): { Characters: Player (Current Gold = 0); Treasures: None }",
        mazePerfect.getCurrentRoom().toString());
  }

  //  @Test
  //  public void testMovePlayer() {
  //    mazePerfect.generate();
  //    mazePerfect.setStartLoc(0, 0);
  //    mazePerfect.setGoalLoc(2, 2);
  //    mazePerfect.generateMazeObjects();
  //    mazePerfect.movePlayer("West");
  //    assertEquals(
  //        "(0, 1): { Characters: Player (Current Gold = 0); Treasures: None }",
  //        mazePerfect.getCurrentRoom().toString());
  //  }

  /**
   * This is a JUnit test to see if the GetLegalActions method is working correctly for a Perfect
   * Maze.
   */
  @Test
  public void testGetLegalActions() {
    mazePerfect.generate();
    mazePerfect.setStartLoc(0, 0);
    mazePerfect.setGoalLoc(2, 2);
    mazePerfect.generateMazeObjects();
    HashSet<String> actions = new HashSet<String>();
    actions.add("East");
    actions.add("South");
    assertEquals(actions, mazePerfect.getCurrentRoom().getLegalActions());
  }

  /** This is a JUnit test to see if the isGoal method is working correctly for a Perfect Maze. */
  @Test
  public void testIsGoal() {
    mazePerfect.generate();
    mazePerfect.setStartLoc(0, 0);
    mazePerfect.setGoalLoc(2, 2);
    mazePerfect.generateMazeObjects();
    mazePerfect.movePlayer("South");
    assertEquals(false, mazePerfect.isGoal());
  }

  /**
   * This is a JUnit test to see if the getCurrentRoom method is working correctly for a Perfect
   * Maze.
   */
  @Test
  public void testGetCurrentRoom() {
    mazePerfect.generate();
    mazePerfect.setStartLoc(0, 0);
    mazePerfect.setGoalLoc(2, 2);
    mazePerfect.generateMazeObjects();
    assertEquals(
        "(0, 0): { Characters: Player (Current Gold = 0); Treasures: None }",
        mazePerfect.getCurrentRoom().toString());
  }

  /**
   * This is a JUnit test to see if the Generate method is working correctly for a Non Perfect Maze.
   */
  @Test
  public void testGenerateNon() {
    mazeImPerfect.generate();
    assertEquals(
        "(0, 0): { Characters: None; Treasures: None }\n"
            + "(0, 1): { Characters: None; Treasures: None }\n"
            + "(0, 2): { Characters: None; Treasures: None }\n"
            + "(1, 0): { Characters: None; Treasures: None }\n"
            + "(1, 1): { Characters: None; Treasures: None }\n"
            + "(1, 2): { Characters: None; Treasures: None }\n"
            + "(2, 0): { Characters: None; Treasures: None }\n"
            + "(2, 1): { Characters: None; Treasures: None }\n"
            + "(2, 2): { Characters: None; Treasures: None }\n",
        mazeImPerfect.toString());
  }

  /**
   * This is a JUnit test to see if the GenerateMazeObjects method is working correctly for a Non
   * Perfect Maze.
   */
  @Test
  public void testGenerateMazeObjectsNon() {
    mazeImPerfect.generate();
    mazeImPerfect.setStartLoc(0, 0);
    mazeImPerfect.setGoalLoc(2, 2);
    mazeImPerfect.generateMazeObjects();
    assertEquals(
        "(0, 0): { Characters: Player (Current Gold = 0); Treasures: None }",
        mazeImPerfect.getCurrentRoom().toString());
  }

  //  @Test
  //  public void testMovePlayerNon() {
  //    mazeImPerfect.generate();
  //    mazeImPerfect.setStartLoc(0, 0);
  //    mazeImPerfect.setGoalLoc(2, 2);
  //    mazeImPerfect.generateMazeObjects();
  //    mazeImPerfect.movePlayer("South");
  //    assertEquals(
  //        "(0, 1): { Characters: Player (Current Gold = 0); Treasures: None }",
  //        mazeImPerfect.getCurrentRoom().toString());
  //  }

  /**
   * This is a JUnit test to see if the GetLegalActions method is working correctly for a Non
   * Perfect Maze.
   */
  @Test
  public void testGetLegalActionsNon() {
    mazeImPerfect.generate();
    mazeImPerfect.setStartLoc(0, 0);
    mazeImPerfect.setGoalLoc(2, 2);
    mazeImPerfect.generateMazeObjects();
    HashSet<String> actions = new HashSet<String>();
    actions.add("North");
    actions.add("South");
    actions.add("East");
    actions.add("West");
    assertEquals(actions, mazeImPerfect.getCurrentRoom().getLegalActions());
  }

  /**
   * This is a JUnit test to see if the isGoal method is working correctly for a Non Perfect Maze.
   */
  @Test
  public void testIsGoalNon() {
    mazeImPerfect.generate();
    mazeImPerfect.setStartLoc(0, 0);
    mazeImPerfect.setGoalLoc(2, 2);
    mazeImPerfect.generateMazeObjects();
    mazeImPerfect.movePlayer("South");
    assertEquals(false, mazeImPerfect.isGoal());
  }

  /**
   * This is a JUnit test to see if the GetCurrent method is working correctly for a Non Perfect
   * Maze.
   */
  @Test
  public void testGetCurrentRoomNon() {
    mazeImPerfect.generate();
    mazeImPerfect.setStartLoc(0, 0);
    mazeImPerfect.setGoalLoc(2, 2);
    mazeImPerfect.generateMazeObjects();
    assertEquals(
        "(0, 0): { Characters: Player (Current Gold = 0); Treasures: None }",
        mazeImPerfect.getCurrentRoom().toString());
  }
}
