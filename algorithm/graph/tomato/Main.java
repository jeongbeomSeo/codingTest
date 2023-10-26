import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {-1, 0, 1, 0};
  static int[] ay = {0, -1, 0, 1};
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    int[][] box = new int[N][M];
    Queue<Node> q = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int n = Integer.parseInt(st.nextToken());
        box[i][j] = n;
        if (n == 1) q.add(new Node(i, j));
      }
    }

    System.out.println(bfs(box, q));
  }
  static int bfs(int[][] box, Queue<Node> q) {

    int max = 0;
    while (!q.isEmpty()) {
      Node curNode = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextI = curNode.i + ay[i];
        int nextJ = curNode.j + ax[i];

        if (checkIdx(nextI, nextJ, N, M) && box[nextI][nextJ] == 0) {
          q.add(new Node(nextI, nextJ, curNode.day + 1));
          box[nextI][nextJ] = 1;
        }
      }
      max = Math.max(max, curNode.day);
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (box[i][j] == 0) return -1;
      }
    }
    return max;
  }
  static boolean checkIdx(int i, int j, int N, int M) {
    if (i < 0 || j < 0 || i > N - 1 || j > M - 1) {
      return false;
    }
    return true;
  }
}

class Node {
  int i;
  int j;
  int day;
  
  Node(int i, int j) {
    this.i = i;
    this.j = j;
    day = 0;
  }

  Node(int i, int j, int day) {
    this.i = i;
    this.j = j;
    this.day = day;
  }
}
