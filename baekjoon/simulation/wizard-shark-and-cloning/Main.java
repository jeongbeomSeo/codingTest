import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int M, S;
  static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    Queue<Fish> fishList = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      fishList.add(new Fish(row, col, direction));
    }

    st = new StringTokenizer(br.readLine());
    int sharkRow = Integer.parseInt(st.nextToken());
    int sharkCol = Integer.parseInt(st.nextToken());
    Shark shark = new Shark(sharkRow, sharkCol);


    System.out.println(simulation(fishList, shark));
  }
  static int simulation(Queue<Fish> fishList, Shark shark) {

    Deque<Smell> smells = new ArrayDeque<>();
    boolean[][] isSmellGrid = new boolean[5][5];

    while (S-- > 0) {
      Queue<Fish>[][] fishGrid = new ArrayDeque[5][5];

      // 1단계
      Queue<Fish> copyedFish = copyFish(fishList, fishGrid);

      // 2단계
      fishGrid = activeFishMove(fishGrid, isSmellGrid, shark);

      // 3단계
      activeSharkMove(fishGrid, smells, shark);

      // 4단계
      smells = activeDiffusionSmell(smells, isSmellGrid);

      fishList = integrateFish(fishGrid, copyedFish);
    }

    return fishList.size();
  }
  static Queue<Fish> integrateFish(Queue<Fish>[][] fishGrid, Queue<Fish> copyedFish) {

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) copyedFish.add(fishGrid[i][j].poll());
        }
      }
    }

    return copyedFish;
  }
  static Deque<Smell> activeDiffusionSmell(Deque<Smell> smells, boolean[][] isSmellGrid) {

    boolean[][] isVisited = new boolean[5][5];
    Deque<Smell> newSmells = new ArrayDeque<>();
    while (!smells.isEmpty()) {
      Smell smell = smells.pollLast();

      if (!isVisited[smell.row][smell.col]) {
        if (--smell.time != 0) {
          isSmellGrid[smell.row][smell.col] = true;
          isVisited[smell.row][smell.col] = true;
          newSmells.addFirst(smell);
        }
        else isSmellGrid[smell.row][smell.col] = false;
      }
    }

    return newSmells;
  }
  static void activeSharkMove (Queue<Fish>[][] fishGrid, Deque<Smell> smells, Shark shark) {

    Queue<MovingShark> q = new ArrayDeque<>();
    q.add(new MovingShark(shark.row, shark.col));

    int[] move_dr = {-1, 0, 1, 0};
    int[] move_dc = {0, -1, 0, 1};

    MovingShark lastShark = null;

    while (!q.isEmpty()) {
      MovingShark movingShark = q.poll();

      if (movingShark.time == 3) {
        if (lastShark == null) lastShark = movingShark;
        else {
          if (movingShark.count > lastShark.count) lastShark = movingShark;
          else if (movingShark.count == lastShark.count) {
            if (lastShark.compareDirection(movingShark)) lastShark = movingShark;
          }
        }
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = movingShark.row + move_dr[i];
        int nextCol = movingShark.col + move_dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          int count = 0;
          if (!movingShark.isVisited(nextRow, nextCol) && fishGrid[nextRow][nextCol] != null)
            count = fishGrid[nextRow][nextCol].size();

          q.add(new MovingShark(nextRow, nextCol, movingShark.time + 1, movingShark.count + count, i, movingShark.direction, movingShark.points));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      int[] point = lastShark.points[i];

      if (fishGrid[point[0]][point[1]] != null) {
        while (!fishGrid[point[0]][point[1]].isEmpty()) fishGrid[point[0]][point[1]].poll();
        smells.addLast(new Smell(point[0], point[1]));
      }
    }

    shark.row = lastShark.row;
    shark.col = lastShark.col;
  }
  static Queue<Fish>[][] activeFishMove (Queue<Fish>[][] fishGrid, boolean[][] isSmellGrid, Shark shark) {

    Queue<Fish>[][] copy = new ArrayDeque[5][5];
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
                      && !(shark.row == nextRow && shark.col == nextCol)
                      && !isSmellGrid[nextRow][nextCol]) {
                fish.row = nextRow;
                fish.col = nextCol;
                break;
              }

              fish.direction = getNextDirection(fish.direction);
            } while (originDirection != fish.direction);

            if (copy[fish.row][fish.col] == null) copy[fish.row][fish.col] = new ArrayDeque<>();
            copy[fish.row][fish.col].add(fish);
          }
        }
      }
    }
    return copy;
  }
  static Queue<Fish> copyFish (Queue<Fish> fishList, Queue<Fish>[][] fishGrid) {

    Queue<Fish> copy = new ArrayDeque<>();
    while (!fishList.isEmpty()) {
      Fish fish = fishList.poll();

      copy.add(new Fish(fish.row, fish.col, fish.direction));

      if (fishGrid[fish.row][fish.col] == null) fishGrid[fish.row][fish.col] = new ArrayDeque<>();
      fishGrid[fish.row][fish.col].add(fish);
    }

    return copy;
  }
  static int getNextDirection(int direction) {
    return direction - 1 != 0 ? direction - 1 : 8;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
}
class Smell {
  int row;
  int col;
  int time;

  Smell (int row, int col) {
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

class MovingShark {
  int row;
  int col;
  int time;
  int count;
  int[] direction;
  int[][] points;

  MovingShark(int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 0;
    this.count = 0;
    this.direction = new int[3];
    this.points = new int[3][2];
  }

  MovingShark(int row, int col, int time, int count, int curDirection, int[] direction, int[][] points) {
    this.row = row;
    this.col = col;
    this.time = time;
    this.count = count;
    this.direction = updateDirection(direction, curDirection, time);
    this.points = updatePoints(points, row, col, time);
  }

  boolean isVisited(int row, int col) {

    for (int i = 0; i < 3; i++) {
      if (this.points[i][0] == row && this.points[i][1] == col) return true;
    }
    return false;
  }

  int[][] updatePoints(int[][] points, int row, int col, int time) {
    int[][] copy = new int[3][2];
    for (int i = 0; i < 3; i++) {
      copy[i][0] = points[i][0];
      copy[i][1] = points[i][1];
    }
    copy[time - 1][0] = row;
    copy[time - 1][1] = col;
    return copy;
  }

  int[] updateDirection(int[] direction, int curDirection, int time) {
    int[] copy = new int[3];

    for (int i = 0; i < 3; i++) copy[i] = direction[i];

    copy[time - 1] = curDirection;
    return copy;
  }

  boolean compareDirection(MovingShark o) {
    for (int i = 0; i < 3; i++) {
      if (this.direction[i] > o.direction[i]) return true;
      else if (this.direction[i] < o.direction[i]) return false;
    }
    return false;
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