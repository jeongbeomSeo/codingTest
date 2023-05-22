import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N, M, K;
  static int[] dr = {-1, 0, 1};
  static int[] dc = {-1, 0, 1};
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

    Queue<Tree> pq = new PriorityQueue<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;
      int age = Integer.parseInt(st.nextToken());

      pq.add(new Tree(x, y, age));
    }

    System.out.println(simulation(pq, baseGrid));
  }
  static int simulation(Queue<Tree> pq, int[][] baseGrid) {
    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) grid[i][j] = 5;

    while (K-- > 0) {
      Queue<Tree> nextPQ = new PriorityQueue<>();

      int[][] deadTree = new int[N][N];
      // 봄
      while (!pq.isEmpty()) {
        Tree tree = pq.poll();

        if (grid[tree.row][tree.col] >= tree.age) {
          grid[tree.row][tree.col] -= tree.age;
          tree.age++;
          nextPQ.add(tree);
        }
        else {
          deadTree[tree.row][tree.col] += tree.age / 2;
        }
      }
      // 여름
      plusGrid(grid, deadTree);
      // 가을
      while (!nextPQ.isEmpty()) {
        Tree tree = nextPQ.poll();

        if (tree.age % 5 == 0) {
          for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              if (i != 1 || j != 1) {
                int nextRow = tree.row + dr[i];
                int nextCol = tree.col + dc[j];
                if (isValidIdx(nextRow, nextCol)) {
                  pq.add(new Tree(nextRow, nextCol, 1));
                }
              }
            }
          }
        }
        pq.add(tree);
      }
      // 겨울
      plusGrid(grid, baseGrid);
    }
    return pq.size();
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  static void plusGrid(int[][] grid, int[][] baseGrid) {

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] += baseGrid[i][j];
      }
    }
  }
}
class Tree implements Comparable<Tree>{
  int row;
  int col;
  int age;

  Tree(int row, int col, int age) {
    this.row = row;
    this.col = col;
    this.age = age;
  }


  @Override
  public int compareTo(Tree o) {
    return this.age - o.age;
  }
}