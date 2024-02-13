import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final Long MIN = Long.MIN_VALUE;
  private static final Long INF = Long.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<Node>[] graph = initGraph(N);
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int src = Integer.parseInt(st.nextToken());
      int dst = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph[src].add(new Node(dst, cost));
    }

    int[] cityCost = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      cityCost[i] = Integer.parseInt(st.nextToken());
    }

    long result = bellmanFord(graph, cityCost, N, start, end);

    if (result == INF) System.out.println("Gee");
    else if (result == MIN) System.out.println("gg");
    else System.out.println(result);
  }
  private static long bellmanFord(ArrayList<Node>[] graph, int[] cityCost, int N, int start, int end) {
    int[] visitCount = new int[N];
    boolean[] isINFLoop = new boolean[N];
    long[] distTable = initDistTable(cityCost, N, start);

    Queue<Integer> q = new ArrayDeque<>();
    q.add(start);

    while (!q.isEmpty()) {
      int curIdx = q.poll();

      if (isINFLoop[curIdx]) continue;

      for (int i = 0; i < graph[curIdx].size(); i++) {
        Node nextNode = graph[curIdx].get(i);

        long nextCost = calcCost(distTable, cityCost, curIdx, nextNode);
        if (distTable[nextNode.dst] < nextCost) {
          if (visitCount[nextNode.dst] == N && !isINFLoop[nextNode.dst]) {
            // nextNode.dst 기점으로 dfs 진행하면서 isINFLoop true 로 변경해주기
            // dst에 도달 가능하면 함수 종료 후 "GEE" 출력하도록 return 값 설정
            if (bfs(graph, nextNode.dst, end, isINFLoop, N)) {
              return INF;
            }
            continue;
          }
          distTable[nextNode.dst] = nextCost;
          q.add(nextNode.dst);
          visitCount[nextNode.dst]++;
        }
      }
    }

    return distTable[end];
  }
  private static long calcCost(long[] distTable, int[] cityCost, int curIdx, Node nextNode) {
    return distTable[curIdx] - nextNode.cost + cityCost[nextNode.dst];
  }
  private static boolean bfs(ArrayList<Node>[] graph, int start, int end, boolean[] isINFLoop, int N) {

    boolean[] isVisited = new boolean[N];

    Queue<Integer> q = new ArrayDeque<>();
    q.add(start);
    isVisited[start] = isINFLoop[start] = true;

    while (!q.isEmpty()) {
      int curIdx = q.poll();

      for (int i = 0; i < graph[curIdx].size(); i++) {
        int nextIdx = graph[curIdx].get(i).dst;

        if (nextIdx == end) return true;
        if (!isVisited[nextIdx]) {
          q.add(nextIdx);
          isVisited[nextIdx] = isINFLoop[nextIdx] = true;
        }
      }
    }

    return false;
  }
  private static long[] initDistTable(int[] cityCost, int N, int start) {
    long[] distTable = new long[N];

    Arrays.fill(distTable, MIN);
    distTable[start] = cityCost[start];

    return distTable;
  }
  private static ArrayList<Node>[] initGraph(int N) {
    ArrayList<Node>[] graph = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }

    return graph;
  }
}
class Node {
  int dst;
  int cost;

  Node(int dst, int cost) {
    this.dst = dst;
    this.cost = cost;
  }
}