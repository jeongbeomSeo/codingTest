import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int time = 1;
    while (true) {
      int N = Integer.parseInt(br.readLine());

      if (N == 0) break;

      int[][] grid = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          grid[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int[][] table = initDistTable(grid, N);

      bw.write("Problem " + time++ + ": " + dijkstra(grid, table, N) + "\n");
    }
    bw.flush();
    bw.close();
  }
  private static int dijkstra(int[][] grid, int[][] table, int N) {

    Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

    pq.add(new Node(0, 0, 0));

    while(!pq.isEmpty()) {
      Node curNode = pq.poll();

      if (table[curNode.row][curNode.col] < curNode.cost) continue;

      if (isEndPoint(curNode.row, curNode.col, N)) return table[curNode.row][curNode.col];

      for (int i = 0; i < 4; i++) {
        int nxtRow = curNode.row + dr[i];
        int nxtCol = curNode.col + dc[i];

        if (isValidIdx(nxtRow, nxtCol, N) &&
                table[nxtRow][nxtCol] > table[curNode.row][curNode.col] + grid[nxtRow][nxtCol]) {
          table[nxtRow][nxtCol] = table[curNode.row][curNode.col] + grid[nxtRow][nxtCol];

          pq.add(new Node(nxtRow, nxtCol, table[nxtRow][nxtCol]));
        }
      }
    }

    return -1;
  }
  private static boolean isValidIdx(int row, int col, int N) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  private static boolean isEndPoint(int curRow, int curCol, int N) {
    return curRow == N - 1 && curCol == N - 1;
  }
  private static int[][] initDistTable(int[][] grid, int N) {

    int[][] dist = new int[N][N];
    for (int i = 0; i < N; i++) {
      Arrays.fill(dist[i], INF);
    }

    dist[0][0] = grid[0][0];

    return dist;
  }
}
class Node {
  int row;
  int col;
  int cost;

  Node(int row, int col, int cost) {
    this.row = row;
    this.col = col;
    this.cost = cost;
  }
}