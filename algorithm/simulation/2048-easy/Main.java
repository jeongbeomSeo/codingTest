import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  private static int result;
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

    System.out.println(query(grid, N));
  }
  private static int query(int[][] grid, int N) {
    result = 0;

    dfs(grid, N, 0);

    return result;
  }
  private static void dfs(int[][] curGrid, int N, int count) {

    if (count == 5) {
      result = Math.max(result, getBiggestNumber(curGrid, N));
      return;
    }

    for (int i = 0; i < 4; i++) {
      int[][] newGrid = copy(curGrid, N);

      moveGrid(newGrid, N, i);
      dfs(newGrid, N, count + 1);
    }
  }
  private static void moveGrid(int[][] grid, int N, int direction) {

    switch (direction) {
      case 0:
        moveLeft(grid, N);
        break;
      case 1:
        moveUp(grid, N);
        break;
      case 2:
        moveRight(grid, N);
        break;
      case 3:
        moveDown(grid, N);
        break;
    }
  }
  private static void moveLeft(int[][] grid, int N) {
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N - 1; col++) {
        if (grid[row][col] == 0) {
          int nxtCol = col + 1;
          while (nxtCol < N && grid[row][nxtCol] == 0) nxtCol++;

          if (nxtCol < N) {
            grid[row][col] = grid[row][nxtCol];
            grid[row][nxtCol] = 0;
          }
        }

        if (grid[row][col] != 0) {
          int nxtCol = col + 1;
          while (nxtCol < N && grid[row][nxtCol] == 0) nxtCol++;

          if (nxtCol < N && grid[row][col] == grid[row][nxtCol]) {
            grid[row][col] *= 2;
            grid[row][nxtCol] = 0;
          }
        }
      }
    }
  }
  private static void moveRight(int[][] grid, int N) {
    for (int row = 0; row < N; row++) {
      for (int col = N - 1; col > 0; col--) {
        if (grid[row][col] == 0) {
          int nxtCol = col - 1;
          while (nxtCol >= 0 && grid[row][nxtCol] == 0) nxtCol--;

          if (nxtCol >= 0) {
            grid[row][col] = grid[row][nxtCol];
            grid[row][nxtCol] = 0;
          }
        }

        if (grid[row][col] != 0) {
          int nxtCol = col - 1;
          while (nxtCol >= 0 && grid[row][nxtCol] == 0) nxtCol--;

          if (nxtCol >= 0 && grid[row][col] == grid[row][nxtCol]) {
            grid[row][col] *= 2;
            grid[row][nxtCol] = 0;
          }
        }
      }
    }
  }
  private static void moveDown(int[][] grid, int N) {
    for (int col = 0; col < N; col++) {
      for (int row = N - 1; row > 0; row--) {
        if (grid[row][col] == 0) {
          int nxtRow = row - 1;
          while (nxtRow >= 0 && grid[nxtRow][col] == 0) nxtRow--;

          if (nxtRow >= 0) {
            grid[row][col] = grid[nxtRow][col];
            grid[nxtRow][col] = 0;
          }
        }

        if (grid[row][col] != 0) {
          int nxtRow = row - 1;
          while (nxtRow >= 0 && grid[nxtRow][col] == 0) nxtRow--;

          if (nxtRow >= 0 && grid[row][col] == grid[nxtRow][col]) {
            grid[row][col] *= 2;
            grid[nxtRow][col] = 0;
          }
        }
      }
    }
  }
  private static void moveUp(int[][] grid, int N) {
    for (int col = 0; col < N; col++) {
      for (int row = 0; row < N - 1; row++) {
        // 빈 공간일 경우(숫자 0) row가 큰 공간 중에 숫자가 0이 아닌 곳 탐색 후 있다면 옮겨주기
        if (grid[row][col] == 0) {
          int nxtRow = row + 1;
          while (nxtRow < N && grid[nxtRow][col] == 0) nxtRow++;

          if (nxtRow < N) {
            grid[row][col] = grid[nxtRow][col];
            grid[nxtRow][col] = 0;
          }
        }
        // 현재 숫자가 0이 아닐 경우 row가 큰 공간 탐색 하면서 가장 먼저 나온 0이 아닌 숫자와 비교 만약 같다면 합쳐주기
        if (grid[row][col] != 0) {
          int nxtRow = row + 1;
          while (nxtRow < N && grid[nxtRow][col] == 0) nxtRow++;

          if (nxtRow < N && grid[row][col] == grid[nxtRow][col]) {
            grid[row][col] *= 2;
            grid[nxtRow][col] = 0;
          }
        }
      }
    }
  }
  private static int[][] copy(int[][] grid, int N) {
    int[][] copy = new int[N][N];

    for (int i = 0; i < N; i++) {
      copy[i] = Arrays.copyOf(grid[i], N);
    }

    return copy;
  }
  private static int getBiggestNumber(int[][] grid, int N) {

    int num = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        num = Math.max(num, grid[i][j]);
      }
    }

    return num;
  }
}