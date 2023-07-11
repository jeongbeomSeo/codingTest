import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M;
  static int DELETE = -9;
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

    boolean activeThisTurn;
    int score = 0;
    do {
      activeThisTurn = false;
      boolean[][] isVisited = new boolean[N][N];

      Group result = null;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != 0 && grid[i][j] != -1 && grid[i][j] != DELETE && !isVisited[i][j]) {
            Group group = bfs(grid, i, j, isVisited);
            if (result == null) result = group;
            else if (result.compare(group) > 0) result = group;
          }
        }
      }

      if (result != null) {
        if (result.totalCount >= 2) {
          deleteGrid(grid, result.list);
          score += (result.totalCount * result.totalCount);

          activeGravity(grid);
          grid = rotateGrid(grid);
          activeGravity(grid);

          activeThisTurn = true;
        }
      }

    } while (activeThisTurn);

    return score;
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
      for (int i = N - 1; i >= 1; i--) {
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
  static void deleteGrid(int[][] grid, ArrayList<Block> list) {
    for (int i = 0; i < list.size(); i++) {
      Block block = list.get(i);
      grid[block.row][block.col] = DELETE;
    }
  }
  static Group bfs(int[][] grid, int initRow, int initCol, boolean[][] isVisited) {

    boolean[][] allIsVisited = new boolean[N][N];

    ArrayList<Block> list = new ArrayList<>();
    int totalCount = 0;
    int rainbowCount = 0;
    int mainColor = grid[initRow][initCol];

    Queue<Block> q = new ArrayDeque<>();
    Block initBlock = new Block(initRow, initCol);
    isVisited[initRow][initCol] = true;
    allIsVisited[initRow][initCol] = true;
    q.add(initBlock);

    Block mainBlock = null;
    while (!q.isEmpty()) {
      Block block = q.poll();

      list.add(block);
      if (grid[block.row][block.col] != 0) {
        if (mainBlock == null) mainBlock = block;
        else if (mainBlock.compare(block) >= 0) mainBlock = block;
      }
      else {
        rainbowCount++;
      }
      totalCount++;

      for (int i = 0; i < 4; i++) {
        int nextRow = block.row + dr[i];
        int nextCol = block.col + dc[i];

        if (isValidIdx(nextRow, nextCol)
                && !allIsVisited[nextRow][nextCol]
                && (grid[nextRow][nextCol] == mainColor || grid[nextRow][nextCol] == 0)) {
          Block nextBlock = new Block(nextRow, nextCol);
          q.add(nextBlock);
          list.add(nextBlock);
          if (grid[nextRow][nextCol] == mainColor) isVisited[nextRow][nextCol] = true;
          allIsVisited[nextRow][nextCol] = true;
        }
      }
    }
    return new Group(list, mainColor, totalCount, rainbowCount, mainBlock);
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Group {
  ArrayList<Block> list;
  int mainColor;
  int totalCount;
  int rainbowBlockCount;
  Block mainBlock;

  Group (ArrayList<Block> list, int mainColor, int totalCount, int rainbowBlockCount, Block mainBlock) {
    this.list = list;
    this.mainColor = mainColor;
    this.totalCount = totalCount;
    this.rainbowBlockCount = rainbowBlockCount;
    this.mainBlock = mainBlock;
  }

  int compare(Group o) {
    if (this.totalCount < o.totalCount) return 1;
    else if (this.totalCount > o.totalCount) return -1;
    else {
      if (this.rainbowBlockCount < o.rainbowBlockCount) return 1;
      else if (this.rainbowBlockCount > o.rainbowBlockCount) return -1;
      else {
        if (this.mainBlock.row < o.mainBlock.row) return 1;
        else if (this.mainBlock.row > o.mainBlock.row) return -1;
        else return o.mainBlock.col - this.mainBlock.col;
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

  int compare(Block o) {
    if (this.row > o.row) return 1;
    else if (this.row < o.row) return -1;
    else return this.col - o.col;
  }
}