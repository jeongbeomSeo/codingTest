import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int[] dr = {-1, 0, 1, 0};
  private static final int[] dc = {0, 1, 0, -1};
  private static final int BLANK = -9;
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
      Queue<Group> groups = new PriorityQueue<>(new GroupComparator());
      boolean[][] isVisited = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (isRegularBlock(grid, i, j) && !isVisited[i][j]) {
            Group resultGroup = search(grid, isVisited, i, j, N);
            if (resultGroup.getBlockSize() >= 2) {
              groups.add(resultGroup);
            }
          }
        }
      }

      if (groups.isEmpty()) break;
      else {
        Group targetGroup = groups.poll();

        score += targetGroup.getBlockSize() * targetGroup.getBlockSize();
        getGridAfterBurst(grid, targetGroup);
        getGridAfterActiveGravity(grid, N);
        grid = getGridAfterRotate(grid, N);
        getGridAfterActiveGravity(grid, N);
      }

    }
    return score;
  }
  private static int[][] getGridAfterRotate(int[][] grid, int N) {
    int[][] nextGrid = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        nextGrid[(N - 1) - j][i] = grid[i][j];
      }
    }

    return nextGrid;
  }
  private static void getGridAfterActiveGravity(int[][] grid, int N) {
    for (int j = 0; j < N; j++) {
      for (int i = N - 1; i > 0; i--) {
        if (grid[i][j] == BLANK) {
          int nextRow = i - 1;
          while (nextRow >= 0 && grid[nextRow][j] == BLANK) nextRow--;
          if (nextRow >= 0 && grid[nextRow][j] != -1) {
            grid[i][j] = grid[nextRow][j];
            grid[nextRow][j] = BLANK;
          }
        }
      }
    }
  }
  private static void getGridAfterBurst(int[][] grid, Group group) {

    while (!group.getBuffer().isEmpty()) {
      Block curBlock = group.getBuffer().poll();

      grid[curBlock.row][curBlock.col] = BLANK;
    }

  }

  private static Group search(int[][] grid, boolean[][] isVisited, int initRow, int initCol, int N) {

    int mainColor = grid[initRow][initCol];

    Block initBlock = new Block(initRow, initCol, mainColor);

    Queue<Block> buffer = new ArrayDeque<>();
    buffer.add(initBlock);
    isVisited[initBlock.row][initBlock.col] = true;
    Group group = new Group(initBlock);

    Queue<Block> rainbowBlockQueue = new ArrayDeque<>();

    while (!buffer.isEmpty()) {
      Block curBlock = buffer.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curBlock.row + dr[i];
        int nextCol = curBlock.col + dc[i];

        if (isValidPoint(nextRow, nextCol, N)
                && !isVisited[nextRow][nextCol]
                && isValidBlockInGroup(grid, nextRow, nextCol, mainColor)) {
          Block nextBlock = new Block(nextRow, nextCol, grid[nextRow][nextCol]);
          group.add(nextBlock);
          buffer.add(nextBlock);
          isVisited[nextRow][nextCol] = true;

          if (nextBlock.color == 0) rainbowBlockQueue.add(nextBlock);
        }
      }
    }

    while (!rainbowBlockQueue.isEmpty()) {
      Block curBlock = rainbowBlockQueue.poll();

      isVisited[curBlock.row][curBlock.col] = false;
    }

    return group;
  }
  private static boolean isValidBlockInGroup(int[][] grid, int row, int col, int mainColor) {
    return grid[row][col] == 0 || grid[row][col] == mainColor;
  }
  private static boolean isValidPoint(int row, int col, int N) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  private static boolean isRegularBlock(int[][] grid, int row, int col) {
    return grid[row][col] != 0 && grid[row][col] != -1 && grid[row][col] != BLANK;
  }
}
class GroupComparator implements Comparator<Group> {
  @Override
  public int compare(Group o1, Group o2) {
    // 전부 내림차순
    if (o1.getBlockSize() != o2.getBlockSize()) return -(o1.getBlockSize() - o2.getBlockSize());
    else {
      if (o1.getRainbowBlockSize() != o2.getRainbowBlockSize()) return -(o1.getRainbowBlockSize() - o2.getRainbowBlockSize());
      else {
        if (o1.getMainBlock().row != o2.getMainBlock().row) return -(o1.getMainBlock().row - o2.getMainBlock().row);
        else {
          return -(o1.getMainBlock().col - o2.getMainBlock().col);
        }
      }
    }
  }
}
class Group {
  private Queue<Block> buffer;
  private Block mainBlock;
  private int mainColor;
  private int rainbowBlockCount;

  Group(Block initBlock) {
    this.mainBlock = initBlock;
    this.buffer = new ArrayDeque<>();
    add(initBlock);

    this.mainColor = initBlock.color;
  }

  public void add(Block block) {
    buffer.add(block);

    if (block.color != 0) {
      if (isMainBlock(block)) {
        this.mainBlock = block;
      }
    } else {
      rainbowBlockCount++;
    }
  }

  public Queue<Block> getBuffer() {
    return this.buffer;
  }
  public int getBlockSize() {
    return this.buffer.size();
  }

  public int getRainbowBlockSize() {
    return this.rainbowBlockCount;
  }
  public Block getMainBlock() {
    return this.mainBlock;
  }
  private boolean isMainBlock(Block block) {
    if (block.row < this.mainBlock.row) return true;
    else if (block.row > this.mainBlock.row) return false;
    else return block.col < this.mainBlock.col;
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