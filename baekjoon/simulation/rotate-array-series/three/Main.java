import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, R;
  // 동, 남, 서, 북
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};

  // 남, 동, 북, 서
  static int[] next_dr = {1, 0, -1, 0};
  static int[] next_dc = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0 ; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int[] rotate = new int[R];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < R; i++) {
      rotate[i] = Integer.parseInt(st.nextToken());
    }
    simulation(grid,rotate);

  }
  static void simulation(int[][] grid, int[] rotate) {

    for (int i = 0; i < R; i++) {
      grid = rotateType(grid, rotate[i]);
    }

    for (int i = 0 ; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }
  static int[][] rotateType(int[][] baseGrid, int rotate) {
    int[][] grid = null;

    switch (rotate) {
      case 1:
        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            grid[i][j] = baseGrid[(N - 1) - i][j];
          }
        }
        break;
      case 2:
        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            grid[i][j] = baseGrid[i][(M - 1) - j];
          }
        }
        break;
      case 3:
        grid = new int[M][N];
        for (int i = 0; i < M; i++) {
          for (int j = 0; j < N; j++) {
            grid[i][j] = baseGrid[(N - 1) - j][i];
          }
        }
        break;
      case 4:
        grid = new int[M][N];
        for (int i = 0; i < M; i++) {
          for (int j = 0; j < N; j++) {
            grid[i][j] = baseGrid[j][(M - 1) - i];
          }
        }
        break;
      case 5: case 6:
        grid = new int[N][M];
        for (int d = 1; d < 4; d++) {
          int row = 0;
          int col = 0;
          switch (d) {
            case 1:
              row = col = 0;
              break;
            case 2:
              row = 0;
              col = M / 2;
              break;
            case 3:
              row = N / 2;
              col = M / 2;
              break;
            case 4:
              row = N / 2;
              col = 0;
              break;
          }
          int mr = dr[d] * N / 2;
          int mc = dc[d] * M / 2;
          if (rotate == 6) {
            mr = next_dr[d] * N / 2;
            mc = next_dc[d] * M / 2;
          }
          for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
              grid[row + i][col + j] = baseGrid[row + i + mr][col + j + mc];
            }
          }
        }
        break;
    }
    return grid;
  }
}
