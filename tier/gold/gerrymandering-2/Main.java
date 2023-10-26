import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {0, -1, 0, 1};
  static int[] ay = {-1, 0, 1, 0};
  static int INF = 40001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] A = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(A, N));

  }
  static int simulation(int[][] A, int N) {
    int min_diff = INF;
    for (int d1 = 1; d1 <= N; d1++) {
      for (int d2 = 1; d2 <= N; d2++) {
        for (int x = 1; x + d1 + d2 <= N; x++) {
          for (int y = d1 + 1; y + d2 <= N; y++) {

            // 격자 생성
            int[][] grid = new int[N + 1][N + 1];

            // 경계선 채우기
            for (int i = 0; i <= d1; i++) {
              grid[x + i][y - i] = 5;
              grid[x + d2 + i][y + d2 - i] = 5;
            }
            for (int i = 0; i <= d2; i++) {
              grid[x + i][y + i] = 5;
              grid[x + d1 + i][y - d1 + i] = 5;
            }

            // 격자 꼭지점 늘려주기
            // 1구역, 2구역, 3구역, 4구역
            for (int i = x - 1; i >= 1; i--)
              grid[i][y] = 1;
            for (int j = y + d2 + 1; j <= N; j++)
              grid[x + d2][j] = 2;
            for (int j = y - d1 - 1; j >= 1; j--)
              grid[x + d1][j] = 3;
            for (int i = x + d1 + d2 + 1; i <= N; i++)
              grid[i][y - d1 + d2] = 4;


            // 5를 제외한 나머지 구역 채우기
            boolean[][] isVisited = new boolean[N + 1][N + 1];
            // 1구역
            dfs(grid, isVisited, N, x, y - 1, 1);
            // 2구역
            dfs(grid, isVisited, N, x, y + 1, 2);
            // 3구역
            dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2  - 1, 3);
            // 4구역
            dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2 + 1, 4);

            for (int i = 1; i <= N; i++) {
              for (int j = 1; j <= N; j++) {
                if (grid[i][j] == 0) grid[i][j] = 5;
              }
            }

            int[] areaSum = new int[6];

            for (int i = 1; i <= N ; i++) {
              for (int j = 1; j <= N; j++) {
                int areaNum = grid[i][j];
                areaSum[areaNum] += A[i][j];
              }
            }

            int max = 0;
            int min = INF;

            for (int i = 1; i <= 5; i++) {
              max = Math.max(areaSum[i], max);
              min = Math.min(areaSum[i], min);
            }

            min_diff = Math.min(max - min, min_diff);
          }
        }
      }
    }
    return min_diff;
  }

  static void dfs(int[][] grid, boolean[][] isVisited, int N, int x, int y, int num) {

    isVisited[x][y] = true;
    grid[x][y] = num;

    for (int i = 0; i < 4; i++) {
      int nextX = x + ax[i];
      int nextY = y + ay[i];

      if (isValidIdx(nextX, nextY, N) && grid[nextX][nextY] == 0 && !isVisited[nextX][nextY]) {
        dfs(grid, isVisited, N, nextX, nextY, num);
      }
    }
  }

  static boolean isValidIdx(int x, int y, int N) {
    if (x < 1 || y < 1 || x > N || y > N) return false;
    return true;
  }
}