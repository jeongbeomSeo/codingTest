import java.io.*;
import java.util.*;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static ArrayList<ArrayList<Node>> graph;
  static ArrayList<Integer>[] trace;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      if (N == 0 && M == 0) break;

      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      graph = new ArrayList<>();
      trace = new ArrayList[N];

      for (int i = 0; i < N; i++) {
        graph.add(new ArrayList<>());
        trace[i] = new ArrayList<>();
      }

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int nodeIdx = Integer.parseInt(st.nextToken());
        int next = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(nodeIdx).add(new Node(next, cost));
      }

      int[] dist = initTable(N, start);

      dijkstra(graph, trace, dist, start, end);

      boolean[][] isDeleteNode = new boolean[N][N];
      backtracking(isDeleteNode, trace, end);

      Arrays.fill(dist, INF);
      dist[start] = 0;
      dijkstraPostAction(graph, isDeleteNode, dist, start, end);

      System.out.println((dist[end] != INF ? dist[end] : -1));
    }
  }
  private static void backtracking(boolean[][] isDeleteNode, ArrayList<Integer>[] trace, int idx) {

    for (int prevIdx : trace[idx]) {
      if (!isDeleteNode[prevIdx][idx]) {
        isDeleteNode[prevIdx][idx] = true;
        backtracking(isDeleteNode, trace, prevIdx);
      }
    }
  }
  private static void dijkstraPostAction(ArrayList<ArrayList<Node>> graph, boolean[][] isDeleteNode, int[] dist, int start, int end) {

    Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

    pq.add(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node curNode = pq.poll();

      if (dist[curNode.idx] < curNode.cost) continue;

      if (curNode.idx == end) return;

      for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node nxtNode = graph.get(curNode.idx).get(i);

        if (!isDeleteNode[curNode.idx][nxtNode.idx] && dist[nxtNode.idx] > dist[curNode.idx] + nxtNode.cost) {
          dist[nxtNode.idx] = dist[curNode.idx] + nxtNode.cost;

          pq.add(new Node(nxtNode.idx, dist[nxtNode.idx]));
        }
      }
    }
  }
  private static void dijkstra(ArrayList<ArrayList<Node>> graph, ArrayList<Integer>[] trace, int[] dist, int start, int end) {

    Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

    pq.add(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node curNode = pq.poll();

      if (curNode.idx == end) continue;

      if (dist[curNode.idx] < curNode.cost) continue;

      for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node nxtNode = graph.get(curNode.idx).get(i);

        if (dist[nxtNode.idx] == dist[curNode.idx] + nxtNode.cost) {
          // Update trace
          updateTrace(trace, curNode.idx, nxtNode.idx);
        }

        if (dist[nxtNode.idx] > dist[curNode.idx] + nxtNode.cost) {
          dist[nxtNode.idx] = dist[curNode.idx] + nxtNode.cost;

          pq.add(new Node(nxtNode.idx, dist[nxtNode.idx]));
          // delete current trace & update
          trace[nxtNode.idx].clear();
          updateTrace(trace, curNode.idx, nxtNode.idx);
        }
      }
    }
  }
  private static void updateTrace(ArrayList<Integer>[] trace, int startIdx, int endIdx) {
    trace[endIdx].add(startIdx);
  }
  private static int[] initTable(int N, int start) {

    int[] table = new int[N];
    Arrays.fill(table, INF);

    table[start] = 0;

    return table;
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