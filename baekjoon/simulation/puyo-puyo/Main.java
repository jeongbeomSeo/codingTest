import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[][] grid = new String[12][6];

    for (int i = 0; i < 12; i++) {
      String str = br.readLine();
      for (int j = 0; j < 6; j++) {
        grid[i][j] = str.substring(j, j + 1);
      }
    }
    System.out.println(simulation(grid));
  }
  static int simulation(String[][] grid) {

    int time = 0;
    while (true) {

      boolean curTime_isPunk = false;
      boolean[][] isPunk = new boolean[12][6];
      boolean[][] isVisited = new boolean[12][6];

      for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 6; j++) {
          if (!grid[i][j].equals(".") && !isVisited[i][j])
            if (bfs(grid, i, j, isPunk, isVisited)) curTime_isPunk = true;
        }
      }

      if (!curTime_isPunk) break;
      else time++;
      active_punk(grid, isPunk);
      active_CleanUp(grid);
    }
    return time;
  }
  static void active_punk(String[][] grid, boolean[][] isPunk) {
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 6; j++) {
        if (isPunk[i][j]) grid[i][j] = ".";
      }
    }
  }
  static void active_CleanUp(String[][] grid) {

    for (int col = 0; col < 6; col++) {
      for (int row = 11; row >= 0; row--) {
        if (grid[row][col].equals(".")) {
          int nextRow = row - 1;
          while (nextRow >= 0 && grid[nextRow][col].equals(".")) nextRow--;
          if (nextRow >= 0) {
            grid[row][col] = grid[nextRow][col];
            grid[nextRow][col] = ".";
          }
          else break;
        }
      }
    }
  }
  static boolean bfs(String[][] grid, int initRow, int initCol, boolean[][] isPunk, boolean[][] isVisited) {

    Queue<Point> q = new LinkedList<>();
    q.add(new Point(initRow, initCol));
    isVisited[initRow][initCol] = true;

    String target = grid[initRow][initCol];

    Queue<Point> buffer = new LinkedList<>();
    buffer.add(new Point(initRow, initCol));

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol] && grid[nextRow][nextCol].equals(target)) {
          Point nextPoint = new Point(nextRow, nextCol);
          q.add(nextPoint);
          buffer.add(nextPoint);
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    if (buffer.size() >= 4) {
      while (!buffer.isEmpty()) {
        Point point = buffer.poll();
        isPunk[point.row][point.col] = true;
      }
      return true;
    }

    return false;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < 12 && col < 6;
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