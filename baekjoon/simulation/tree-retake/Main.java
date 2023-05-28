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
  static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] baseGrid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        baseGrid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Deque<Tree> treeDeque = new ArrayDeque<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int age = Integer.parseInt(st.nextToken());

      treeDeque.add(new Tree(row, col, age));
    }

    System.out.println(simulation(treeDeque, baseGrid));
  }
  static int simulation(Deque<Tree> treeDeque, int[][] baseGrid) {

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) Arrays.fill(grid[i], 5);

    while (K-- > 0) {
      Deque<Tree> deadTree = new ArrayDeque<>();
      Deque<Tree> aliveTree = new ArrayDeque<>();
      while (!treeDeque.isEmpty()) {
        Tree tree = treeDeque.poll();

        if (grid[tree.row][tree.col] >= tree.age) {
          grid[tree.row][tree.col] -= tree.age;
          tree.age++;
          aliveTree.add(tree);
        }
        else {
          deadTree.add(tree);
        }
      }

      while (!deadTree.isEmpty()) {
        Tree tree = deadTree.poll();

        grid[tree.row][tree.col] += tree.age / 2;
      }

      while (!aliveTree.isEmpty()) {
        Tree tree = aliveTree.poll();

        if (tree.age % 5 == 0) {
          for (int i = 0; i < 8; i++) {
            int nextRow = tree.row + dr[i];
            int nextCol = tree.col + dc[i];

            if (isValidIdx(nextRow, nextCol)) {
              treeDeque.addFirst(new Tree(nextRow, nextCol, 1));
            }
          }
        }
        treeDeque.addLast(tree);
      }

      addGrid(grid, baseGrid);
    }

    return treeDeque.size();
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  static void addGrid(int[][] grid, int[][] baseGrid) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] += baseGrid[i][j];
      }
    }
  }
}
class Tree{
  int row;
  int col;
  int age;

  Tree(int row, int col, int age) {
    this.row = row;
    this.col = col;
    this.age = age;
  }
}