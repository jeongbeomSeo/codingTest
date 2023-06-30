import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int DELETE = -9999;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    int point = 0;
    while(true) {
      Result finalResult = null;

      boolean[][] notCheckZeroPointIsVisited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!notCheckZeroPointIsVisited[i][j] && (grid[i][j] != 0 && grid[i][j] != -1 && grid[i][j] != DELETE)) {
            Result result = findBlockGroup(grid, notCheckZeroPointIsVisited, i, j);

            if (result == null) continue;

            if (finalResult == null) finalResult = result;
            else if (finalResult.compare(result)) finalResult = result;
          }
        }
      }

      if (finalResult == null) break;
      point += activeFinalResult(finalResult, grid);

      activeGravity(grid);

      grid = rotateGrid(grid);

      activeGravity(grid);
    }

    return point;
  }
  static int[][] rotateGrid(int[][] grid) {
    int[][] newGrid = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        newGrid[i][j] = grid[j][N - 1 - i];
      }
    }

    return newGrid;
  }
  static void activeGravity(int[][] grid) {

    for (int j = 0; j < N; j++) {
      for (int i = N - 1; i>= 0; i--) {
        if (grid[i][j] == DELETE) {
          int nextRow = i - 1;
          while (nextRow >= 0 && grid[nextRow][j] == DELETE) nextRow--;
          if (nextRow >= 0 && grid[nextRow][j] != -1) {
            grid[i][j] = grid[nextRow][j];
            grid[nextRow][j] = DELETE;
          }
        }
      }
    }
  }
  static int activeFinalResult(Result result, int[][] grid) {

    Queue<Block> q = result.resultQueue;

    while(!q.isEmpty()) {
      Block block = q.poll();

      grid[block.row][block.col] = DELETE;
    }

    return result.blockSize * result.blockSize;
  }
  static Result findBlockGroup(int[][] grid, boolean[][] notCheckZeroPointIsVisited, int row, int col) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Block> q = new ArrayDeque<>();
    Queue<Block> resultQueue = new ArrayDeque<>();
    Block initBlock = new Block(row, col);
    q.add(initBlock);
    resultQueue.add(initBlock);
    int color = grid[row][col];
    int rainbowBlockCount = 0;
    int totalCount = 1;
    isVisited[row][col] = true;
    notCheckZeroPointIsVisited[row][col] = true;

    Block mainBlock = null;
    while (!q.isEmpty()) {
      Block block = q.poll();

      if (grid[block.row][block.col] == color) {
        if (mainBlock == null) mainBlock = block;
        else if (mainBlock.compareBlock(block)) mainBlock = block;
        notCheckZeroPointIsVisited[block.row][block.col] = true;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = block.row + dr[i];
        int nextCol = block.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && (grid[nextRow][nextCol] == color || grid[nextRow][nextCol] == 0) &&
        !isVisited[nextRow][nextCol]) {
          Block nextBlock = new Block(nextRow, nextCol);
          q.add(nextBlock);
          resultQueue.add(nextBlock);
          isVisited[nextRow][nextCol] = true;
          if (grid[nextRow][nextCol] == 0) rainbowBlockCount++;
          totalCount++;
        }
      }
    }

    if (totalCount == 1) return null;

    return new Result(resultQueue, totalCount, rainbowBlockCount, mainBlock);
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Result {
  Queue<Block> resultQueue;
  int blockSize;
  int rainbowBlockSize;
  Block mainBlock;

  Result(Queue<Block> resultQueue, int blockSize, int rainbowBlockSize, Block mainBlock) {
    this.resultQueue = resultQueue;
    this.blockSize = blockSize;
    this.rainbowBlockSize = rainbowBlockSize;
    this.mainBlock = mainBlock;
  }

  boolean compare(Result o) {
    if (this.blockSize < o.blockSize) return true;
    else if (this.blockSize > o.blockSize) return false;
    else {
      if (this.rainbowBlockSize < o.rainbowBlockSize) return true;
      else if (this.rainbowBlockSize > o.rainbowBlockSize) return false;
      else {
        if (this.mainBlock.row < o.mainBlock.row) return true;
        else if (this.mainBlock.row > o.mainBlock.row) return false;
        else {
          return this.mainBlock.col < o.mainBlock.col;
        }
      }
    }
  }
}
class Block {
  int row;
  int col;

  Block(int row, int col) {
    this.row = row;
    this.col = col;
  }

  boolean compareBlock(Block o) {
    if (this.row > o.row) return true;
    else if (this.row < o.row) return false;
    else return this.col > o.col;
  }
}