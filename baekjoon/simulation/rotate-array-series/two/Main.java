import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  // 동, 남, 서, 북
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    simulation(grid, R);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }
  static void simulation(int[][] grid, int R) {

    int num = 0;
    int end_row = N - 1;
    int end_col = M - 1;

    while (end_row - num >= 1 && end_col - num >= 1) {
      int number_count = (end_row - num) * 2 + (end_col - num) * 2;
      int rotate = R % number_count;

      while (rotate-- > 0) {
        int row = num;
        int col = num;
        int temp = grid[row][col]; // 6
        for (int i = 0; i < 4; i++) {
          while (isValidIdx(row + dr[i], col + dc[i], num, end_row, end_col)) {
            grid[row][col] = grid[row + dr[i]][col + dc[i]];
            row += dr[i];
            col += dc[i];
          }
        }
        grid[row + 1][col] = temp;
      }

      end_row--;
      end_col--;
      num++;
    }

  }
  static boolean isValidIdx(int row, int col, int num, int end_row, int end_col) {
    return row >= num && col >= num && row <= end_row && col <= end_col;
  }
}