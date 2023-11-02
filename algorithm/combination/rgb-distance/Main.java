import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N][3];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(queryResult(grid, N));
  }
  private static int queryResult(int[][] grid, int N) {

    int[][] table = initDpTable(grid, N);

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          if (j != k) {
            table[i][j] = Math.min(table[i][j], table[i - 1][k] + grid[i][j]);
          }
        }
      }
    }

    return getMinCost(table, N);
  }
  private static int getMinCost(int[][] table, int N) {
    int min = INF;

    for (int i = 0; i < 3; i++) {
      min = Math.min(min, table[N - 1][i]);
    }

    return min;
  }
  private static int[][] initDpTable(int[][] grid, int N) {

    int[][] table = new int[N][3];

    for (int i = 0; i < N; i++) {
      Arrays.fill(table[i], INF);
    }

    for (int i = 0; i < 3; i++) {
      table[0][i] = grid[0][i];
    }

    return table;
  }
}