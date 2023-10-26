import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] dr = {1, 0, -1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int MIN = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] rotate = new int[K][3];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        rotate[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid, rotate));
  }
  static int simulation(int[][] grid, int[][] rotate) {

    select_rotate(grid, rotate, new boolean[K]);

    return MIN;
  }
  static void select_rotate(int[][] grid, int[][] rotate, boolean[] isUsedRotate) {

    boolean usedAllRotate = true;

    for (int i = 0; i < K; i++) {
      if (!isUsedRotate[i]) {
        usedAllRotate = false;

        int[][] newGrid = copy_grid(grid);
        rotateGird(newGrid, rotate[i]);
        isUsedRotate[i] = true;
        select_rotate(newGrid, rotate, isUsedRotate);
        isUsedRotate[i] = false;
      }
    }
    if (usedAllRotate) MIN = Math.min(MIN, queryArray(grid));
  }
  static int queryArray(int[][] grid) {
    int min = Integer.MAX_VALUE;

    for (int i = 1; i <= N; i++) {
      int sum = 0;
      for (int j = 1; j <= M; j++) {
        sum += grid[i][j];
      }
      min = Math.min(min, sum);
    }
    return min;
  }
  static void rotateGird(int[][] grid, int[] rotate) {

    int initRow = rotate[0] - rotate[2];
    int initCol = rotate[1] - rotate[2];
    int rowLen = rotate[0] + rotate[2];
    int colLen = rotate[1] + rotate[2];

    int idx = 0;
    while (initRow + idx < rowLen - idx && initCol + idx < colLen - idx) {
      int nextRow = initRow + idx;
      int nextCol = initCol + idx;
      int temp = grid[nextRow][nextCol];
      for (int i = 0; i < 4; i++) {
        while (isValidIdx(nextRow + dr[i], nextCol + dc[i], initRow + idx, initCol + idx, rowLen - idx, colLen - idx)) {
          grid[nextRow][nextCol] = grid[nextRow + dr[i]][nextCol + dc[i]];
          nextRow += dr[i];
          nextCol += dc[i];
        }
      }
      grid[nextRow][nextCol + 1] = temp;
      idx++;
    }
  }
  static boolean isValidIdx(int row, int col, int rowStart, int colStart, int rowLen, int colLen) {
    return row >= rowStart && col >= colStart && row <= rowLen && col <= colLen;
  }
  static int[][] copy_grid(int[][] grid) {
    int[][] newGrid = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      newGrid[i] = Arrays.copyOf(grid[i], M + 1);
    }

    return newGrid;
  }
}