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

    int score = 0;
    while (true) {

      boolean[][] isVisitedOnlyColor = new boolean[N][N];
      Group resultGroup = null;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (grid[i][j] != BLACK && grid[i][j] != RAINBOW && grid[i][j] != DELETE && !isVisitedOnlyColor[i][j]) {
            Group result = search(grid, N, isVisitedOnlyColor, i, j);

            if (result.colorBlockCount >= 2) {
              if (resultGroup == null) resultGroup = result;
              else {
                resultGroup = priorityCheckGroup(resultGroup, result);
              }
            }
          }
        }
      }

      if (resultGroup == null) break;
      score += resultGroup.totalBlockCount * resultGroup.totalBlockCount;
      grid = processPostAction(grid, N, resultGroup.history);
    }

    return score;
  }
  private static int[][] processPostAction(int[][] grid, int N, List<Block> history) {

    int[][] nextGrid = deleteBlock(grid, N, history);
    nextGrid = activeGravity(nextGrid, N);
    nextGrid = rotateGrid(nextGrid, N);
    nextGrid = activeGravity(nextGrid, N);

    return nextGrid;
  }
  private static int[][] rotateGrid(int[][] grid, int N) {
    int[][] nextGrid = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        nextGrid[i][j] = grid[j][(N - 1) - i];
      }
    }

    return nextGrid;
  }
  private static int[][] activeGravity(int[][] grid, int N) {

    int[][] nextGrid = new int[N][N];

    for (int j = 0; j < N; j++) {
      for (int i = N - 1; i >= 0; i--) {
        if (grid[i][j] != BLACK) {
          if (grid[i][j] == DELETE) {
            int nextRow = i - 1;
            while (nextRow >= 0 && grid[nextRow][j] == DELETE) nextRow--;

            if (nextRow >= 0) {
              if (grid[nextRow][j] != BLACK) {
                nextGrid[i][j] = grid[nextRow][j];
                grid[nextRow][j] = DELETE;
              } else {
                nextGrid[i][j] = DELETE;
              }
            } else {
              nextGrid[i][j] = DELETE;
            }
          } else {
            nextGrid[i][j] = grid[i][j];
          }
        } else {
          nextGrid[i][j] = BLACK;
        }
      }
    }
    return nextGrid;
  }
  private static int[][] deleteBlock(int[][] grid, int N, List<Block> history) {
    int[][] copyGrid = copyGrid(grid, N);
 
    for (int i = 0; i < history.size(); i++) {
      Block deleteblock = history.get(i);

      copyGrid[deleteblock.row][deleteblock.col] = DELETE;
    }
    
    return copyGrid;
  }
  private static int[][] copyGrid(int[][] grid, int N) {
    int[][] copy = new int[N][N];
    
    for (int i = 0; i < N; i++) {
      copy[i] = Arrays.copyOf(grid[i], N);
    }
    
    return copy;
  }
  
  private static Group search(int[][] grid, int N, boolean[][] isVisitedOnlyColor, int initRow, int initCol) {

    boolean[][] isVisited = new boolean[N][N];

    Queue<Block> q = new ArrayDeque<>();
    int mainColor = grid[initRow][initCol];
    Block initBlock = new Block(initRow, initCol, mainColor);
    q.add(new Block(initRow, initCol, mainColor));
    isVisitedOnlyColor[initRow][initCol] = isVisited[initRow][initCol] = true;

    Group finalGroup = new Group();
    while (!q.isEmpty()) {
      Block curBlock = q.poll();

      finalGroup.addBlock(curBlock);

      for (int i = 0; i < 4; i++) {
        int nxtRow = curBlock.row + DR[i];
        int nxtCol = curBlock.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol, N) && !isVisited[nxtRow][nxtCol]
                && isValidBlock(grid, nxtRow, nxtCol, mainColor)) {
          q.add(new Block(nxtRow, nxtCol, grid[nxtRow][nxtCol]));
          if (grid[nxtRow][nxtCol] == mainColor) {
            isVisitedOnlyColor[nxtRow][nxtCol] = true;
          }
          isVisited[nxtRow][nxtCol] = true;
        }
      }
    }

    return finalGroup;
  }
  private static boolean isValidBlock(int[][] grid, int row, int col, int mainColor) {
    return grid[row][col] == RAINBOW || grid[row][col] == mainColor;
  }
  private static boolean isValidPoint(int row, int col, int N) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  private static Group priorityCheckGroup(Group group, Group comp) {
    int groupRainbowBlockCount = group.totalBlockCount - group.colorBlockCount;
    int compRainBowBlockCount = comp.totalBlockCount - comp.colorBlockCount;

    if (groupRainbowBlockCount > compRainBowBlockCount) return group;
    else if (groupRainbowBlockCount < compRainBowBlockCount) return comp;
    else {
      if (group.mainBlock.row < comp.mainBlock.row) return group;
      else if (group.mainBlock.col > comp.mainBlock.col) return comp;
      else {
        if (group.mainBlock.col < comp.mainBlock.col) return group;
        else return comp;
      }
    }
  }
}
class Group {
  private static final int RAINBOW = 0;
  Block mainBlock;
  int totalBlockCount;
  int colorBlockCount;
  List<Block> history = new ArrayList<>();

  Group() {
    this.totalBlockCount = 0;
    this.colorBlockCount = 0;
  }

  public void addBlock(Block block) {
    if (this.mainBlock == null) {
      this.mainBlock = block;
      this.colorBlockCount++;
    } else if (block.color != RAINBOW) {
      this.mainBlock = priorityCheckBlock(this.mainBlock, block);
      this.colorBlockCount++;
    }
    this.history.add(block);
    totalBlockCount++;
  }

  private static Block priorityCheckBlock(Block prevMainBlcok, Block newBlock) {
    if (newBlock.color == 0) return prevMainBlcok;
    else {
      if (prevMainBlcok.row < newBlock.row)  return prevMainBlcok;
      else if (prevMainBlcok.row > newBlock.row) return newBlock;
      else {
        if (prevMainBlcok.col < newBlock.col) return prevMainBlcok;
        else return newBlock;
      }
    }
  }
}
class Block {
  int row;
  int col;
  int color;

  Block (int row, int col, int color) {
    this.row = row;
    this.col = col;
    this.color = color;
  }
}
