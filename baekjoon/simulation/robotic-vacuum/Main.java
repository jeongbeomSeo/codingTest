import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int[] back = {2, 3, 0, 1};
  static int count = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int direction = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    simulation(grid, row, col, direction);

    System.out.println(count);

  }
  static void simulation(int[][] grid, int row, int col, int direction) {

    boolean[][] isVisited = new boolean[N][M];

    dfs(grid, isVisited, row, col, direction);
  }
  static void dfs(int[][] grid, boolean[][] isVisited, int row, int col, int direction) {

    // 후진으로 인한 재방문도 고려해서 조건문 필요
    if (grid[row][col] == 0) {
      count++;
      isVisited[row][col] = true;
      grid[row][col] = -1;
    }

    boolean flag = false;
    for (int i = 0; i < 4; i++) {
      int curDirection = (direction + 3 - i) % 4;
      int curRow = row + dr[curDirection];
      int curCol = col + dc[curDirection];

      if (isValidIdx(curRow, curCol) && !isVisited[curRow][curCol]
      && grid[curRow][curCol] == 0) {
        dfs(grid, isVisited, curRow, curCol, curDirection);
        flag = true;
        break;
      }
    }
    if (!flag) {
      int backDirection = back[direction];
      int curRow = row + dr[backDirection];
      int curCol = col + dc[backDirection];

      if (isValidIdx(curRow, curCol) && grid[curRow][curCol] != 1)
        dfs(grid, isVisited, curRow, curCol, direction);
    }


  }
  static boolean isValidIdx(int row, int col) {
    if (row < 0 || col < 0 || row > N - 1 || col > M - 1) return false;
    else return true;
  }
}