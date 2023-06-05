import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] dr = {1, 0, -1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int Min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st =  new StringTokenizer(br.readLine());

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

    int[][] rotates = new int[K][3];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      rotates[i][0] = Integer.parseInt(st.nextToken());
      rotates[i][1] = Integer.parseInt(st.nextToken());
      rotates[i][2] = Integer.parseInt(st.nextToken());
    }

    simulation(grid, rotates, new boolean[K]);
    System.out.println(Min);
  }
  static void simulation(int[][] grid, int[][] rotates, boolean[] isUsed) {

    boolean isEnd = true;
    for (int i = 0; i < rotates.length; i++) {
      if (!isUsed[i]) {
        isEnd = false;
        int[] rotate = rotates[i];
        int[][] newGrid = copyGrid(grid);

        rotateGrid(newGrid, rotate);
        isUsed[i] = true;
        simulation(newGrid, rotates, isUsed);
        isUsed[i] = false;
      }
    }
    if (isEnd) {
      for (int i = 1; i <= N; i++) {
        int sum = 0;
        for (int j = 1; j <= M; j++) {
          sum += grid[i][j];
        }
        Min = Math.min(Min, sum);
      }

    }
  }
  static int[][] copyGrid (int[][] grid) {
    int[][] newGrid = new int[N + 1][M + 1];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        newGrid[i][j] = grid[i][j];
      }
    }
    return newGrid;
  }

  static void rotateGrid(int[][] grid, int[] rotate) {

    int idx = 0;
    while (rotate[0] + rotate[2] - idx > rotate[0] - rotate[2] + idx && rotate[1] + rotate[2] - idx > rotate[1] - rotate[2] + idx) {
      int startRow = rotate[0] - rotate[2] + idx;
      int endRow = rotate[0] + rotate[2] - idx;
      int startCol = rotate[1] - rotate[2] + idx;
      int endCol = rotate[1] + rotate[2] - idx;

      int temp = grid[startRow][startCol];

      int nextRow = startRow;
      int nextCol = startCol;
      for (int i = 0; i < 4; i++) {
        while (isValidIdx(nextRow + dr[i], nextCol + dc[i], startRow, startCol, endRow, endCol)) {
          grid[nextRow][nextCol] = grid[nextRow + dr[i]][nextCol + dc[i]];
          nextRow += dr[i];
          nextCol += dc[i];
        }
      }
      grid[startRow][startCol + 1] = temp;
      idx++;
    }
  }
  static boolean isValidIdx(int row, int col, int startRow, int startCol, int rowLen, int colLen) {
    return row >= startRow && col >= startCol && row <= rowLen && col <= colLen;
  }
}
