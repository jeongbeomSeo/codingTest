import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 복, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, L, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

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
    int time = 0;

    boolean[][] lookChange = new boolean[N][N];
    while (true) {
      boolean action = false;
      boolean[][] isVisited = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!isVisited[i][j]) {
            if (time == 0 && bfs(grid, isVisited, lookChange, i, j)) action = true;
            else if (lookChange[i][j]) {
              if (bfs(grid, isVisited, lookChange, i, j)) action = true;
            }
          }
        }
      }
      if (!action) break;
      time++;
    }

    return time;
  }

  static boolean bfs(int[][] grid, boolean[][] isVisited, boolean[][] lookChange, int row, int col) {
    Queue<Node> q = new ArrayDeque<>();
    q.add(new Node(row, col));
    isVisited[row][col] = true;

    Node[] nodeList = new Node[N * N];
    int sum = 0;
    int size = 0;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      nodeList[size++] = curNode;
      sum += grid[curNode.row][curNode.col];

      for (int i = 0; i < 4; i++) {
        int nextRow = curNode.row + dr[i];
        int nextCol = curNode.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol]) {
          int diff = Math.abs(grid[curNode.row][curNode.col] - grid[nextRow][nextCol]);
          if (diff >= L && diff <= R) {
            q.add(new Node(nextRow, nextCol));
            isVisited[nextRow][nextCol] = true;
          }
        }
      }
    }

    if (size > 1) {
      int average = sum / size;
      for (int i = 0; i < size; i++) {
        Node curNode = nodeList[i];
        lookChange[curNode.row][curNode.col] = true;
        grid[curNode.row][curNode.col] = average;
      }
      return true;
    }
    return false;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= N - 1 && col <= N - 1;
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