import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, Q, size;
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  static int[] nextDr = {1, 0, -1, 0};
  static int[] nextDc = {0, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());

    size = (int)Math.pow(2, N);
    int[][] grid = new int[size][size];

    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[] levels = new int[Q];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < Q; i++) {
      levels[i] = Integer.parseInt(st.nextToken());
    }

    simulation(grid, levels);
  }
  static void simulation(int[][] grid, int[] levels) {

    int time = 0;
    while(time < Q) {
      int level = levels[time];

      grid = allRotate(grid, level);

      activeFireStorm(grid);
      time++;
    }

    int totalAmount = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        totalAmount += grid[i][j];
      }
    }
    System.out.println(totalAmount);

    boolean[][] isVisited = new boolean[size][size];
    int maxSize = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[i][j] != 0 && !isVisited[i][j]) {
          maxSize = Math.max(maxSize, bfs(grid, isVisited, i, j));
        }
      }
    }

    System.out.println(maxSize);
  }
  static int bfs(int[][] grid, boolean[][] isVisited, int initRow, int initCol) {

    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    isVisited[initRow][initCol] = true;
    int count = 1;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol, 0, 0, size, size)
        && !isVisited[nextRow][nextCol]
        && grid[nextRow][nextCol] != 0) {
          q.add(new Point(nextRow, nextCol));
          isVisited[nextRow][nextCol] = true;
          count++;
        }
      }
    }
    return count;
  }
  static void activeFireStorm(int[][] grid) {

    Queue<Point> q = new ArrayDeque<>();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        int count = 0;

        if (grid[i][j] == 0) continue;

        for (int d = 0; d < 4; d++) {
          int checkRow = i + dr[d];
          int checkCol = j + dc[d];
          if (isValidIdx(checkRow, checkCol, 0, 0, size, size)
          && grid[checkRow][checkCol] != 0)
            count++;
        }

        if (count < 3) q.add(new Point(i, j));
      }
    }

    while (!q.isEmpty()) {
      Point point = q.poll();

      grid[point.row][point.col]--;
    }
  }
  static int[][] allRotate(int[][] grid, int level) {

    int interval = (int)Math.pow(2, level);

    int[][] rotatedGrid = new int[size][size];
    for (int i = 0; i < size; i += interval) {
      for (int j = 0; j < size; j += interval) {
        int ptr = 0;
        while (ptr < interval - ptr) {
          int originRowPtr = i + ptr;
          int originColPtr = j + ptr;

          int nextRowPtr = i + ptr;
          int nextColPtr = j + (interval - 1 - ptr);
          rotatedGrid[nextRowPtr][nextColPtr] = grid[originRowPtr][originColPtr];

          for (int d = 0; d < 4; d++) {
            while (isValidIdx(originRowPtr + dr[d], originColPtr + dc[d], i + ptr, j + ptr, i + interval - ptr, j + interval - ptr)) {
              originRowPtr += dr[d];
              originColPtr += dc[d];
              nextRowPtr += nextDr[d];
              nextColPtr += nextDc[d];

              rotatedGrid[nextRowPtr][nextColPtr] = grid[originRowPtr][originColPtr];
            }
          }
          ptr++;
        }
      }
    }
    return rotatedGrid;
  }
  static boolean isValidIdx(int row, int col, int startRow, int startCol, int rowLen, int colLen) {
    return row >= startRow && col >= startCol && row < rowLen && col < colLen;
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
