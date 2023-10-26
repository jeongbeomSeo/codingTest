import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int src;
  int idx;
  long cost;

  Node(int src, int idx, long cost) {
    this.src = src;
    this.idx = idx;
    this.cost = cost;
  }
}

public class Main {
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 도시의 개수, 버스 노선의 개수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // 그래프 초기화
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    // 그래프 정보 입력
    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      // 단반향 그래프
      graph.get(n1).add(new Node(n1, n2, cost));
    }

    long[] dist = new long[N +1];
    Arrays.fill(dist, INF);
    dist[1] = 0;

    if(SPFA(N, graph, dist)) {
      for(int i = 2; i < dist.length; i++) {
        if(dist[i] == INF) System.out.println(-1);
        else System.out.println(dist[i]);
      }
    }
  }
  static boolean SPFA(int N, ArrayList<ArrayList<Node>> graph, long[] dist) {
    Queue<Node> queue = new LinkedList<>();
    boolean[] onQueue = new boolean[N + 1];
    int[] cycle = new int[N + 1];

    queue.add(new Node(1, 1, 0));
    cycle[1]++;

    onQueue[1] = true;

    while (!queue.isEmpty()) {
      Node curNode = queue.poll();
      onQueue[curNode.idx] = false;

      for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node adjNode = graph.get(curNode.idx).get(i);

        if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
          dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
          if(!onQueue[adjNode.idx]) {
            queue.add(new Node(curNode.idx, adjNode.idx, dist[adjNode.idx]));
            cycle[adjNode.idx]++;
            if(cycle[adjNode.idx] >= N) {
              System.out.println(-1);
              return false;
            }
            onQueue[adjNode.idx] = true;
         }
        }
      }
    }
    return true;
  }
}