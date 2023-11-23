import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  private static int[] dr = {0, -1, 1, 0, 0};
  private static int[] dc = {0, 0, 0, 1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M + 1];
    int[][] sharkGrid = new int[R + 1][C + 1];

    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());

      sharks[i] = new Shark(i, row, col, speed, direction, size, R, C);
      sharkGrid[row][col] = i;
    }

    System.out.println(simulation(sharks, sharkGrid, R, C, M));
  }
  private static long simulation(Shark[] sharks, int[][] sharkGrid, int R, int C, int M) {

    King king = new King();

    while (king.col != C) {

      king.move();

      int sharkIdx = getLiveSharkIdxWhenSpecCol(sharks, sharkGrid, king.col, R);
      if (sharkIdx != 0) king.count += deadShark(sharks, sharkGrid, sharkIdx);

      activeSharks(sharks, sharkGrid, R, C, M);
    }

    return king.count;
  }
  private static void activeSharks(Shark[] sharks, int[][] sharkGrid, int R, int C, int M) {

    int[][] sharkBuffer = new int[R + 1][C + 1];
    for (int i = 1; i <= M; i++) {
      if (sharks[i].isDead) continue;

      Shark shark = sharks[i];

      int[] nextPoint = getNextPoint(shark.row, shark.col, shark.direction, shark.speed, R, C);

      if (isSharkInPoint(nextPoint[0], nextPoint[1], sharkBuffer)) {
        Shark pointShark = sharks[sharkBuffer[nextPoint[0]][nextPoint[1]]];
        if (compareSize(shark, pointShark) > 0) {
          pointShark.isDead = true;
          sharkBuffer[nextPoint[0]][nextPoint[1]] = 0;
          moveShark(shark, sharkBuffer, nextPoint);
        } else {
          shark.isDead = true;
        }
      } else {
        moveShark(shark, sharkBuffer, nextPoint);
      }
    }
    gridCopy(sharkGrid, sharkBuffer, R, C);
  }
  private static void gridCopy(int[][] origin, int[][] copy, int R, int C) {

    for (int i = 0; i <= R; i++) {
      for (int j = 0; j <= C; j++) {
        origin[i][j] = copy[i][j];
      }
    }
  }
  private static void moveShark(Shark shark, int[][] sharkBuffer, int[] nextPoint) {

    shark.row = nextPoint[0];
    shark.col = nextPoint[1];
    shark.direction = nextPoint[2];

    sharkBuffer[shark.row][shark.col] = shark.idx;
  }
  private static boolean isSharkInPoint(int row, int col, int[][] sharkBuffer) {
    return sharkBuffer[row][col] != 0;
  }
  private static int compareSize(Shark shark, Shark compareShark) {
    return shark.size - compareShark.size;
  }
  private static int deadShark(Shark[] sharks, int[][] sharkGrid, int idx) {

    Shark deadShark = sharks[idx];
    deadShark.isDead = true;
    sharkGrid[deadShark.row][deadShark.col] = 0;

    return deadShark.size;
  }
  private static int getLiveSharkIdxWhenSpecCol(Shark[] sharks, int[][] sharkGrid, int col, int R) {

    for (int i = 1; i <= R; i++) {
      if (sharkGrid[i][col] != 0 && !sharks[sharkGrid[i][col]].isDead) {
        return sharkGrid[i][col];
      }
    }

    return 0;
  }
  private static int[] getNextPoint(int row, int col, int direction, int speed, int R, int C) {

    while (speed-- != 0) {
      int nextRow = row + dr[direction];
      int nextCol = col + dc[direction];
      if (!isValidPoint(nextRow, nextCol, R, C)) {
        direction = reverseDirection(direction);
        nextRow = row + dr[direction];
        nextCol = col + dc[direction];
      }

      row = nextRow;
      col = nextCol;
    }

    return new int[]{row, col, direction};
  }
  private static int reverseDirection(int direction) {
    if (direction == 1) return 2;
    if (direction == 2) return 1;
    if (direction == 3) return 4;
    return 3;
  }
  private static boolean isValidPoint(int row, int col, int R, int C) {
    return row >= 1 && col >= 1 && row <= R && col <= C;
  }
}
class King {
  int col;
  long count;

  King () {
    this.col = 0;
    this.count = 0;
  }

  public void move() {
    this.col += 1;
  }
}
class Shark {
  int idx;
  int row;
  int col;
  int speed;
  int direction;
  int size;
  boolean isDead;

  Shark (int idx, int row, int col, int speed, int direction, int size, int R, int C) {
    this.idx = idx;
    this.row = row;
    this.col = col;
    this.speed = moduleateSpeed(speed, direction, R, C);
    this.direction = direction;
    this.size = size;
    this.isDead = false;
  }

  private static int moduleateSpeed(int speed, int direction, int R, int C) {
    if (direction == 1 || direction == 2) {
      return speed % (2 * (R - 1));
    }

    return speed % (2 * (C - 1));
  }
}