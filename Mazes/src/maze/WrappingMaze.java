package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This child class contains all the operations that the Wrapping Maze should support. This class
 * consists the functionality of two sub types of mazes: Perfect and Non-Perfect Maze.
 */
public class WrappingMaze extends AbstractMaze {

  /**
   * This constructor is used to instantiate a Perfect Wrapping Maze object that will be used in the
   * game.
   *
   * @param numRows the number of rows in the maze
   * @param numColumns the number of columns in the maze
   */
  public WrappingMaze(int numRows, int numColumns) {
    super(numRows, numColumns, 0);
    for (int x = 0; x < numRows; x++) {
      for (int y = 0; y < numColumns; y++) {
        this.mazeRooms[x][y] = new Room("(" + x + ", " + y + ")", x, y);
      }
    }
    this.startRoom = mazeRooms[0][0];
    this.goalRoom = mazeRooms[numRows - 1][numColumns - 1];
    this.currentRoom = this.startRoom;
  }

  /**
   * This constructor is used to instantiate a Wrapping Non-Perfect Maze object that will be used in
   * the game.
   *
   * @param numRows the number of rows in the maze
   * @param numColumns the number of columns in the maze
   * @param numRemainingWalls the number of remaining walls in the game
   */
  public WrappingMaze(int numRows, int numColumns, int numRemainingWalls) {
    super(numRows, numColumns, numRemainingWalls);
    for (int x = 0; x < numRows; x++) {
      for (int y = 0; y < numColumns; y++) {
        this.mazeRooms[x][y] = new Room("(" + x + ", " + y + ")", x, y);
      }
    }
    this.startRoom = mazeRooms[0][0];
    this.goalRoom = mazeRooms[numRows - 1][numColumns - 1];
    this.currentRoom = this.startRoom;
  }

  /**
   * This method is used to generate a Wrapping maze based on the specifications given.
   *
   * @return the maze in a graph like data structure, with key as a room and its value as its
   *     neighbors
   */
  @Override
  public HashMap<Room, Set<Room>> generate() {
    for (int x = 0; x < this.numRows; x++) {
      for (int y = 0; y < this.numColumns; y++) {
        if (x - 1 >= 0) {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[x - 1][y]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        } else {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[this.numRows - 1][y]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        }
        if (y - 1 >= 0) {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[x][y - 1]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        } else {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[x][this.numColumns - 1]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        }
        if (x + 1 <= this.numRows - 1) {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[x + 1][y]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        } else {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[0][y]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        }
        if (y + 1 <= this.numColumns - 1) {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[x][y + 1]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        } else {
          Set<Room> tempSet = new HashSet<>();
          tempSet.add(this.mazeRooms[x][y]);
          tempSet.add(this.mazeRooms[x][0]);
          if (!this.wallList.contains(tempSet)) {
            this.wallList.add(tempSet);
          }
        }
        Set<Room> disjointTemp = new HashSet<>();
        disjointTemp.add(mazeRooms[x][y]);

        this.disjointSet.add(disjointTemp);
      }
    }

    System.out.println("\nTotal Number of walls present: " + this.wallList.size() + "\n");

    kruskal(this.wallList.size());

    // This below block of code removes some random walls to make the maze Non-Perfect.
    if (numRemainingWalls != 0) {
      int numOfWalls = this.savedList.size();
      List<Set<Room>> listWalls = new ArrayList<>(this.savedList);
      Collections.shuffle(listWalls, new Random(7));
      for (int w = 0; w < numOfWalls - numRemainingWalls; w++) {
        listWalls.remove(0);
      }

      this.savedList = new HashSet<>(listWalls);
    }

    System.out.println("\nTotal Number of walls remaining: " + this.savedList.size() + "\n");

    for (int x = 0; x < this.numRows; x++) {
      for (int y = 0; y < this.numColumns; y++) {
        Set<Room> tempSet = new HashSet<>();
        HashMap<String, Room> neighbors = new HashMap<>();

        if (x - 1 >= 0) {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[x - 1][y]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[x - 1][y]);
            neighbors.put("West", this.mazeRooms[x - 1][y]);
          }
        } else {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[this.numRows - 1][y]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[this.numRows - 1][y]);
            neighbors.put("West", this.mazeRooms[this.numRows - 1][y]);
          }
        }
        if (y - 1 >= 0) {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[x][y - 1]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[x][y - 1]);
            neighbors.put("North", this.mazeRooms[x][y - 1]);
          }
        } else {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[x][this.numColumns - 1]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[x][this.numColumns - 1]);
            neighbors.put("North", this.mazeRooms[x][this.numColumns - 1]);
          }
        }
        if (x + 1 <= this.numRows - 1) {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[x + 1][y]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[x + 1][y]);
            neighbors.put("East", this.mazeRooms[x + 1][y]);
          }
        } else {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[0][y]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[0][y]);
            neighbors.put("East", this.mazeRooms[0][y]);
          }
        }
        if (y + 1 <= this.numColumns - 1) {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[x][y + 1]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[x][y + 1]);
            neighbors.put("South", this.mazeRooms[x][y + 1]);
          }
        } else {
          Set<Room> tempWall = new HashSet<>();
          tempWall.add(this.mazeRooms[x][y]);
          tempWall.add(this.mazeRooms[x][0]);
          if (!this.savedList.contains(tempWall)) {
            tempSet.add(this.mazeRooms[x][0]);
            neighbors.put("South", this.mazeRooms[x][0]);
          }
        }
        this.mazeRooms[x][y].setNeighbors(neighbors);
        this.mazeRooms[x][y].setLegalActions(neighbors.keySet());
        this.graphMaze.put(this.mazeRooms[x][y], tempSet);
      }
    }

    return this.graphMaze;
  }
}
