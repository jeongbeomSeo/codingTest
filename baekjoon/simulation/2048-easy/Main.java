import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int maxValue = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        maxValue = Math.max(maxValue, grid[i][j]);
      }
    }

    simulation(grid);
    System.out.println(maxValue);
  }
  static void simulation(int[][] initGird) {
    Queue<Case> q = new LinkedList<>();
    q.add(new Case(initGird, 0));

    while (!q.isEmpty()) {
      Case curCase = q.poll();

      if (curCase.time == 5) {
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            maxNum = Math.max(curCase.grid[i][j], maxNum);
          }
        }
        maxValue = Math.max(maxValue, maxNum);
        continue;
      }

      boolean change = false;
      for (int i = 0; i < 4; i++) {
        int[][] grid = new int[N][N];

        for (int row = 0; row < N; row++) {
          for (int col = 0; col < N; col++) {
            grid[row][col] = curCase.grid[row][col];
          }
        }

        // 북
        if (i == 0) {
          for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (row + ptr < N && grid[row + ptr][col] == 0) ptr++;
                if (row + ptr < N && grid[row][col] == grid[row + ptr][col]) {
                  grid[row][col] *= 2;
                  grid[row + ptr][col] = 0;
                  change = true;
                }
                ptr = 1;
                while (row - ptr >= 0 && grid[row - ptr][col] == 0) ptr++;
                if (ptr != 1) {
                  grid[row - (ptr - 1)][col] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        // 동
        else if (i == 1) {
          for (int row = 0; row < N; row++) {
            for (int col = N - 1; col >= 0; col--) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (col - ptr >= 0 && grid[row][col - ptr] == 0) ptr++;
                if (col - ptr >= 0 && grid[row][col] == grid[row][col - ptr]) {
                  grid[row][col] *= 2;
                  grid[row][col - ptr] = 0;
                  change = true;
                }
                ptr = 1;
                while (col + ptr < N && grid[row][col + ptr] == 0) ptr++;
                if (ptr != 1) {
                  grid[row][col + (ptr - 1)] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        // 남
        else if (i == 2) {
          for (int col = 0; col < N; col++) {
            for (int row = N - 1; row >= 0; row--) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (row - ptr >= 0 && grid[row - ptr][col] == 0) ptr++;
                if (row - ptr >= 0 && grid[row - ptr][col] == grid[row][col]) {
                  grid[row][col] *= 2;
                  grid[row - ptr][col] = 0;
                  change = true;
                }
                ptr = 1;
                while (row + ptr < N && grid[row + ptr][col] == 0) ptr++;
                if (ptr != 1) {
                  grid[row + (ptr - 1)][col] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        // 서
        else {
          for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (col + ptr < N && grid[row][col + ptr] == 0) ptr++;
                if (col + ptr < N && grid[row][col] == grid[row][col + ptr]) {
                  grid[row][col] *= 2;
                  grid[row][col + ptr] = 0;
                  change = true;
                }
                ptr = 1;
                while (col - ptr >= 0 && grid[row][col - ptr] == 0) ptr++;
                if (ptr != 1) {
                  grid[row][col - (ptr - 1)] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        if (change) q.add(new Case(grid, curCase.time + 1));
      }
    }
  }
}
class Case {
  int[][] grid;
  int time;

  Case(int[][] grid, int time) {
    this.grid = grid;
    this.time = time;
  }
}