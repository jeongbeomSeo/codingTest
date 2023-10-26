import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int MAX_SIZE = 200000;
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    boolean[] isVisited = new boolean[MAX_SIZE];
    System.out.println(bfs(N, M, isVisited));

  }
  static int bfs(int N, int M, boolean[] isVisited) {

    Queue<Node> q = new ArrayDeque<>();

    q.add(new Node(N));
    isVisited[N] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      if (curNode.idx == M) return Math.min(curNode.time, min);
      if (curNode.idx > 100000)
      {
        min = Math.min(min, curNode.idx - 100000 + curNode.time);
        continue;
      }

      if (curNode.idx != 0) {
        if (!isVisited[curNode.idx - 1]) {
          q.add(new Node(curNode.idx - 1, curNode.time + 1));
          isVisited[curNode.idx - 1] = true;
        }
      }
      if (!isVisited[curNode.idx + 1]) {
        q.add(new Node(curNode.idx + 1, curNode.time + 1));
        isVisited[curNode.idx + 1] = true;
      }
      if (!isVisited[curNode.idx * 2]) {
        q.add(new Node(curNode.idx * 2, curNode.time + 1));
        isVisited[curNode.idx * 2] = true;
      }
    }
    return min;
  }
}

class Node {
  int idx;
  int time;

  Node(int idx) {
    this.idx = idx;
    this.time = 0;
  }

  Node(int idx, int time) {
    this.idx = idx;
    this.time = time;
  }

}
