import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int TC = Integer.parseInt(br.readLine());
    for(int count = 0; count < TC; count++) {

      st = new StringTokenizer(br.readLine());

      // 지점의 수, 도로의 개수, 웜홀의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
      }

      // 도로 정보
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(n1).add(new Node(n2, cost));
        graph.get(n2).add(new Node(n1, cost));
      }

      for(int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(n1).add(new Node(n2, -cost));
      }

      boolean hasNegativeCycle = false;
      for(int i = 1; i < N + 1; i++) {
        int[] dist = new int[N + 1];
        if(SPFA(N, i, dist, graph)) {
          hasNegativeCycle = true;
          break;
        }
      }
      if(hasNegativeCycle) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  static boolean SPFA(int N, int start, int[] dist, ArrayList<ArrayList<Node>> graph) {
    Arrays.fill(dist, INF);
    dist[start] = 0;

    boolean[] inQueue = new boolean[N + 1];
    int[] cycle = new int[N + 1];

    Queue<Node> q = new LinkedList<>();
    q.add(new Node(start, 0));
    inQueue[start] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      inQueue[curNode.idx] = false;

      for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node adjNode = graph.get(curNode.idx).get(i);

        if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
          dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
          cycle[adjNode.idx]++;
          if(cycle[adjNode.idx] >= N) return true;

          if(!inQueue[adjNode.idx]) {
            q.add(new Node(adjNode.idx, dist[adjNode.idx]));
            inQueue[adjNode.idx] = true;
          }
        }
      }
    }
    return false;
  }
}