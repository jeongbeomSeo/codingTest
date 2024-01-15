import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static int[] DR = {0, -1, 0};
  private static int[] DC = {-1, 0, 1};
  private static int max = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(queryWithDFS(grid, N, M, D));
  }
  private static int queryWithDFS(int[][] grid, int N, int M, int D) {
    max = Integer.MIN_VALUE;

    dfs(grid, new int[3], 0, N, M, 0, D);

    return max;
  }
  private static void dfs(int[][] grid, int[] buffer, int ptr, int N, int M, int size, int D) {

    if (size == 3) {
      int[][] copyGrid = copyGrid(grid, N, M);
      max = Math.max(max, getMaxKill(copyGrid, buffer, N, M, D));
    }
    else if (ptr != M) {
      buffer[size] = ptr;
      dfs(grid, buffer, ptr + 1, N, M, size + 1, D);
      buffer[size] = 0;

      dfs(grid, buffer, ptr + 1, N, M, size, D);
    }
  }
  private static int[][] copyGrid(int[][] grid, int N, int M) {
    int[][] copy = new int[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        copy[i][j] = grid[i][j];
      }
    }

    return copy;
  }
  private static int getMaxKill(int[][] grid, int[] buffer, int N, int M, int D) {

    int killCount = 0;
    while (isAliveEnemy(grid, N, M)) {
      for (int i = 0; i < 3; i++) {
        int arrowPos = buffer[i];

        Status resultStatus = getKilledEnemyPos(grid, N, arrowPos, M, D);
        if (resultStatus != null) {
          killCount++;
          grid[resultStatus.row][resultStatus.col] = 0;
        }
      }
      grid = getGridWhenNextStep(grid, N, M);
    }

    return killCount;
  }
  private static int[][] getGridWhenNextStep(int[][] grid, int N, int M) {
    int[][] copy = new int[N][M];

    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < M; j++) {
        copy[i + 1][j] = grid[i][j];
      }
    }

    return copy;
  }
  private static Status getKilledEnemyPos(int[][] grid, int N, int arrowPos, int M, int D) {

    boolean[][] isVisited = new boolean[N][M];
    Queue<Status> queueStatus = new ArrayDeque<>();
    queueStatus.add(new Status(N - 1, arrowPos, 1));
    isVisited[N - 1][arrowPos] = true;

    Queue<Status> resultByPq = new PriorityQueue<>(new ComparatorStatus());
    while (!queueStatus.isEmpty()) {
      Status curStatus = queueStatus.poll();

      if (grid[curStatus.row][curStatus.col] == 1) {
        resultByPq.add(curStatus);
      }
      if (curStatus.count == D) continue;

      for (int i = 0; i < 3; i++) {
        int nxtRow = curStatus.row + DR[i];
        int nxtCol = curStatus.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol, N, M) && !isVisited[nxtRow][nxtCol]) {
          queueStatus.add(new Status(nxtRow, nxtCol, curStatus.count + 1));
          isVisited[nxtRow][nxtCol] = true;
        }
      }
    }

    if (resultByPq.isEmpty()) return null;
    return resultByPq.poll();
  }
  private static boolean isValidPoint(int row, int col, int N, int M) {
    return row >= 0 && col >= 0 && row < N && col < M;
  }
  private static boolean isAliveEnemy(int[][] grid, int N, int M) {

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 1) return true;
      }
    }

    return false;
  }
}
class ComparatorStatus implements Comparator<Status> {
  @Override
  public int compare(Status o1, Status o2) {
    if (o1.col != o2.col) return o1.col - o2.col;
    else {
      if (o1.row != o2.row) return o2.row - o1.row;

      return o2.count - o1.count;
    }
  }

}
class Status{
  int row;
  int col;
  int count;

  Status(int row, int col, int count) {
    this.row = row;
    this.col = col;
    this.count = count;
  }
}