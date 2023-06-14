import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
  static int M, S;
  static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    Deque<Fish> fishList = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      fishList.addLast(new Fish(row, col, direction));
    }

    st = new StringTokenizer(br.readLine());
    int sharkRow = Integer.parseInt(st.nextToken());
    int sharkCol = Integer.parseInt(st.nextToken());
    Shark shark = new Shark(sharkRow, sharkCol);

    System.out.println(simulation(fishList, shark));
  }
  static int simulation(Deque<Fish> fishList, Shark shark) {

    Deque<Smell> smellList = new ArrayDeque<>();
    boolean[][] isSmellGrid = new boolean[5][5];
    while (S-- > 0) {

      Deque<Fish>[][] fishGrid = new Deque[5][5];
      Deque<Fish> copyedFish = fishCopy(fishList, fishGrid);

      fishGrid = activeMoveFish(fishGrid, isSmellGrid, shark);

      activeMoveShark(fishGrid, smellList, shark);

      smellList = activeSmell(smellList, isSmellGrid);

      fishList = integrateFish(copyedFish, fishGrid);
    }

    return countAllFish(fishList);
  }
  static int countAllFish(Deque<Fish> fishList) {
    return fishList.size();
  }
  static Deque<Fish> integrateFish(Deque<Fish> copyedFish, Deque<Fish>[][] fishGrid) {

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) copyedFish.add(fishGrid[i][j].poll());
        }
      }
    }

    return copyedFish;
  }
  static Deque<Smell> activeSmell(Deque<Smell> smellList, boolean[][] isSmellGrid) {

    Deque<Smell> newSmellList = new ArrayDeque<>();
    boolean[][] isSmell = new boolean[5][5];
    while (!smellList.isEmpty()) {
      Smell smell = smellList.pollLast();

      if(--smell.time != 0 && !isSmell[smell.row][smell.col]) {
        isSmellGrid[smell.row][smell.col] = true;
        newSmellList.addFirst(smell);
        isSmell[smell.row][smell.col] = true;
      }
      else if (!isSmell[smell.row][smell.col]) isSmellGrid[smell.row][smell.col] = false;
    }
    return newSmellList;
  }
  static void activeMoveShark(Deque<Fish>[][] fishGrid, Deque<Smell> smellList, Shark shark) {

    Queue<Shark> q = new ArrayDeque<>();
    int[] move_dr = {0, -1, 0, 1, 0};
    int[] move_dc = {0, 0, -1, 0, 1};
    q.add(shark);

    Shark maxShark = null;
    while (!q.isEmpty()) {
      Shark curShark = q.poll();

      if (curShark.time == 3) {
        if (maxShark == null) maxShark = curShark;
        else {
          if (maxShark.count < curShark.count) maxShark = curShark;
          else if (maxShark.count == curShark.count) {
            for (int c = 0; c < 3; c++) {
              if (maxShark.directionList[c] == curShark.directionList[c]) continue;
              else if (maxShark.directionList[c] > curShark.directionList[c]) {maxShark = curShark; break;}
              else break;
            }
          }
        }
        continue;
      }

      for (int i = 1; i <= 4; i++) {
        int nextRow = curShark.row + move_dr[i];
        int nextCol = curShark.col + move_dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          int count = 0;
          if (!curShark.isVisited[nextRow][nextCol] && fishGrid[nextRow][nextCol] != null) count = fishGrid[nextRow][nextCol].size();
          q.add(new Shark(nextRow, nextCol, curShark.count + count, curShark.time + 1, curShark.directionList, curShark.isVisited, i, curShark.pointList));
        }
      }
    }

    for (int i = 0; i < 3; i++) {
      Point point = maxShark.pointList[i];
      if (fishGrid[point.row][point.col] != null) {
        if (!fishGrid[point.row][point.col].isEmpty()) smellList.addLast(new Smell(point.row, point.col));
        while (!fishGrid[point.row][point.col].isEmpty()) fishGrid[point.row][point.col].poll();
      }
    }

    shark.isVisited[shark.row][shark.col] = false;
    shark.row = maxShark.row;
    shark.col = maxShark.col;
    shark.isVisited[shark.row][shark.col] = true;

  }
  static Deque<Fish>[][] activeMoveFish(Deque<Fish>[][] fishGrid, boolean[][] isSmellGrid, Shark shark) {


    Deque<Fish>[][] nextFishGrid = new ArrayDeque[5][5];

    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        // 물고기가 있는 칸인 경우
        if (fishGrid[i][j] != null) {
          while (!fishGrid[i][j].isEmpty()) {
            Fish fish = fishGrid[i][j].poll();

            int originDirection = fish.direction;

            do {
              int nextRow = fish.row + dr[fish.direction];
              int nextCol = fish.col + dc[fish.direction];

              if (isValidIdx(nextRow, nextCol) && !(nextRow == shark.row && nextCol == shark.col) && !isSmellGrid[nextRow][nextCol]) {
                fish.row = nextRow;
                fish.col = nextCol;
                break;
              }

              fish.direction = getNextDirection(fish.direction);
            } while (fish.direction != originDirection);

            if (nextFishGrid[fish.row][fish.col] == null) nextFishGrid[fish.row][fish.col] = new ArrayDeque<>();
            nextFishGrid[fish.row][fish.col].add(fish);
          }
        }
      }
    }

    return nextFishGrid;
  }
  static Deque<Fish> fishCopy (Deque<Fish> fishList, Deque<Fish>[][] fishGrid) {

    Deque<Fish> copyedFish = new ArrayDeque<>();
    while (!fishList.isEmpty()) {
      Fish fish = fishList.poll();

      copyedFish.addLast(new Fish(fish.row, fish.col, fish.direction));

      if (fishGrid[fish.row][fish.col] == null) fishGrid[fish.row][fish.col] = new ArrayDeque<>();
      fishGrid[fish.row][fish.col].add(fish);
    }
    return copyedFish;
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

  Smell(int row, int col) {
    this.row = row;
    this.col = col;
    this.time = 3;
  }
}
class Shark {
  int row;
  int col;
  int count;
  int time;
  int[] directionList;
  boolean[][] isVisited;
  Point[] pointList;

  Shark (int row, int col) {
    this.row = row;
    this.col = col;
    this.count = 0;
    this.time = 0;
    this.directionList = new int[3];
    isVisited = new boolean[5][5];
    isVisited[row][col] = true;
    pointList = new Point[3];
  }

  Shark (int row, int col, int count, int time, int[] directionList, boolean[][] isVisited, int direction, Point[] pointList) {
    this.row = row;
    this.col = col;
    this.count = count;
    this.time = time;
    this.directionList = Arrays.copyOf(directionList, 3);
    this.directionList[time - 1] = direction;
    this.isVisited = copyIsVisited(isVisited);
    this.isVisited[row][col] = true;
    this.pointList = copyPointList(pointList);
    this.pointList[time - 1] = new Point(row, col);
  }

  Point[] copyPointList(Point[] pointList) {
    Point[] newPointList = new Point[3];

    for (int i = 0; i < 3; i++) {
      if (pointList[i] != null) {
        newPointList[i] = new Point(pointList[i].row, pointList[i].col);
      }
      else newPointList[i] = null;
    }
    return newPointList;
  }

  boolean[][] copyIsVisited(boolean[][] isVisited) {
    boolean[][] newCopyIsVisited = new boolean[5][5];
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        newCopyIsVisited[i][j] = isVisited[i][j];
      }
    }
    return newCopyIsVisited;
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
class Fish {
  int row;
  int col;
  int direction;

  Fish(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}