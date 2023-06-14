import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, -1, 0, 1};
  // 북, 서, 남, 동 (0 ~ 3)(0 ~ 8 / 9는 alpha)
  static int[][] sand_dr = {
          {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1},
          {0, 1, -1, 1, -1, 2, -2, 1, -1, 0},
          {2, 1, 1, 0, 0, 0, 0, -1, -1, 1},
          {0, 1, -1, 1, -1, 2, -2, 1, -1, 0}
  };
  static int[][] sand_dc = {
          {0, 1, -1, 1, -1, 2, -2, 1, -1, 0},
          {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1},
          {0, 1, -1, 1, -1, 2, -2, 1, -1, 0},
          {2, 1, 1, 0, 0, 0, 0, -1, -1, 1}
  };
  // 0 ~ 8
  static int[] sand_percent = {
          5, 10, 10, 7, 7, 2, 2, 1, 1
  };

  static int startRow, startCol;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N + 1][N + 1];

    startRow = startCol = (N + 1) / 2;

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    return activeTornado(grid);
  }
  static int activeTornado(int[][] grid) {

    int outOfSandAmount = 0;
    boolean[][] isVisited = new boolean[N + 1][N + 1];

    int curRow = startRow;
    int curCol = startCol;
    int curDirection = 0;

    while (!(curRow == 1 && curCol == 1)) {

      isVisited[curRow][curCol] = true;

      int leftDirection = getLeftDirection(curDirection);

      int leftRow = curRow + dr[leftDirection];
      int leftCol = curCol + dc[leftDirection];

      if (!isVisited[leftRow][leftCol]) {
        curRow = leftRow;
        curCol = leftCol;
        curDirection = leftDirection;
      }
      else {
        curRow += dr[curDirection];
        curCol += dc[curDirection];
      }

      outOfSandAmount += activeMoveSand(grid, curRow, curCol, curDirection);
    }

    return outOfSandAmount;
  }
  static int activeMoveSand(int[][] grid, int row, int col, int direction) {

    int remainSandAmount = grid[row][col];
    int outOfSandAmount = 0;

    if (remainSandAmount == 0) return 0;

    for (int i = 0; i < 9; i++) {
      int nextRow = row + sand_dr[direction][i];
      int nextCol = col + sand_dc[direction][i];
      int percent = sand_percent[i];

      int moveSandAmount = grid[row][col] * percent / 100;

      if (isValidIdx(nextRow, nextCol)) {
        grid[nextRow][nextCol] += moveSandAmount;
      }
      else outOfSandAmount += moveSandAmount;

      remainSandAmount -= moveSandAmount;
    }

    int alphaRow = row + sand_dr[direction][9];
    int alphaCol = col + sand_dc[direction][9];
    if (isValidIdx(alphaRow, alphaCol)) grid[alphaRow][alphaCol] += remainSandAmount;
    else outOfSandAmount += remainSandAmount;

    return outOfSandAmount;
  }
  static int getLeftDirection(int direction) {
    return direction + 1 != 4 ? direction + 1 : 0;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
}