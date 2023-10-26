import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    int cheese_amount = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 1) cheese_amount++;
      }
    }

    System.out.println(simulation(grid, cheese_amount));

  }

  static int simulation(int[][] grid, int cheese_amount) {

    int time = 0;
    while (true) {
      boolean[][] isVisited = new boolean[N][M];
      int[][] finalCount = new int[N][M];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (!isVisited[i][j] && grid[i][j] == 0) {
            int[][] count = bfs(grid, isVisited, i, j);
            if (count != null) {
              for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                  finalCount[row][col] += count[row][col];
                }
              }
            }
          }
        }
      }

      int counter = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (grid[i][j] == 1 && finalCount[i][j] >= 2) {
            grid[i][j] = 0;
            counter++;
          }
        }
      }
      cheese_amount -= counter;
      time++;

      if (cheese_amount == 0) break;
    }
    return time;
  }
  static int[][] bfs(int[][] grid, boolean[][] isVisited, int row, int col) {

    int[][] count = new int[N][M];
    Queue<Node> q = new LinkedList<>();
    Queue<Node> cheese = new LinkedList<>();
    q.add(new Node(row, col));
    isVisited[row][col] = true;

    boolean canReachOutside = false;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curNode.row + dr[i];
        int nextCol = curNode.col + dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          if (grid[nextRow][nextCol] == 0){
            if (!isVisited[nextRow][nextCol])
              q.add(new Node(nextRow, nextCol));
          }
          else {
            if (!isVisited[nextRow][nextCol]) cheese.add(new Node(nextRow, nextCol));
            count[nextRow][nextCol]++;
          }
          isVisited[nextRow][nextCol] = true;
        }
        else canReachOutside = true;
      }
    }

    if (!canReachOutside) {
      while (!cheese.isEmpty()) {
        count = null;
        Node curNode = cheese.poll();
        isVisited[curNode.row][curNode.col] = false;
      }
    }

    return count;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < M;
  }
}

class Node {
  int row;
  int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
