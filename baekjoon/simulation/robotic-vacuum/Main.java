import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int M;
  static int count;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];

    st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int direction = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    cleaner(grid, row, col, direction);

    System.out.println(count);
  }
  static void cleaner(int[][] grid, int row, int col, int direction) {

    if (grid[row][col] == 0) {
      grid[row][col] = -1;
      count++;
    }

    while (true) {
      boolean moveForward = false;
      for (int i = 0; i < 4; i++) {
        int nextRow = row;
        int nextCol = col;
        switch (direction) {
          case 0:
            nextRow = row - 1;
            break;
          case 1:
            nextCol = col + 1;
            break;
          case 2:
            nextRow = row + 1;
            break;
          case 3:
            nextCol = col - 1;
            break;
        }
        if (grid[nextRow][nextCol] == 0) {
          grid[nextRow][nextCol] = -1;
          count++;
          row = nextRow;
          col = nextCol;
          moveForward = true;
          break;
        }
        if (direction != 0) direction--;
        else direction = 3;
      }

      if (!moveForward)  {
        int nextRow = row;
        int nextCol = col;
        switch (direction) {
          case 0:
            nextRow = row + 1;
            break;
          case 1:
            nextCol = col - 1;
            break;
          case 2:
            nextRow = row - 1;
            break;
          case 3:
            nextCol = col + 1;
            break;
        }
        if (grid[nextRow][nextCol] != 1) {
            row = nextRow;
            col = nextCol;
        }
        else break;
      }
    }
  }
}