import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int countBlindSpot = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    // cctv 번호, cctv Row, cctv Col
    int[][] cctvList = new int[8][3];
    int size = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int n = Integer.parseInt(st.nextToken());
        grid[i][j] = n;
        if (n != 0 && n != 6) {
          cctvList[size][0] = n;
          cctvList[size][1] = i;
          cctvList[size][2] = j;
          size++;
        }
      }
    }
    System.out.println(simulation(grid, cctvList, size));
  }
  static int simulation(int[][] baseGrid, int[][] cctvList, int size) {

    backTracking(baseGrid, cctvList, 0, size);

    return countBlindSpot;

  }
  static void backTracking(int[][] baseGrid, int[][] cctvList, int ptr, int size) {

    if (ptr == size) {
      int count = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (baseGrid[i][j] == 0) count++;
        }
      }
      countBlindSpot = Math.min(countBlindSpot, count);
    }
    else {
      int curCCTVNumber = cctvList[ptr][0];

      if (curCCTVNumber == 5) {
        int[][] grid = new int[N][M];
        initGrid(grid, baseGrid);

        int row = cctvList[ptr][1];
        int col = cctvList[ptr][2];

        // 북
        for (int i = 1; row - i >= 0; i++) {
          if (grid[row - i][col] == 6) break;
          grid[row - i][col] = -1;
        }
        // 동
        for (int i = 1; col + i < M; i++) {
          if (grid[row][col + i] == 6) break;
          grid[row][col + i] = -1;
        }
        // 남
        for (int i = 1; row + i < N; i++) {
          if (grid[row + i][col] == 6) break;
          grid[row + i][col] = -1;
        }
        // 서
        for (int i = 1; col - i >= 0; i++) {
          if (grid[row][col - i] == 6) break;
          grid[row][col - i] = -1;
        }

        backTracking(grid, cctvList, ptr + 1, size);
      }

      if (curCCTVNumber == 1) {
        for (int direction = 0; direction < 4; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          if (direction == 0) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
          }

          else if (direction == 1) {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
          }

          else if (direction == 2) {
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }

          else {
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

      if (curCCTVNumber == 2) {
        for (int direction = 0; direction < 2; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          // row
          if (direction == 0) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }
          // col
          else {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

      if (curCCTVNumber == 3) {
        for (int direction = 0; direction < 4; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          // 북, 동
          if (direction == 0) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
          }

          // 동, 남
          else if (direction == 1) {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }

          // 남, 서
          else if (direction == 2) {
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }

          // 서, 북
          else {
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

      if (curCCTVNumber == 4) {
        for (int direction = 0; direction < 4; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          // 북 제외
          if (direction == 0) {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }

          // 동 제외
          else if (direction == 1) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }

          }

          // 남 제외
          else if (direction == 2) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }

          }

          // 서 제외
          else {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

    }
  }
  static void initGrid(int[][] grid, int[][] baseGrid) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        grid[i][j] = baseGrid[i][j];
      }
    }
  }
}
