import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] move_dr = {0, -1, 0, 1, 0};
  static int[] move_dc = {0, 0, -1, 0, 1};
  static int M, S;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    Deque<Fish> fishDeque = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      fishDeque.add(new Fish(row, col, direction));
    }

    st = new StringTokenizer(br.readLine());
    int sharkRow = Integer.parseInt(st.nextToken());
    int sharkCol = Integer.parseInt(st.nextToken());
    Shark shark = new Shark(sharkRow, sharkCol);

    System.out.println(simulation(fishDeque, shark));
  }
  static int simulation(Deque<Fish> fishDeque, Shark shark) {

    Deque<Fish>[][] fishGrid = new ArrayDeque[5][5];
    Deque<Smell> smellDeque = new ArrayDeque<>();
    boolean[][] isSmell = new boolean[5][5];
    while (S-- > 0) {

      Deque<Fish> copyFishDeque = copyFish(fishDeque, fishGrid);

      fishGrid = fishMove(fishGrid, isSmell, shark);

      sharkMove(fishGrid, smellDeque, shark);

      smellDeque = updateSmellDeque(smellDeque, isSmell);

      fishDeque = gatherAllFish(copyFishDeque, fishGrid);
    }

    return fishDeque.size();
  }
  static Deque<Fish> gatherAllFish(Deque<Fish> copyFishDeque, Deque<Fish>[][] fishGrid) {

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) {
            copyFishDeque.add(fishGrid[i][j].poll());
          }
        }
      }
    }
    return copyFishDeque;
  }
  static Deque<Smell> updateSmellDeque(Deque<Smell> smellDeque, boolean[][] isSmell) {

    Deque<Smell> newSmellDeque = new ArrayDeque<>();
    boolean[][] isUpdated = new boolean[5][5];
    while (!smellDeque.isEmpty()) {
      Smell smell = smellDeque.pollLast();

      if (--smell.time > 0) {
        if (!isUpdated[smell.row][smell.col]) {
          newSmellDeque.addFirst(smell);
          isUpdated[smell.row][smell.col] = true;
          isSmell[smell.row][smell.col] = true;
        }
      }
      else if (!isUpdated[smell.row][smell.col]) isSmell[smell.row][smell.col] = false;
    }
    return newSmellDeque;
  }
  static void sharkMove(Deque<Fish>[][] fishGrid, Deque<Smell> smellDeque, Shark shark) {

    Deque<MoveShark> dq = new ArrayDeque<>();
    dq.addLast(new MoveShark(shark.row, shark.col));

    MoveShark result = null;
    while (!dq.isEmpty()) {
      MoveShark moveShark = dq.pollFirst();

      if (moveShark.time == 3) {
        if (result == null) result = moveShark;
        else {
          if (result.count < moveShark.count) result = moveShark;
          else if (result.count == moveShark.count && result.compare(moveShark)) result = moveShark;
        }
        continue;
      }

      for (int i = 1; i <= 4; i++) {
        int nextRow = moveShark.row + move_dr[i];
        int nextCol = moveShark.col + move_dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          int count = 0;
          if (!moveShark.isVisited(nextRow, nextCol) && fishGrid[nextRow][nextCol] != null) count = fishGrid[nextRow][nextCol].size();
          dq.addLast(
                  new MoveShark(nextRow, nextCol, moveShark.time + 1, moveShark.count + count, i, moveShark.directions, moveShark.points));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      Point point = result.points[i];

      if (fishGrid[point.row][point.col] != null) {
        if (!fishGrid[point.row][point.col].isEmpty()) smellDeque.addLast(new Smell(point.row, point.col));
        while (!fishGrid[point.row][point.col].isEmpty()) {
          fishGrid[point.row][point.col].pop();
        }
      }
    }

    shark.row = result.row;
    shark.col = result.col;
  }
  static Deque<Fish>[][] fishMove(Deque<Fish>[][] fishGrid, boolean[][] isSmell, Shark shark) {

    Deque<Fish>[][] newFishGrid = new Deque[5][5];

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) {
            Fish fish = fishGrid[i][j].poll();

            int originDirection = fish.direction;
            do {
              int nextRow = fish.row + dr[fish.direction];
              int nextCol = fish.col + dc[fish.direction];

              if (isValidIdx(nextRow, nextCol)
                      && !(nextRow == shark.row && nextCol == shark.col)
                      && !isSmell[nextRow][nextCol]) {
                fish.row = nextRow;
                fish.col = nextCol;
                break;
              }
              fish.direction = getNextDirection(fish.direction);
            } while (fish.direction != originDirection);

            if (newFishGrid[fish.row][fish.col] == null) newFishGrid[fish.row][fish.col] = new ArrayDeque<>();
            newFishGrid[fish.row][fish.col].add(fish);
          }
        }
      }
    }
    return newFishGrid;
  }
  static int getNextDirection(int direction) {
    return direction - 1 != 0 ? direction - 1 : 8;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
  static Deque<Fish> copyFish(Deque<Fish> fishDeque, Deque<Fish>[][] fishGrid) {

    Deque<Fish> copyFishDeque = new ArrayDeque<>();

    while (!fishDeque.isEmpty()) {
      Fish fish = fishDeque.poll();

      copyFishDeque.add(new Fish(fish.row, fish.col, fish.direction));
      if (fishGrid[fish.row][fish.col] == null) fishGrid[fish.row][fish.col] = new ArrayDeque<>();
      fishGrid[fish.row][fish.col].add(fish);
    }

    return copyFishDeque;
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class MoveShark {
  int row;
  int col;
  int time;
  int count;
  Point[] points;
  int[] directions;

  MoveShark (int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 0;
    this.points = new Point[3];
    this.directions = new int[3];
  }

  MoveShark (int row, int col, int time, int count, int direction, int[] directions, Point[] points) {
    this.row = row;
    this.col = col;
    this.time = time;
    this.count = count;
    this.directions = initDirections(directions, time, direction);
    this.points = initPoints(points, time, row, col);
  }
  Point[] initPoints (Point[] points, int time, int row, int col) {
    Point[] newPoints = new Point[3];
    for (int i = 0; i < time - 1; i++) {
      newPoints[i] = new Point(points[i].row, points[i].col);
    }
    newPoints[time - 1] = new Point(row, col);

    return newPoints;
  }

  int[] initDirections (int[] directions, int time, int direction) {
    int[] newDirections = new int[3];
    for (int i = 0; i < time - 1; i++) {
      newDirections[i] = directions[i];
    }
    newDirections[time - 1] = direction;

    return newDirections;
  }

  boolean compare(MoveShark o) {
    for (int i = 0; i < 3; i++) {
      if (this.directions[i] > o.directions[i]) return true;
      else if (this.directions[i] < o.directions[i]) return false;
    }
    return false;
  }

  boolean isVisited(int row, int col) {
    for (int i = 0; i < time; i++) {
      if (points[i].row == row && points[i].col == col) return true;
    }
    return false;
  }
}
class Smell {
  int row;
  int col;
  int time;

  Smell(int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 3;
  }
}
class Shark {
  int row;
  int col;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class Fish {
  int row;
  int col;
  int direction;

  Fish (int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}