import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final long INF = Long.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<List<Node>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(n1).add(new Node(n2, cost));
    }

    long[] dist = initDistTable(N);

    spfa(graph, dist, N);

  }
  private static long[] initDistTable(int N) {

    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);

    dist[1] = 0;

    return dist;
  }
  private static void spfa(List<List<Node>> graph, long[] dist, int N) {

    int[] visitCount = new int[N + 1];
    boolean[] isInQueue = new boolean[N + 1];
    Queue<Integer> q = new ArrayDeque<>();

    q.add(1);
    visitCount[1]++;
    isInQueue[1] = true;

    while (!q.isEmpty()) {
      int curIdx = q.poll();
      isInQueue[curIdx] = false;

      for (int i = 0; i < graph.get(curIdx).size(); i++) {
        Node nxtNode = graph.get(curIdx).get(i);

        if (dist[nxtNode.idx] > dist[curIdx] + nxtNode.cost) {
          dist[nxtNode.idx] = dist[curIdx] + nxtNode.cost;

          if (!isInQueue[nxtNode.idx]) {
            q.add(nxtNode.idx);
            isInQueue[nxtNode.idx] = true;
            if (++visitCount[nxtNode.idx] == N) {
              System.out.println(-1);
              return;
            }
          }
        }
      }
    }

    for (int i = 2; i < N + 1; i++) {
      System.out.println(dist[i] != INF ? dist[i] : -1);
    }

  }
}
class Node {
  int idx;
  int cost;

  Node (int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}