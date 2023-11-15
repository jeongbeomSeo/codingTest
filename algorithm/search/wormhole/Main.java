import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    for (int test = 0; test < tc; test++) {
      st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      List<List<Node>> graph = new ArrayList<>();
      List<List<Node>> wormhole = new ArrayList<>();
      for (int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
        wormhole.add(new ArrayList<>());
      }

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(n1).add(new Node(n2, cost));
        graph.get(n2).add(new Node(n1, cost));
      }

      for (int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        wormhole.get(src).add(new Node(dst, -cost));
      }
      long[] dist = initDistTable(N);

      boolean hasInfiniteLoop = false;
      for (int i = 1; i < N + 1; i++) {
        if (dist[i] == INF) {
          if (!spfa(graph, wormhole, dist, i, N)) {
            hasInfiniteLoop = true;
            break;
          }
        }
      }
      System.out.println(hasInfiniteLoop ? "YES" : "NO");
    }
  }
  private static boolean spfa(List<List<Node>> graph, List<List<Node>> wormhole, long[] dist, int start, int N) {

    int[] visitCount = new int[N + 1];
    boolean[] isInQueue = new boolean[N + 1];
    dist[start] = 0;

    Queue<Integer> q = new ArrayDeque<>();
    q.add(start);
    visitCount[start]++;
    isInQueue[start] = true;

    while (!q.isEmpty()) {
      Integer curIdx = q.poll();
      isInQueue[curIdx] = false;

      if (!searchNextNode(graph, q, dist, visitCount, isInQueue, curIdx, N)) return false;

      if (!searchNextNode(wormhole, q, dist, visitCount, isInQueue, curIdx, N)) return false;
    }
    return true;
  }
  private static boolean searchNextNode(List<List<Node>> graph, Queue<Integer> q, long[] dist, int[] visitCount, boolean[] isInQueue, Integer curIdx, int N) {
    for (int i = 0; i < graph.get(curIdx).size(); i++) {
      Node nxtNode = graph.get(curIdx).get(i);

      if (dist[nxtNode.idx] > dist[curIdx] + nxtNode.cost) {
        dist[nxtNode.idx] = dist[curIdx] + nxtNode.cost;

        if (!isInQueue[nxtNode.idx]) {
          q.add(nxtNode.idx);
          isInQueue[nxtNode.idx] = true;

          if (++visitCount[nxtNode.idx] == N) {
            return false;
          }
        }
      }
    }
    return true;
  }
  private static long[] initDistTable(int N) {

    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);

    return dist;
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