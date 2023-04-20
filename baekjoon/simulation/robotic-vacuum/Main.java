import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int[] reverse = {2, 3, 0, 1};
  static int count = 0 ;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] grid = new int[N][M];

    st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int direction = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M ; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    dfs(grid, row, col, direction);

    System.out.println(count);
  }
  static void dfs(int[][] grid, int row, int col, int direction) {

    if (grid[row][col] == 0) {
      grid[row][col] = -1;
      count++;
    }

    boolean flag = false;

    for (int i = 0 ; i < 4; i++) {
      int nd = (direction + 3 - i) % 4;
      int nr = row + dr[nd];
      int nc = col + dc[nd];

      if (grid[nr][nc] == 0) {
        flag = true;
        dfs(grid, nr, nc, nd);
        break;
      }
    }
    if (!flag) {
      int back = reverse[direction];
      int nr = row + dr[back];
      int nc = col + dc[back];

      if (grid[nr][nc] != 1) {
        dfs(grid, nr, nc, direction);
      }
      else return;
    }

  }
}