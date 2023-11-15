import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    List<List<Node>> graph = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken()) - 1;
      int node2 = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());

      graph.get(node1).add(new Node(node2, cost));
      graph.get(node2).add(new Node(node1, cost));
    }

    st = new StringTokenizer(br.readLine());
    int[] endPoint = new int[2];
    endPoint[0] = Integer.parseInt(st.nextToken()) - 1;
    endPoint[1] = Integer.parseInt(st.nextToken()) - 1;

    long[] dist = initDistTable(N, 0);

    if(dijkstra(graph, dist, 0, endPoint, N) == INF) {
      System.out.println(-1);
      return;
    }

    long[] nextDist = initDistTable(N, N - 1);

    if(dijkstra(graph, nextDist, N - 1, endPoint, N) == INF) {
      System.out.println(-1);
      return;
    }

    long[] lastDist = initDistTable(N, endPoint[0]);
    int[] lastEndPoint = new int[1];
    lastEndPoint[0] = endPoint[1];
    long middleCost = dijkstra(graph, lastDist, endPoint[0], lastEndPoint, N);

    if (middleCost == INF) {
      System.out.println("-1");
      return;
    }

    long min = Math.min(dist[endPoint[0]] + nextDist[endPoint[1]], dist[endPoint[1]] + nextDist[endPoint[0]]) + middleCost;

    System.out.println(min);
  }
  private static long dijkstra(List<List<Node>> graph, long[] dist, int start, int[] endPoint, int N) {

    Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
    pq.add(new Node(start, 0));

    int count = 0;
    int length = endPoint.length;
    boolean[] isVisitedEndPoint = new boolean[N];
    while (!pq.isEmpty()) {
      Node curNode = pq.poll();

      if (dist[curNode.idx] < curNode.cost) continue;

      if (isEndPoint(endPoint, curNode.idx, isVisitedEndPoint)) {
        count++;
      }

      if (count == length) {
        if (length == 1) {
          return dist[curNode.idx];
        } else {
          return -1;
        }
      }

      for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node nxtNode = graph.get(curNode.idx).get(i);

        if (dist[nxtNode.idx] > dist[curNode.idx] + nxtNode.cost) {
          dist[nxtNode.idx] = dist[curNode.idx] + nxtNode.cost;

          pq.add(new Node(nxtNode.idx, dist[nxtNode.idx]));
        }
      }
    }

    return INF;
  }
  private static boolean isEndPoint(int[] endPoint, int curNodeIdx, boolean[] isVisitedEndPoint) {
    for (int endPointIdx : endPoint) {
      if (endPointIdx == curNodeIdx && !isVisitedEndPoint[curNodeIdx]) {
        isVisitedEndPoint[curNodeIdx] = true;
        return true;
      }
    }
    return false;
  }
  private static long[] initDistTable(int N, int start) {
    long[] dist = new long[N];
    Arrays.fill(dist, INF);

    dist[start] = 0;

    return dist;
  }
}
class Node {
  int idx;
  long cost;

  Node (int idx, long cost) {
    this.idx = idx;
    this.cost = cost;
  }
}