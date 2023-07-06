import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    while (tc-- > 0) {
      st = new StringTokenizer(br.readLine());

      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      char[][] grid = new char[h][w];

      ArrayList<Point> startPoint = new ArrayList<>();

      for (int i = 0; i < h; i++) {
        String str = br.readLine();
        for (int j = 0; j < w; j++) {
          grid[i][j] = str.charAt(j);

          if (isBoundary(i, j, h, w)) {
            if (grid[i][j] != '*') startPoint.add(new Point(i, j));
          }
        }
      }

      boolean[] hasKey = new boolean['z' - 'a' + 1];

      String keys = br.readLine();
      if (keys.charAt(0) != '0') {
        for (int i = 0; i < keys.length(); i++) {
          int keyIdx = getKeyIdx(keys.charAt(i));
          hasKey[keyIdx] = true;
        }
      }
      System.out.println(simulation(grid, hasKey, startPoint, h, w));
    }
  }
  static int simulation (char[][] grid, boolean[] hasKey, ArrayList<Point> startPoint, int h, int w) {
    int result = 0;

    boolean getKeyThisTurn;
    do {
      getKeyThisTurn = false;
      boolean[][] isVisited = new boolean[h][w];
      for (int i = 0; i < startPoint.size(); i++) {
        Point point = startPoint.get(i);

        if (isDoor(grid[point.row][point.col])) {
          if (hasKey[getDoorIdx(grid[point.row][point.col])]) grid[point.row][point.col] = '.';
          else continue;
        }

        if (isKey(grid[point.row][point.col])) {
          int keyIdx = getKeyIdx(grid[point.row][point.col]);
          hasKey[keyIdx] = true;
          getKeyThisTurn = true;
          grid[point.row][point.col] = '.';
        }

        if (!isVisited[point.row][point.col]) {
          int[] bfsResult = bfs(grid, hasKey, isVisited, point.row, point.col, h, w);

          if (bfsResult[1] == 1) getKeyThisTurn = true;
          result += bfsResult[0];
        }
      }

    } while (getKeyThisTurn);

    return result;
  }
  static int[] bfs (char[][] grid, boolean[] hasKey, boolean[][] isVisited, int initRow, int initCol, int h, int w) {

    int[] result = new int[2];

    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    isVisited[initRow][initCol] = true;

    while (!q.isEmpty()) {
      Point point = q.poll();

      if (grid[point.row][point.col] == '$') {
        result[0]++;
      }
      else if (isKey(grid[point.row][point.col])) {
        int keyIdx = getKeyIdx(grid[point.row][point.col]);
        if (!hasKey[keyIdx]) result[1] = 1;
        hasKey[keyIdx] = true;
      }

      grid[point.row][point.col] = '.';

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol, h, w)
                && !isVisited[nextRow][nextCol]
                && grid[nextRow][nextCol] != '*') {
          if ((isDoor(grid[nextRow][nextCol]) && !hasKey[getDoorIdx(grid[nextRow][nextCol])])) continue;

          q.add(new Point(nextRow, nextCol));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    return result;
  }
  static boolean isValidIdx(int row, int col, int h, int w) {
    return row >= 0 && col >= 0 && row < h && col < w;
  }
  static boolean isBoundary (int row, int col, int h, int w) {
    return row == 0 || col == 0 || row == h - 1 || col == w - 1;
  }
  static boolean isKey(char c) {
    return c >= 'a' && c <= 'z';
  }
  static boolean isDoor(char c) {
    return c >= 'A' && c <= 'Z';
  }
  static int getKeyIdx(char c) {
    return c - 'a';
  }
  static int getDoorIdx(char c) {
    return c - 'A';
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