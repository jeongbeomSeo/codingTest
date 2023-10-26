import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int R, C;
  static int[] dr = {-1, 0, 1};
  static int[] dc = {1, 1, 1};
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

    int row = 0;
    int count = 0;
    while (row < R) {
      if (dfs(grid, row, 0)) count++;
      row++;
    }

    return count;
  }
  static boolean dfs(String[][] grid, int row, int col) {

    grid[row][col] = "@";

    if (col == C - 1) return true;

    boolean isSuccess = false;

    for (int i = 0; i < 3; i++) {
      int nextRow = row + dr[i];
      int nextCol = col + dc[i];
      if (!isSuccess && isValidIdx(nextRow, nextCol) && grid[nextRow][nextCol].equals(".")) {
        isSuccess = dfs(grid, nextRow, nextCol);
      }
    }

    return isSuccess;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < R && col < C;
  }
}