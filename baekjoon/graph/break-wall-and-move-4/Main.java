import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        grid[i][j] = str.charAt(j) - '0';
      }
    }

    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    map.put(1, 0);

    int num = 2;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 0) {
          int count = bfs(grid, num, i, j);
          map.put(num, count);
          num++;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] != 1) sb.append("0");
        else {
          HashSet<Integer> set = new HashSet<>();

          for (int d = 0; d < 4; d++) {
            int nextRow = i + dr[d];
            int nextCol = j + dc[d];

            if (isValidIdx(nextRow, nextCol)) set.add(grid[nextRow][nextCol]);
          }
          int sum = 1;
          for (int idx : set) sum += map.get(idx);

         sb.append(sum % 10);
        }
      }
      sb.append("\n");
    }

    System.out.println(sb);
  }
  static int bfs(int[][] grid, int num, int initRow, int initCol) {
    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    grid[initRow][initCol] = num;
    int count = 1;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && grid[nextRow][nextCol] == 0) {
          q.add(new Point(nextRow, nextCol));
          grid[nextRow][nextCol] = num;
          count++;
        }
      }
    }

    return count;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < M;
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