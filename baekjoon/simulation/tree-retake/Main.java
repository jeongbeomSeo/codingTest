import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] dc = {-1, 0 ,1, -1, 1, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] addGrid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        addGrid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Deque<Tree> trees = new ArrayDeque<>();
    Tree[] treesForSort = new Tree[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;
      int age = Integer.parseInt(st.nextToken());

      treesForSort[i] = new Tree(x, y, age);
    }

    Arrays.sort(treesForSort);
    for (int i = 0; i < M; i++) trees.add(treesForSort[i]);
    treesForSort = null;

    System.out.println(simulation(trees, addGrid));
  }
  static int simulation(Deque<Tree> trees, int[][] addGrid) {

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) grid[i][j] = 5;

    while (K-- > 0) {
      active(trees, grid, addGrid);
    }
    return trees.size();
  }
  static void active(Deque<Tree> trees, int[][] grid, int[][] addGrid) {

    Deque<Tree> deadTree = new ArrayDeque<>();
    Deque<Tree> nextTree = new ArrayDeque<>();
    while (!trees.isEmpty()) {
      Tree tree = trees.poll();
      if (tree.age <= grid[tree.row][tree.col]) {
        grid[tree.row][tree.col] -= tree.age;
        tree.age++;
        nextTree.add(tree);
      }
      else deadTree.add(tree);
    }

    while (!deadTree.isEmpty()) {
      Tree tree = deadTree.poll();

      grid[tree.row][tree.col] += tree.age / 2;
    }

    while (!nextTree.isEmpty()) {
      Tree tree = nextTree.poll();

      if (tree.age % 5 == 0) {
        for (int i = 0; i < 8; i++) {
          int nextRow = tree.row + dr[i];
          int nextCol = tree.col + dc[i];
          if (isValidIdx(nextRow, nextCol)) {
            trees.addFirst(new Tree(nextRow, nextCol, 1));
          }
        }
      }
      trees.addLast(tree);
    }

    initGrid(grid, addGrid);
  }
  static void initGrid(int[][] grid, int[][] addGrid) {

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] += addGrid[i][j];
      }
    }

  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}

class Tree implements Comparable<Tree>{
  int row;
  int col;
  int age;

  Tree (int row, int col, int age) {
    this.row = row;
    this.col = col;
    this.age = age;
  }

  @Override
  public int compareTo(Tree o) {
    return this.age - o.age;
  }
}