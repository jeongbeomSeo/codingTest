import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  // 동, 남, 서, 북
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  static int N, M, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    simulation(grid);
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }

  }
  static void simulation(int[][] grid) {

    while (R-- > 0) {
      int num = 1;
      int size_R = N;
      int size_C = M;

      while (size_R - num >= 1 && size_C - num >= 1) {
        int row = num;
        int col = num;
        int temp = grid[row][col];
        for (int i = 0; i < 4; i++) {
          while (isValidIdx(row + dr[i], col + dc[i], num, size_R, size_C)) {
            grid[row][col] = grid[row + dr[i]][col + dc[i]];
            row = row + dr[i];
            col = col + dc[i];
          }
        }
        grid[row + 1][col] = temp;
        num++;
        size_R--;
        size_C--;
      }
    }
  }
  static boolean isValidIdx(int row, int col, int num, int size_R, int size_C) {
    return row >= num && col >= num && row <= size_R && col <= size_C;
  }
}
