import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Edge[] edges = new Edge[M];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int src = Integer.parseInt(st.nextToken());
      int dst = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      edges[i] = new Edge(src, dst, cost);
    }

    int[] city = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      city[i] = Integer.parseInt(st.nextToken());
    }

    bellmanFord(edges, city, start, end, N, M);
  }
  private static void bellmanFord(Edge[] edges, int[] city, int start, int end, int N, int M) {

    long[] dist = initDistTable(city, N, start);

    for (int time = 0; time < N; time++) {

      for (int i = 0; i < M; i++) {
        Edge curEdge = edges[i];

        if (dist[curEdge.src] != INF && dist[curEdge.dst] < dist[curEdge.src] + getMoneyWhenGoNextCity(city, curEdge)) {
          dist[curEdge.dst] = dist[curEdge.src] + getMoneyWhenGoNextCity(city, curEdge);
        }
      }
    }

    if (!canGoTargetCity(dist, end)) {
      System.out.println("gg");
      return;
    }

    if (hasInfiniteCycle(edges, city, dist, end, N, M)) {
      System.out.println("Gee");
      return;
    }

    System.out.println(dist[end]);
  }
  private static boolean hasInfiniteCycle(Edge[] edges, int[] city, long[] dist, int end, int N, int M) {

    boolean[] isVisited = new boolean[N];
    for (int i = 0; i < M; i++) {
      Edge curEdge = edges[i];

      if (dist[curEdge.src] != INF && dist[curEdge.dst] < dist[curEdge.src] + getMoneyWhenGoNextCity(city, curEdge)) {
        if (!isVisited[curEdge.src] && dfs(edges, isVisited, curEdge.src, end, M)) {
          return true;
        }
      }
    }
    return false;
  }
  private static boolean dfs(Edge[] edges, boolean[] isVisited, int idx, int end, int M) {

    isVisited[idx] = true;

    Deque<Integer> stack = new ArrayDeque<>();
    stack.add(idx);

    while (!stack.isEmpty()) {
      int curIdx = stack.peek();

      if (curIdx == end) return true;

      boolean hasNearNode = false;
      for (int i = 0; i < M; i++) {
        if (edges[i].src == curIdx && !isVisited[edges[i].dst]) {
          stack.add(edges[i].dst);
          isVisited[edges[i].dst] = true;
          hasNearNode = true;
          break;
        }
      }
      if (!hasNearNode) stack.pop();
    }
    return false;
  }
  private static boolean canGoTargetCity(long[] dist, int end) {
    return dist[end] != INF;
  }
  private static int getMoneyWhenGoNextCity(int[] city, Edge edge) {
    return city[edge.dst] - edge.cost;
  }
  private static long[] initDistTable(int[] city, int N, int start) {

    long[] dist = new long[N];

    Arrays.fill(dist, INF);

    dist[start] = city[start];
    return dist;
  }
}
class Edge {
  int src;
  int dst;
  int cost;

  Edge (int src, int dst, int cost) {
    this.src = src;
    this.dst = dst;
    this.cost = cost;
  }
}