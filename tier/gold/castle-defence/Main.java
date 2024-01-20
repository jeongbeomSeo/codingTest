import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static int ENEMY_COUNT = 0;
  private static int MAX;
  private static final int[] DR = {-1, 0, 1, 0};
  private static final int[] DC = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());

        if (grid[i][j] == 1) ENEMY_COUNT++;
      }
    }

    System.out.println(simulationWithDFS(grid, N, M, D));
  }
  private static int simulationWithDFS(int[][] grid, int N, int M, int D) {

    MAX = Integer.MIN_VALUE;
    dfs(grid, new int[3], 0, M, 0, N, D);

    return MAX;
  }
  private static void dfs(int[][] grid, int[] buffer, int ptr, int colLen, int size, int rowLen, int arrowRange) {

    // 성능 개선 1 => 648ms -> 396ms
    if (MAX == ENEMY_COUNT || MAX == (3 * rowLen)) return;

    if (size == 3) {
      int[][] copy = copyIntGrid(grid, rowLen, colLen);
      MAX = Math.max(MAX, simulation(copy, buffer, rowLen, colLen, arrowRange));
    } else if (ptr < colLen) {
      buffer[size] = ptr;
      dfs(grid, buffer, ptr + 1, colLen, size + 1, rowLen, arrowRange);
      buffer[size] = 0;

      dfs(grid, buffer, ptr + 1, colLen, size, rowLen, arrowRange);
    }
  }
  private static int simulation(int[][] grid, int[] buffer, int rowLen, int colLen, int arrowRange) {

    int result = 0;
    while (isContinue(grid, rowLen, colLen)) {
      Queue<Point> delEnemyPointBuffer = new ArrayDeque<>();
      for (int i = 0; i < 3; i++) {
        int arrowColPos = buffer[i];

        Point delEnemyPoint = findEnemy(grid, rowLen, arrowColPos, rowLen, colLen, arrowRange);
        if (delEnemyPoint != null) delEnemyPointBuffer.add(delEnemyPoint);
      }

      result += killEnemyCount(grid, delEnemyPointBuffer);

      // 성능 개선 2 => 396ms -> 332ms
      rowLen--;
      //grid = getNextStepGrid(grid, rowLen, colLen);
    }
    return result;
  }
  private static int killEnemyCount(int[][] grid, Queue<Point> delEnemyPointBuffer) {

    int killCount = 0;
    while (!delEnemyPointBuffer.isEmpty()) {
      Point delEnemyPoint = delEnemyPointBuffer.poll();

      if (grid[delEnemyPoint.row][delEnemyPoint.col] == 1) {
        killCount++;
      }

      grid[delEnemyPoint.row][delEnemyPoint.col] = 0;
    }

    return killCount;
  }
  private static Point findEnemy(int[][] grid, int arrowRowPos, int arrowColPos, int rowLen, int colLen, int arrowRange) {

    boolean[][] isVisited = new boolean[rowLen][colLen];
    Queue<Status> buffer = new ArrayDeque<>();
    buffer.add(new Status(arrowRowPos - 1, arrowColPos, 1));
    isVisited[arrowRowPos - 1][arrowColPos] = true;

    Queue<RegistryInfo> pq = new PriorityQueue<>(new RegistryInfoCompartor());
    while (!buffer.isEmpty()) {
      Status curStatus = buffer.poll();

      if (grid[curStatus.point.row][curStatus.point.col] == 1) {
        pq.add(new RegistryInfo(curStatus. point, calcDistance(arrowRowPos, arrowColPos, curStatus.point.row, curStatus.point.col)));
      }
      if (curStatus.count == arrowRange) continue;

      for (int i = 0; i < 4; i++) {
        int nxtRow = curStatus.point.row + DR[i];
        int nxtCol = curStatus.point.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol, rowLen, colLen) && !isVisited[nxtRow][nxtCol]) {
          buffer.add(new Status(nxtRow, nxtCol, curStatus.count + 1));
          isVisited[nxtRow][nxtCol] = true;
        }
      }
    }

    if (pq.isEmpty()) return null;

    return pq.poll().point;
  }
  private static boolean isValidPoint(int row, int col, int rowLen, int colLen) {
    return row >= 0 && col >= 0 && row < rowLen && col < colLen;
  }
  private static int calcDistance(int arrowRowPos, int arrowColPos, int row, int col) {
    return Math.abs(arrowRowPos - row) + Math.abs(arrowColPos - col);
  }
  private static int[][] getNextStepGrid(int[][] grid, int rowLen, int colLen) {

    int[][] nextStepGrid = new int[rowLen][colLen];

    for (int i = 0; i < rowLen - 1; i++) {
      for (int j = 0; j < colLen; j++) {
        nextStepGrid[i + 1][j] = grid[i][j];
      }
    }

    return nextStepGrid;
  }
  private static boolean isContinue(int[][] grid, int rowLen, int colLen) {

    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        if (grid[i][j] == 1) return true;
      }
    }

    return false;
  }
  private static int[][] copyIntGrid(int[][] grid, int rowLen, int colLen) {
    int[][] copy = new int[rowLen][colLen];

    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        copy[i][j] = grid[i][j];
      }
    }

    return copy;
  }
}
class RegistryInfoCompartor implements Comparator<RegistryInfo> {
  @Override
  public int compare(RegistryInfo o1, RegistryInfo o2) {
    if (o1.distance != o2.distance) return o1.distance - o2.distance;
    else {
      return o1.point.col - o2.point.col;
    }
  }
}
class RegistryInfo {
  Point point;
  int distance;

  RegistryInfo(Point point, int distance) {
    this.point = point;
    this.distance = distance;
  }
}
class Status {
  Point point;
  int count;

  Status(int row, int col, int count) {
    this.point = new Point(row, col);
    this.count = count;
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