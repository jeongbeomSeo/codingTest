import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
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

    int[][] result = simulation(grid, rotate);

    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        System.out.print(result[i][j] + " ");
      }
      System.out.println();
    }
  }
  static int[][] simulation(int[][] grid, int[] rotate) {

    for (int rotate_number : rotate) {
      int[][] rotate_grid = null;
      switch (rotate_number) {
        case 1 :
          rotate_grid = rotateOne(grid);
          break;
        case 2:
          rotate_grid = rotateTwo(grid);
          break;
        case 3:
          rotate_grid = rotateThree(grid);
          break;
        case 4:
          rotate_grid = rotateFour(grid);
          break;
        case 5:
          rotate_grid = rotateFive_Six(grid, 5);
          break;
        case 6:
          rotate_grid = rotateFive_Six(grid, 6);
          break;
      }
      grid = rotate_grid;
    }

    return grid;
  }
  static int[][] rotateOne(int[][] baseGrid) {
    int row_length = baseGrid.length;
    int col_length = baseGrid[0].length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[row_length - 1 - i][j];
      }
    }
    return grid;
  }
  static int[][] rotateTwo(int[][] baseGrid) {
    int row_length = baseGrid.length;
    int col_length = baseGrid[0].length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[i][col_length - 1 - j];
      }
    }
    return grid;
  }
  static int[][] rotateThree(int[][] baseGrid) {
    int row_length = baseGrid[0].length;
    int col_length = baseGrid.length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[col_length - 1 - j][i];
      }
    }
    return grid;
  }
  static int[][] rotateFour(int[][] baseGrid) {
    int row_length = baseGrid[0].length;
    int col_length = baseGrid.length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[j][row_length - 1 - i];
      }
    }
    return grid;
  }
  static int[][] rotateFive_Six(int[][] baseGrid, int num) {
    int row_length = baseGrid.length;
    int col_length = baseGrid[0].length;
    int[][] grid = new int[row_length][col_length];

    int[] dr;
    int[] dc;
    // 남, 동, 북, 서 (N / 2, M / 2)
    dr = new int[]{row_length / 2, 0, -row_length / 2, 0};
    dc = new int[]{0, col_length / 2, 0, -col_length / 2};
    if (num == 6) {
      // 동, 남, 서, 북
      dr = new int[]{0, row_length / 2, 0, -row_length / 2};
      dc = new int[]{col_length / 2, 0, -col_length / 2, 0};
    }

      for (int i = 0; i < row_length / 2; i++) {
        for (int j = 0; j < col_length / 2; j++) {
          int row = i;
          int col = j;
          for (int k = 0; k < 4; k++) {
            grid[row][col] = baseGrid[row + dr[k]][col + dc[k]];
            row += dr[k];
            col += dc[k];
          }
        }
      }
      return grid;
  }
}