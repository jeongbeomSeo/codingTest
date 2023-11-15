import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int INF = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int src = Integer.parseInt(st.nextToken());
      int dst = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(src).add(new Node(dst, cost));
    }

    int[] city = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      city[i] = Integer.parseInt(st.nextToken());
    }

    long[] dist = initDistTable(city, N, start);

    spfa(graph, city, dist, start, end, N);

  }
  private static void spfa(ArrayList<ArrayList<Node>> graph, int[] city, long[] dist, int start, int end, int N) {

    int[] visitCount = new int[N];
    boolean[] isInQueue = new boolean[N];
    boolean[] isVisited = new boolean[N];

    Queue<Integer> q = new ArrayDeque<>();
    q.add(start);
    visitCount[start]++;
    isInQueue[start] = true;

    while (!q.isEmpty()) {
      Integer curIdx = q.poll();

      isInQueue[curIdx] = false;

      for (int i = 0; i < graph.get(curIdx).size(); i++) {
        Node nxtNode = graph.get(curIdx).get(i);

        if (dist[nxtNode.idx] < dist[curIdx] + getMoneyWhenVisitCity(city, nxtNode)) {
          dist[nxtNode.idx] = dist[curIdx] + getMoneyWhenVisitCity(city, nxtNode);

          if (!isInQueue[nxtNode.idx]) {
            if (++visitCount[nxtNode.idx] == N) {
              if (dist[end] == INF) {
                break;
              }
              if (!isVisited[nxtNode.idx] && dfs(graph, isVisited, nxtNode.idx, end)) {
                System.out.println("Gee");
                return;
              }
            } else {
              q.add(nxtNode.idx);
              isInQueue[nxtNode.idx] = true;
            }
          }
        }
      }
    }

    System.out.println(dist[end]);
  }
  private static int getMoneyWhenVisitCity(int[] city, Node node) {
    return city[node.idx] - node.cost;
  }
  private static boolean dfs(ArrayList<ArrayList<Node>> graph, boolean[] isVisited, int src, int end) {

    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(src);
    isVisited[src] = true;

    while (!stack.isEmpty()) {
      int curIdx = stack.peek();

      if (curIdx == end) return true;

      boolean hasNearNode = false;
      for (int i = 0; i < graph.get(curIdx).size(); i++) {
        Node nxtNode = graph.get(curIdx).get(i);

        if (!isVisited[nxtNode.idx]) {
          stack.push(nxtNode.idx);
          hasNearNode = true;
          isVisited[nxtNode.idx] = true;
          break;
        }
      }
      if (!hasNearNode) stack.pop();
    }

    return false;
  }
  private static long[] initDistTable(int[] city, int N, int start) {

    long[] dist = new long[N];
    Arrays.fill(dist, INF);

    dist[start] = city[start];

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