import java.awt.*;
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

    simulation(grid);

  }
  static void simulation(int[][] baseGrid) {

    int num = 0;
    int last_row = N - 1;
    int last_col = M - 1;
    int[][] grid = new int[N][M];
    while (last_row - num >= 1 && last_col - num >= 1) {
      int number_count = (last_row - num) * 2 + (last_col - num) * 2;
      int rotate = R % number_count;

      Pointer prev_pointer = new Pointer(num, last_row, last_col);
      for (int i = 0; i < rotate; i++) prev_pointer.move();
      Pointer pointer = new Pointer(num, last_row, last_col);

      while (number_count-- > 0) {
        grid[pointer.row][pointer.col] = baseGrid[prev_pointer.row][prev_pointer.col];
        pointer.move();
        prev_pointer.move();
      }

      last_col--;
      last_row--;
      num++;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }

  }
}
class Pointer {
  private static final int[] dr = {0, 1, 0, -1};
  private static final int[] dc = {1, 0, -1, 0};
  private final int R;
  private final int C;
  int min_num;
  int row;
  int col;
  int direction;

  Pointer(int num, int R, int C) {
    this.min_num = num;
    this.row = num;
    this.col = num;
    this.R = R;
    this.C = C;
    this.direction = 0;
  }

  void move() {

    int row = this.row + dr[direction];
    int col = this.col + dc[direction];
    if (!isValidIdx(row, col)) {
      direction = (direction + 1) % 4;
      row = this.row + dr[direction];
      col = this.col + dc[direction];
    }

    this.row = row;
    this.col = col;
  }

  boolean isValidIdx(int row, int col) {
    return row >= min_num && col >= min_num && row <= R && col <= C;
  }

}