import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, -1, 0, 1, 0};
  static int[] dc = {0, 0, 1, 0, -1};
  static int N, M;
  static int DELETE = -9;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    boolean isEnd = false;
    int score = 0;
    do {
      boolean[][] isVisited = new boolean[N + 1][N + 1];

      Group resultGroup = null;
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (!isVisited[i][j] && (grid[i][j] != 0 && grid[i][j] != DELETE && grid[i][j] != -1)) {
            Group group = getGroup(grid, i, j, isVisited);
            if (resultGroup == null) resultGroup = group;
            else if (resultGroup.compare(group)) resultGroup = group;
          }
        }
      }

      if (resultGroup == null || resultGroup.blockCount < 2) isEnd = true;
      else {
        score += resultGroup.blockCount * resultGroup.blockCount;
        deleteBlock(grid, resultGroup);
        activeGravity(grid);
        grid = rotateGrid(grid);
        activeGravity(grid);
      }

    } while (!isEnd);

    return score;
  }
  static void activeGravity(int[][] grid) {

    for (int j = 1; j <= N; j++) {
      for (int i = N; i > 1; i--) {
        if (grid[i][j] == DELETE) {
          int nextRow = i - 1;
          while (nextRow >= 1 && grid[nextRow][j] == DELETE) nextRow--;

          if (nextRow >= 1 && grid[nextRow][j] != -1) {
            grid[i][j] = grid[nextRow][j];
            grid[nextRow][j] = DELETE;
          }
        }
      }
    }
  }
  static int[][] rotateGrid(int[][] grid) {

    int[][] newGrid = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        newGrid[i][j] = grid[j][N + 1- i];
      }
    }
    return newGrid;
  }
  static void deleteBlock(int[][] grid, Group group) {
    for (Block block : group.blockList) {
      grid[block.row][block.col] = DELETE;
    }
  }
  static Group getGroup(int[][] grid, int row, int col, boolean[][] isVisited) {

    Queue<Block> q = new ArrayDeque<>();
    q.add(new Block(row, col));
    int color = grid[row][col];
    isVisited[row][col] = true;

    boolean[][] isVisitedThisTurn = new boolean[N + 1][N + 1];
    isVisitedThisTurn[row][col] = true;

    Group group = new Group();
    while (!q.isEmpty()) {
      Block block = q.poll();

      group.add(block, grid[block.row][block.col] == 0);

      for (int i = 1; i <= 4; i++) {
        int nextRow = block.row + dr[i];
        int nextCol = block.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisitedThisTurn[nextRow][nextCol] && (grid[nextRow][nextCol] == color || grid[nextRow][nextCol] == 0)) {
          isVisitedThisTurn[nextRow][nextCol] = true;
          if (grid[nextRow][nextCol] == color) isVisited[nextRow][nextCol] = true;

          q.add(new Block(nextRow, nextCol));
        }
      }
    }

    return group;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
}
class Group {
  Block mainBlock;
  int rainbowBlockCount;
  int blockCount;
  ArrayList<Block> blockList;

  Group() {
    this.mainBlock = null;
    this.rainbowBlockCount = 0;
    this.blockCount = 0;
    this.blockList = new ArrayList<>();
  }

  void add(Block block, boolean isRainbowBlock) {
    if (!isRainbowBlock) {
      if (this.mainBlock == null) this.mainBlock = block;
      else if (this.mainBlock.compare(block)) this.mainBlock = block;
    }
    else this.rainbowBlockCount++;
    this.blockCount++;
    this.blockList.add(block);
  }
  boolean compare(Group o) {
    if (this.blockCount < o.blockCount) return true;
    else if (this.blockCount > o.blockCount) return false;
    else {
      if (this.rainbowBlockCount < o.rainbowBlockCount) return true;
      else if (this.rainbowBlockCount > o.rainbowBlockCount) return false;
      else {
        if (this.mainBlock.row < o.mainBlock.row) return true;
        else if (this.mainBlock.row > o.mainBlock.row) return false;
        else return this.mainBlock.col < o.mainBlock.col;
      }
    }
  }

}
class Block {
  int row;
  int col;
  boolean isRainbowBlock;

  Block(int row, int col) {
    this.row = row;
    this.col = col;
    this.isRainbowBlock = false;
  }

  Block(int row, int col, boolean isRainbowBlock) {
    this.row = row;
    this.col = col;
    this.isRainbowBlock = isRainbowBlock;
  }

  boolean compare(Block o) {
    if (this.row > o.row) return true;
    else if (this.row < o.row) return false;
    else return this.col > o.col;
  }
}