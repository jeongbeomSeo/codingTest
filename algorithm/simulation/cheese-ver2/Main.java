import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  private static final int[] dr = {-1, 0, 1, 0};
  private static final int[] dc = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N ; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid, N, M));
  }
  private static int simulation(int[][] grid, int N, int M) {

    int time = 0;

    // Simulation 전역 변수
    // 1. 치즈 외부 공기 접촉 횟수 int[][]
    // 2. 2회 이상 접촉한 치즈 위치 Queue
    // 3. 치즈 아닌 위치 방문 여부 boolean[][]
    while (true) {

      int[][] countCheese = new int[N][M];
      Queue<Point> meltDownCheeseBuffer = new ArrayDeque<>();
      boolean[][] isVisited = new boolean[N][M];

      int row = 0;
      int col = 0;
      do {

        if (!isVisited[row][col]) {
          checkMeltDownCheese(grid, countCheese, meltDownCheeseBuffer, isVisited, row, col, N, M);
        }

        Point point = nextPoint(row, col, N, M);
        row = point.row;
        col = point.col;
      } while (!(row == 0 && col == 0));

      if (meltDownCheeseBuffer.isEmpty()) break;
      meltDownCheese(grid, meltDownCheeseBuffer);

      time++;
    }

    return time;
  }
  private static void meltDownCheese(int[][] grid, Queue<Point> meltDownCheeseBuffer) {

    while (!meltDownCheeseBuffer.isEmpty()) {
      Point point = meltDownCheeseBuffer.poll();

      grid[point.row][point.col] = 0;
    }
  }
  private static void checkMeltDownCheese(int[][] grid, int[][] countCheese, Queue<Point> meltDownCheeseBuffer,
                                    boolean[][] isVisited, int row, int col, int N, int M) {

    Queue<Point> q = new ArrayDeque<>();

    q.add(new Point(row, col));
    isVisited[row][col] = true;

    while (!q.isEmpty()) {
      Point point = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = point.row + dr[i];
        int nextCol = point.col + dc[i];

        if (isValidPoint(nextRow, nextCol, N, M) && !isVisited[nextRow][nextCol]) {
          if (grid[nextRow][nextCol] == 1) {
            if (++countCheese[nextRow][nextCol] == 2) {
              meltDownCheeseBuffer.add(new Point(nextRow, nextCol));
            }
          } else {
            q.add(new Point(nextRow, nextCol));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }
  }
  private static boolean isValidPoint(int row, int col, int N, int M) {
    return row >= 0 && col >= 0 && row < N && col < M;
  }
  private static Point nextPoint(int row, int col, int N, int M) {
    if (row == 0) {
      if (col + 1 != M) return new Point(row, col + 1);
      return new Point(row + 1, col);
    } else if (col == M - 1) {
      if (row + 1 != N) return new Point(row + 1, col);
      return new Point(row, col - 1);
    } else if (row == N - 1) {
      if (col - 1 != -1) return new Point(row, col - 1);
      return new Point(row - 1, col);
    }
    return new Point(row - 1, col);
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }
}