import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(queryResult(grid, N));
  }
  private static int queryResult(int[][] grid, int N) {
    boolean[] flag = new boolean[2 * N - 1];

    int evenResult = backTracking(grid, flag, 0, 0, N);
    int oddResult = backTracking(grid, flag, 1, 0, N);

    return evenResult + oddResult;
  }
  private static int backTracking(int[][] grid, boolean[] flag, int ptr, int count, int size) {
    int max = Integer.MIN_VALUE;

    if (ptr >= 2 * size - 1) {
      return count;
    } else {
      int row = ptr < size ? ptr : size - 1;
      int col = ptr < size ? 0 : ptr - (size - 1);

      while (row >= 0 && col < size) {
        if (grid[row][col] == 1 && !flag[(size - 1) - row + col]) {
          flag[(size - 1) - row + col] = true;
          max = Math.max(max, backTracking(grid, flag, ptr + 2, count + 1, size));
          flag[(size - 1) - row + col] = false;
        }
        row--;
        col++;
      }
      max = Math.max(max, backTracking(grid, flag, ptr + 2, count, size));
    }

    return max;
  }
}