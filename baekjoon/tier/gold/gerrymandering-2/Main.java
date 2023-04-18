import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {0, -1, 0, 1};
  static int[] ay = {-1, 0, 1, 0};

  static int INF = 400000;
  static int MIN = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] A = new int[N + 1][N + 1];
    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j < N + 1; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(Simulation(A, N));
  }

  // 시물레이션
  /*
   * 1. x, y, d1, d2 결정 (x가 행이고 y가 열)
   * 2. 경계선위의 좌표들 5로 채워주고 DFS를 이용해서 경계선 밖의 구역 채워주기
   * 3. 경계선 안의 구역 5로 채워주고 구역간의 인구수 차이 계산
   * 4. 반복
   */

  static int Simulation(int[][] A, int N) {
    int min_diff_population = INF;

    for (int d1 = 1; d1 <= N; d1++) {
      for (int d2 = 1; d2 <= N; d2++) {
        for (int x = 1; x + d1 + d2 <= N; x++) {
          for (int y = d1 + 1; y + d2 <= N; y++) {
            // 구역 초기화
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

            // 경계선 밖 채워주기
            for (int i = x - 1; i >= 0; i--) grid[i][y] = 1;
            for (int i = y + d2 + 1; i <= N; i++) grid[x + d2][i] = 2;
            for (int i = y - d1 - 1; i >= 0; i--) grid[x + d1][i] = 3;
            for (int i = x + d1 + d2 + 1; i <= N; i++) grid[i][y - d1 + d2] = 4;

            boolean[][] isVisited = new boolean[N + 1][N + 1];

            // 1구역
            dfs(grid, isVisited, N, x, y - 1, 1);
            // 2구역
            dfs(grid,isVisited, N, x, y + 1, 2);
            // 3구역
            dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2 - 1, 3);
            // 4구역
            dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2 + 1, 4);

            // 경계 선안의 5구역 채워주기
            for (int i = 1; i <= N; i++) {
              for (int j = 1; j <= N; j++) {
                if (grid[i][j] == 0) grid[i][j] = 5;
              }
            }

            // 크기가 N + 1이 아니다. 무작정 크기를 설정하지 말자.
            int[] areaSum = new int[6];

            for (int i = 1; i <= N; i++) {
              for (int j = 1; j <= N; j++) {
                areaSum[grid[i][j]] += A[i][j];
              }
            }

            int max = MIN;
            int min = INF;

            // 영역이 몇 개인지 주의!
            for (int i = 1; i <= 5; i++) {
              max = Math.max(areaSum[i], max);
              min = Math.min(areaSum[i], min);
            }

            min_diff_population = Math.min(min_diff_population, Math.abs(max - min));
          }
        }
      }
    }
    return min_diff_population;
  }

  static void dfs(int[][] grid, boolean[][] isVisited, int N, int x, int y, int num) {

    grid[x][y] = num;
    isVisited[x][y] = true;

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