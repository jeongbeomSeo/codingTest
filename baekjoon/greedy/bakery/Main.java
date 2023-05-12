import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1};
  static int[] dc = {-1, -1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    String[][] grid = new String[R][C];
    for (int i = 0; i < R; i++) {
      String str = br.readLine();
      for (int j = 0; j < C; j++) {
        grid[i][j] = str.substring(j, j + 1);
      }
    }

    System.out.println(greedy(grid, R, C));

  }
  static int greedy(String[][] grid, int R, int C) {
    boolean[][] canReach = new boolean[R][C];

    for (int i = 0; i < R; i++) {
      canReach[i][0] = grid[i][0].equals(".");
    }

    for (int col = 1; col < C; col++) {
      for (int row = 0; row < R; row++) {
        if (grid[row][col].equals("x")) continue;
        if (row == 0) {
          for (int i = 1; i < 3; i++) {
            if (canReach[row + dr[i]][col + dc[i]]) {
              canReach[row + dr[i]][col + dc[i]] = false;
              canReach[row][col] = true;
              break;
            }
          }
        }
        else if (row == R - 1) {
          for (int i = 0; i < 2; i++) {
            if (canReach[row + dr[i]][col + dc[i]]) {
              canReach[row + dr[i]][col + dc[i]] = false;
              canReach[row][col] = true;
              break;
            }
          }
        }
        else {
          for (int i = 0; i < 3; i++) {
            if (canReach[row + dr[i]][col + dc[i]]) {
              canReach[row + dr[i]][col + dc[i]] = false;
              canReach[row][col] = true;
              break;
            }
          }
        }
      }
    }

    int count = 0;
    for (int row = 0; row < R; row++) {
      if (canReach[row][C - 1]) count++;
    }
    return count;
  }
}
