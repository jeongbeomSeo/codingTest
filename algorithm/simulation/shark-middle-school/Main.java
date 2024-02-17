import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int DELETE = -9;
  private static final int BLACK = -1;
  private static final int RAINBOW = 0;
  private static final int[] DR = {-1, 0, 1, 0};
  private static final int[] DC = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid, N));

  }
  private static int simulation(int[][] grid, int N) {
    int result = 0;

    int turn = 1;
    while (true) {

      boolean[][] isColorVisited = new boolean[N][N];

      Status curStatus = null;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != DELETE && grid[i][j] != BLACK && grid[i][j] != RAINBOW && !isColorVisited[i][j]) {
            Status resultStatus = search(grid, N, isColorVisited, i, j);
            if (resultStatus.totoalCount >= 2) {
              if (curStatus == null) {
                curStatus = resultStatus;
              } else {
                if (curStatus.totoalCount < resultStatus.totoalCount) curStatus = resultStatus;
                else if (curStatus.totoalCount == resultStatus.totoalCount) {
                  if (curStatus.rainbowCount < resultStatus.rainbowCount) {
                    curStatus = resultStatus;
                  } else if (curStatus.rainbowCount == resultStatus.rainbowCount) {
                    if (curStatus.mainBlock.row < resultStatus.mainBlock.row) curStatus = resultStatus;
                    else if (curStatus.mainBlock.row == resultStatus.mainBlock.row)  {
                      if (curStatus.mainBlock.col < resultStatus.mainBlock.col) curStatus = resultStatus;
                    }
                  }
                }
              }
            }
          }
        }
      }

      if (curStatus == null) break;

      result += curStatus.totoalCount * curStatus.totoalCount;
      grid = breakingBlock(grid, curStatus.history, N);
      grid = processPostAction(grid, N);

      turn++;
    }

    return result;
  }
  private static int[][] processPostAction(int[][] grid, int N) {

    int[][] firstGrid = activeGravity(grid, N);
    int[][] secondGrid = activeRotate(firstGrid, N);
    int[][] finalGrid = activeGravity(secondGrid, N);

    return finalGrid;
  }
  private static int[][] breakingBlock(int[][] grid, List<Block> history, int N) {

    int[][] nextGrid = copyGrid(grid, N);

    for (int i = 0; i < history.size(); i++) {
      Block breakBlock = history.get(i);

      nextGrid[breakBlock.row][breakBlock.col] = DELETE;
    }

    return nextGrid;
  }
  private static int[][] activeRotate(int[][] grid, int N) {
    int[][] nextGrid = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        nextGrid[i][j] = grid[j][(N - 1) - i];
      }
    }

    return nextGrid;
  }
  private static int[][] activeGravity(int[][] grid, int N) {
    int[][] nextGrid = copyGrid(grid, N);

    for (int j = 0; j < N; j++) {
      for (int i = N - 1; i > 0; i--) {
        if (nextGrid[i][j] == DELETE) {
          int nextRow = i - 1;
          while(nextRow >= 0 && nextGrid[nextRow][j] == DELETE) nextRow--;

          if (nextRow >= 0 && nextGrid[nextRow][j] != BLACK) {
            nextGrid[i][j] = nextGrid[nextRow][j];
            nextGrid[nextRow][j] = DELETE;
          }
        }
      }
    }

    return nextGrid;
  }
  private static int[][] copyGrid(int[][] grid, int N) {
    int[][] copy = new int[N][N];

    for (int i = 0; i < N; i++) {
      copy[i] = Arrays.copyOf(grid[i], N);
    }

    return copy;
  }
  private static Status search(int[][] grid, int N, boolean[][] isColorVisited, int initRow, int initCol) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Block> q = new ArrayDeque<>();
    int mainColor = grid[initRow][initCol];
    Block initBlock = new Block(initRow, initCol, mainColor);
    q.add(initBlock);
    isVisited[initRow][initCol] = isColorVisited[initRow][initCol] = true;
    Status status = new Status();

    while (!q.isEmpty()) {
      Block curBlock = q.poll();

      status.add(curBlock);

      for (int i = 0; i < 4; i++) {
        int nextRow = curBlock.row + DR[i];
        int nextCol = curBlock.col + DC[i];

        if (isValidPoint(nextRow, nextCol, N)
                && isValidBlock(grid, nextRow, nextCol, mainColor)
                && !isVisited[nextRow][nextCol]) {
          q.add(new Block(nextRow, nextCol, grid[nextRow][nextCol]));
          isVisited[nextRow][nextCol] = true;
          if (grid[nextRow][nextCol] == mainColor) isColorVisited[nextRow][nextCol] = true;
        }
      }
    }

    return status;
  }
  private static boolean isValidBlock(int[][] grid, int row, int col, int mainColor) {
    return grid[row][col] == mainColor || grid[row][col] == RAINBOW;
  }
  private static boolean isValidPoint(int row, int col, int N) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Status {
  List<Block> history;
  Block mainBlock;
  int totoalCount;
  int colorCount;
  int rainbowCount;

  Status() {
    this.history = new ArrayList<>();
    this.mainBlock = null;
    this.totoalCount = 0;
    this.colorCount = 0;
    this.rainbowCount = 0;
  }

  public void add(Block block) {
    this.history.add(block);
    if (this.mainBlock == null) this.mainBlock = block;
    else if (this.mainBlock.color == block.color && !priorityCheck(block)) this.mainBlock = block;
    totoalCount++;
    if (this.mainBlock.color == block.color) colorCount++;
    else this.rainbowCount++;
  }

  public boolean priorityCheck(Block block) {
    if (this.mainBlock.row < block.row) return true;
    else if (this.mainBlock.row > block.row) return false;
    else {
      return this.mainBlock.col < block.col;
    }
  }
}
class Block {
  int row;
  int col;
  int color;

  Block(int row, int col, int color) {
    this.row = row;
    this.col = col;
    this.color = color;
  }
}