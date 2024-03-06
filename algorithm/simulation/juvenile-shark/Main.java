import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  private static final int[] DR = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  private static final int[] DC = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  private static int result;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    Fish[][] fishGrid = new Fish[4][4];
    Point[] fishPointList = new Point[17];


    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int idx = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        fishGrid[i][j] = new Fish(idx, direction);
        fishPointList[idx] = new Point(i, j);
      }
    }

    System.out.println(simulation(fishGrid, fishPointList));
  }
  private static int simulation(Fish[][] fishGrid, Point[] fishPointList) {

    Shark shark = new Shark(0, 0, fishGrid[0][0].direction);

    miniSimulation(fishGrid, fishPointList, shark, 0);

    return result;
  }
  private static void miniSimulation(Fish[][] fishGrid, Point[] fishPointList, Shark shark, int count) {

    fishGrid[shark.row][shark.col].isDead = true;
    count += fishGrid[shark.row][shark.col].idx;

    moveFish(fishGrid, fishPointList, shark);

    int nxtSharkRow = shark.row + DR[shark.direction];
    int nxtSharkCol = shark.col + DC[shark.direction];

    while (isValidPoint(nxtSharkRow, nxtSharkCol)) {
      if (!fishGrid[nxtSharkRow][nxtSharkCol].isDead) {
        Shark nxtShark = new Shark(nxtSharkRow, nxtSharkCol, fishGrid[nxtSharkRow][nxtSharkCol].direction);
        Fish[][] nxtFishGrid = copyFishGrid(fishGrid);
        Point[] nxtFishPointList = copyFishPointList(fishPointList);

        miniSimulation(nxtFishGrid, nxtFishPointList, nxtShark, count);
      }
      nxtSharkRow += DR[shark.direction];
      nxtSharkCol += DC[shark.direction];
    }

    result = Math.max(result, count);
  }
  private static Point[] copyFishPointList(Point[] fishPointList) {

    Point[] copy = new Point[17];

    for (int i = 1; i <= 16; i++) {
      copy[i] = new Point(fishPointList[i].row, fishPointList[i].col);
    }

    return copy;
  }
  private static Fish[][] copyFishGrid(Fish[][] fishGrid) {

    Fish[][] copy = new Fish[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        copy[i][j] = new Fish(fishGrid[i][j].idx, fishGrid[i][j].direction, fishGrid[i][j].isDead);
      }
    }

    return copy;
  }
  private static void moveFish(Fish[][] fishGrid, Point[] fishPointList, Shark shark) {

    for (int i = 1; i <= 16; i++) {
      Point curFishPoint = fishPointList[i];

      Fish curFish = fishGrid[curFishPoint.row][curFishPoint.col];

      if (curFish.isDead) continue;

      int direction = curFish.direction;
      do {
        int nxtRow = curFishPoint.row + DR[direction];
        int nxtCol = curFishPoint.col + DC[direction];

        if (canMovePoint(nxtRow, nxtCol, shark)) {
          // 움직이는 위치에 있는 물고기 이동시키기
          Point targetFishPoint = fishPointList[fishGrid[nxtRow][nxtCol].idx];
          Fish targetFish = fishGrid[nxtRow][nxtCol];
          targetFishPoint.row = curFishPoint.row;
          targetFishPoint.col = curFishPoint.col;
          fishGrid[curFishPoint.row][curFishPoint.col] = targetFish;

          // 현재 물고기 이동
          curFishPoint.row = nxtRow;
          curFishPoint.col = nxtCol;
          curFish.direction = direction;
          fishGrid[nxtRow][nxtCol] = curFish;

          break;
        }

        direction = getNextDirection(direction);
      } while (curFish.direction != direction);
    }
  }
  private static int getNextDirection(int direction) {
    return direction + 1 != 9 ? direction + 1 : 1;
  }
  private static boolean canMovePoint(int row, int col, Shark shark) {
    return isValidPoint(row, col) && !(shark.row == row && shark.col == col);
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}
class Shark {
  int row;
  int col;
  int direction;

  Shark(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
class Fish {
  int idx;
  int direction;
  boolean isDead;

  Fish(int idx, int direction) {
    this.idx = idx;
    this.direction = direction;
    this.isDead = false;
  }

  Fish(int idx, int direction, boolean isDead) {
    this(idx, direction);
    this.isDead = isDead;
  }
}
class Point {
  int row;
  int col;

  Point(int row, int col) {
    this.row = row;
    this.col = col;
  }
}