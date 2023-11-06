import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        grid[i][j] = str.charAt(j) - '0';
      }
    }

    char[][] result = queryResult(grid);

    for (int i = 0; i < N; i++) {
      bw.write(String.valueOf(result[i]) + "\n");
    }
    bw.flush();
    bw.close();
  }
  private static char[][] queryResult(int[][] grid) {

    int num = 2;
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 0) {
          int count = bfs(grid, i, j, num);
          map.put(num, count);
          num++;
        }
      }
    }

    char[][] charGrid = new char[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 1) {
          Set<Integer> group = new HashSet<>();

          int sum = 1;
          for (int k = 0; k < 4; k++) {
            int nxtRow = i + dr[k];
            int nxtCol = j + dc[k];

            if (boundaryCheck(nxtRow, nxtCol) && grid[nxtRow][nxtCol] != 1) {
              group.add(grid[nxtRow][nxtCol]);
            }
          }
          for (int idx : group) {
            sum += map.get(idx);
          }
          charGrid[i][j] = (char)((sum % 10) + '0');
        } else {
          charGrid[i][j] = '0';
        }
      }
    }
    return  charGrid;
  }
  private static int bfs(int[][] grid, int row, int col, int num) {

    Queue<Node> q = new ArrayDeque<>();
    q.add(new Node(row, col));
    grid[row][col] = num;
    int count = 1;

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        int nxtRow = curNode.row + dr[i];
        int nxtCol = curNode.col + dc[i];

        if (boundaryCheck(nxtRow, nxtCol) && grid[nxtRow][nxtCol] == 0) {
          q.add(new Node(nxtRow, nxtCol));
          grid[nxtRow][nxtCol] = num;
          count++;
        }
      }
    }
    return count;
  }
  private static boolean boundaryCheck(int row, int col) {
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