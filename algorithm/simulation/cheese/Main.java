import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    int cheeseCount = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == 1) cheeseCount++;
      }
    }
    int[] result =simulation(grid, cheeseCount);
    System.out.println(result[0]);
    System.out.println(result[1]);
  }
  static int[] simulation(int[][] grid, int cheeseCount) {

    int time = 1;
    while (true) {
      boolean[][] isVisited = new boolean[N][M];
      boolean end = true;
      int deleteCount = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (!isVisited[i][j] && grid[i][j] == 0) {
            bfs(grid, isVisited, i, j);
          }
        }
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (isVisited[i][j] && grid[i][j] == 1) {
            grid[i][j] = 0;
            deleteCount++;
          }
        }
      }
      if (cheeseCount == deleteCount) break;
      cheeseCount -= deleteCount;
      time++;
    }

    return new int[]{time, cheeseCount};
  }
  static void bfs(int[][] grid, boolean[][] isVisited, int row, int col) {

    Queue<Node> q = new LinkedList<>();
    Queue<Node> cheeseList = new LinkedList<>();
    q.add(new Node(row, col));
    isVisited[row][col] = true;
    boolean isOutSideArea = false;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRow = curNode.row + dr[i];
        int nextCol = curNode.col + dc[i];

        if (isValidIdx(nextRow, nextCol)) {
          if (!isVisited[nextRow][nextCol]) {
            Node nextNode = new Node(nextRow, nextCol);
            if (grid[nextRow][nextCol] == 1) cheeseList.add(nextNode);
            else q.add(nextNode);
            isVisited[nextRow][nextCol] = true;
          }
        }
        else isOutSideArea = true;
      }
    }
    if (!isOutSideArea) {
      while (!cheeseList.isEmpty()) {
        Node curNode = cheeseList.poll();
        isVisited[curNode.row][curNode.col] = false;
      }
    }
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
