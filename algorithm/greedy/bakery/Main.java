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

    char[][] grid = new char[R][C];
    for (int i = 0; i < R; i++) {
      String str = br.readLine();
      for (int j = 0; j < C; j++) {
        grid[i][j] = str.charAt(j);
      }
    }

    System.out.println(queryResult(grid));
  }
  private static int queryResult(char[][] grid) {

    int count = 0;
    for (int i = 0; i < R; i++) {
      if (dfs(grid, i, 0)) count++;
    }
    return count;
  }
  private static boolean dfs(char[][] grid, int row, int col) {

    if (col == C - 1) {
      grid[row][col] = 'O';
      return true;
    } else {
      for (int i = 0; i < 3; i++) {
        int nxtRow = row + dr[i];
        int nxtCol = col + dc[i];

        if (boundaryCheck(nxtRow, nxtCol) && grid[nxtRow][nxtCol] == '.') {
          if (dfs(grid, nxtRow, nxtCol)) {
            grid[nxtRow][nxtCol] = 'O';
            return true;
          }
          else {
            grid[nxtRow][nxtCol] = 'X';
          }
        }
      }
    }
    return false;
  }
  private static boolean boundaryCheck(int row, int col) {
    return row >= 0 && col >= 0 && row < R && col < C;
  }
}