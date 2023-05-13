import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1};
  static int[] dc = {1, 1, 1};
  static int R, C;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    String[][] grid = new String[R][C];
    for (int i = 0; i < R; i++) {
      String str = br.readLine();
      for (int j = 0; j < C; j++) {
        grid[i][j] = str.substring(j, j + 1);
      }
    }

    System.out.println(greedy(grid));

  }
  static int greedy(String[][] grid) {

    boolean[][] isVisited = new boolean[R][C];
    int count = 0;
    for (int i = 0; i < R; i++) {
      if (dfs(grid, isVisited, i, 0)) count++;
    }
    return count;
  }
  static boolean dfs(String[][] grid, boolean[][] isVisited, int row, int col) {

    isVisited[row][col] = true;
    if (col == C - 1) return true;

    boolean isSuccess = false;
    for (int i = 0; i < 3; i++) {
      int nextRow = row + dr[i];
      int nextCol = col + dc[i];

      if (!isSuccess && isValidIdx(nextRow, nextCol) && grid[nextRow][nextCol].equals(".") && !isVisited[nextRow][nextCol])
        isSuccess = dfs(grid, isVisited, nextRow, nextCol);
    }
    return isSuccess;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < R && col < C;
  }
}