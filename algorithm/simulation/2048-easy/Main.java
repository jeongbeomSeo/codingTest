import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  private static final int MINUS_INF = Integer.MIN_VALUE;
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

    System.out.println(simulation(grid, N));
  }
  private static int simulation(int[][] grid, int size) {

    Queue<Board> q = new ArrayDeque<>();
    q.add(new Board(grid, 0));

    int max = MINUS_INF;

    while (!q.isEmpty()) {
      Board board = q.poll();

      if (board.count == 5) {
        max = Math.max(max, getMaxValue(board.grid, size));
        continue;
      }

      for (int i = 0; i < 4; i++) {
        Board nextBoard = getNextBoard(board, i, size);

        if (!isEqualGrid(board.grid, nextBoard.grid, size)) {
          q.add(nextBoard);
        }
      }
    }

    return max != MINUS_INF ? max : getMaxValue(grid, size);
  }
  private static boolean isEqualGrid(int[][] grid, int[][] nextGird, int size) {

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[i][j] != nextGird[i][j]) return false;
      }
    }

    return true;
  }
  private static Board getNextBoard(Board board, int direction, int size) {

    int[][] nextGrid = getNextGrid(board.grid, direction, size);

    return new Board(nextGrid, board.count + 1);
  }
  private static int[][] getNextGrid(int[][] grid, int direction, int size) {

    int[][] nextGrid = copyGrid(grid, size);

    switch (direction) {
      case 0:
        moveUp(nextGrid, size);
        break;
      case 1:
        moveRight(nextGrid, size);
        break;
      case 2:
        moveDown(nextGrid, size);
        break;
      case 3:
        moveLeft(nextGrid, size);
        break;
    }

    return nextGrid;
  }
  private static void moveUp(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];

    for (int j = 0; j < size; j++) {
      for (int i = 1; i < size; i++) {
        if (grid[i][j] != 0) {
          int prevRow = i - 1;

          while (prevRow >= 0 && grid[prevRow][j] == 0) prevRow--;

          if (prevRow == -1) {
            grid[prevRow + 1][j] = grid[i][j];
            grid[i][j] = 0;
          } else if (grid[prevRow][j] == grid[i][j] && !isCrush[prevRow][j]) {
            grid[prevRow][j] *= 2;
            grid[i][j] = 0;
            isCrush[prevRow][j] = true;
          } else if (prevRow + 1 != i) {
            grid[prevRow + 1][j] = grid[i][j];
            grid[i][j] = 0;
          }
        }
      }
    }
  }
  private static void moveDown(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];

    for (int j = 0; j < size; j++) {
      for (int i = size - 2; i >= 0; i--) {
        if (grid[i][j] != 0) {
          int prevRow = i + 1;
          while (prevRow <= size - 1 && grid[prevRow][j] == 0) prevRow++;

          if (prevRow == size) {
            grid[size - 1][j] = grid[i][j];
            grid[i][j] = 0;
          } else if (grid[prevRow][j] == grid[i][j] && !isCrush[prevRow][j]) {
            grid[prevRow][j] *= 2;
            grid[i][j] = 0;
            isCrush[prevRow][j] = true;
          } else if (prevRow - 1 != i) {
            grid[prevRow - 1][j] = grid[i][j];
            grid[i][j] = 0;
          }
        }
      }
    }
  }
  private static void moveRight(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = size - 2; j >= 0; j--) {
        int prevCol = j + 1;
        while (prevCol <= size - 1 && grid[i][prevCol] == 0) prevCol++;

        if (prevCol == size) {
          grid[i][prevCol - 1] = grid[i][j];
          grid[i][j] = 0;
        } else if (grid[i][prevCol] == grid[i][j] && !isCrush[i][prevCol]) {
          grid[i][prevCol] *= 2;
          grid[i][j] = 0;
          isCrush[i][prevCol] = true;
        } else if (prevCol - 1 != j) {
          grid[i][prevCol - 1] = grid[i][j];
          grid[i][j] = 0;
        }
      }
    }
  }
  private static void moveLeft(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 1; j < size; j++) {
        int prevCol = j - 1;
        while (prevCol >= 0 && grid[i][prevCol] == 0) prevCol--;

        if (prevCol == -1) {
          grid[i][prevCol + 1] = grid[i][j];
          grid[i][j] = 0;
        } else if (grid[i][prevCol] == grid[i][j] && !isCrush[i][prevCol]) {
          grid[i][prevCol] *= 2;
          grid[i][j] = 0;
          isCrush[i][prevCol] = true;
        } else if (prevCol + 1 != j) {
          grid[i][prevCol + 1] = grid[i][j];
          grid[i][j] = 0;
        }
      }
    }
  }
  private static int getMaxValue(int[][] grid, int size) {
    int max = MINUS_INF;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        max = Math.max(max, grid[i][j]);
      }
    }

    return max;
  }
  private static int[][] copyGrid(int[][] grid, int size) {

    int[][] copy = new int[size][size];
    for (int i = 0; i < size; i++) {
      copy[i] = Arrays.copyOf(grid[i], size);
    }

    return copy;
  }
}
class Board {
  int[][] grid;
  int count;

  Board(int[][] grid, int count) {
    this.grid = grid;
    this.count = count;
  }
}