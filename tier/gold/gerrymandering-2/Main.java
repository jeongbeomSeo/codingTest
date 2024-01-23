import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  private static final int[] DR = {-1, 0, 1, 0};
  private static final int[] DC = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid, N));
  }
  private static int simulation(int[][] grid, int N) {

    int minSubResult = Integer.MAX_VALUE;
    for (int d1 = 1; d1 < N - 1; d1++) {
      for (int d2 = 1; d1 + d2 < N; d2++) {
        for (int x = 1; x <= N - d1 - d2; x++) {
          for (int y = 1 + d1; y <= N - d2; y++) {

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
//            if (!(x == 3 && y == 5 && d1 == 2 && d2 == 1)) continue;
            int[][] startPointArray = getStartPointArray(x, y, N);
            int[][] districtGrid = createDistrictGrid(x, y, d1, d2, N, startPointArray);

//            for (int i = 1; i <= N; i++) {
//              for (int j = 1; j <= N; j++) {
//                System.out.print(districtGrid[i][j] + " ");
//              }
//              System.out.println();
//            }

            for (int i = 0; i < 5; i++) {
              int score = getScore(grid, districtGrid, startPointArray[i][0], startPointArray[i][1], i + 1, N);
              min = Math.min(min, score);
              max = Math.max(max, score);
            }

            minSubResult = Math.min(minSubResult, max - min);
          }
        }
      }
    }

    return minSubResult;
  }
  private static int[][] getStartPointArray(int x, int y, int N) {
    return new int[][] {
            {1, 1},
            {1, N},
            {N, 1},
            {N, N},
            {x, y}
    };
  }
  private static int getScore(int[][] grid, int[][] districtGrid, int initRow, int initCol, int color, int N) {

    int score = 0;
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    isVisited[initRow][initCol] = true;

    while (!q.isEmpty()) {
      Point curPoint = q.poll();

      score += grid[curPoint.row][curPoint.col];

      for (int i = 0; i < 4; i++) {
        int nxtRow = curPoint.row + DR[i];
        int nxtCol = curPoint.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol, N)
                && districtGrid[nxtRow][nxtCol] == color
                && !isVisited[nxtRow][nxtCol]) {
          q.add(new Point(nxtRow, nxtCol));
          isVisited[nxtRow][nxtCol] = true;
        }
      }
    }

    return score;
  }
  private static int[][] createDistrictGrid(int x, int y, int d1, int d2, int N, int[][] startPointArray) {

    // 대각선 5
    int[][] grid = new int[N + 1][N + 1];
    for (int i = 0; i <= d1; i++) {
      grid[x + i][y - i] = 5;
      grid[x + d2 + i][y + d2 - i] = 5;
    }
    for (int i = 0; i <= d2; i++) {
      grid[x + i][y + i] = 5;
      grid[x + d1 + i][y - d1 + i] = 5;
    }

    // 1
    for (int i = x - 1; i >= 0; i--) {
      grid[i][y] = 1;
    }
    // 2
    for (int i = y + d2 + 1; i <= N; i++) {
      grid[x + d2][i] = 2;
    }
    // 3
    for (int i = y - d1 - 1; i >= 0; i--) {
      grid[x + d1][i] = 3;
    }
    // 4
    for (int i = x + d1 + d2 + 1; i <= N; i++) {
      grid[i][y - d1 + d2] = 4;
    }

//    for (int i = 1; i <= N; i++) {
//      for (int j = 1; j <= N; j++) {
//        System.out.print(grid[i][j] + " ");
//      }
//      System.out.println();
//    }

    for (int i = 0; i < 4; i++) {
      paint(grid, startPointArray[i][0], startPointArray[i][1], i + 1, N);
    }
    // 5는 위의 방식대로 채우면 3번쨰 예제 입력을 넣어보면 재대로 작동 안함. 이유는 남은 칸들이 서로 격리되어 있을 수 있기 때문
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (grid[i][j] == 0) grid[i][j] = 5;
      }
    }

    return grid;
  }
  private static void paint(int[][] grid, int initRow, int initCol, int color, int N) {

    Queue<Point> q = new ArrayDeque<>();
    q.add(new Point(initRow, initCol));
    grid[initRow][initCol] = color;

    while (!q.isEmpty()) {
      Point curPoint = q.poll();

      for (int i = 0; i < 4; i++) {
        int nxtRow = curPoint.row + DR[i];
        int nxtCol = curPoint.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol, N) && grid[nxtRow][nxtCol] == 0) {
          grid[nxtRow][nxtCol] = color;
          q.add(new Point(nxtRow, nxtCol));
        }
      }
    }
  }
  private static boolean isValidPoint(int row, int col, int N) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
}
class Point {
  int row;
  int col;

  Point(int row, int col) {
    this.row = row;
    this.col = col;
  }
}