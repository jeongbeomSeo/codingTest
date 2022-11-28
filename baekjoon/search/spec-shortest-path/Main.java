import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
  static int E,V;
  static ArrayList<ArrayList<Node>> graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점 E 와 간선 V 입력 받기
    E = Integer.parseInt(st.nextToken());
    V = Integer.parseInt(st.nextToken());

    // 그래프 초기화
    graph = new ArrayList<>();
    for(int i = 0; i < E + 1; i++) {
      graph.add(new ArrayList<>());
    }

    // 간선 입력 받기
    for(int i = 0; i < V; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(node1).add(new Node(node2, cost));
      graph.get(node2).add(new Node(node1, cost));
    }

    // 임의의 두 정점 입력 받기
    st = new StringTokenizer(br.readLine());
    int v1 = Integer.parseInt(st.nextToken());
    int v2 = Integer.parseInt(st.nextToken());

    int[] dist = new int[E + 1];
    Arrays.fill(dist, INF);


    int v1Tov2 = ShortestPath(v1, v2, dist);
    if(v1Tov2 == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int node1Tov1 = ShortestPath(1, v1, dist);
    if(node1Tov1 == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int node1Tov2 = ShortestPath(1, v2, dist);
    if(node1Tov2 == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int v1TonodeE = ShortestPath(v1, E, dist);
    if(v1TonodeE == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int v2TonodeE = ShortestPath(v2, E, dist);
    if(v2TonodeE == -1) {
      System.out.print(-1);
      return;
    }

    int case1 = node1Tov1 + v2TonodeE;
    int case2 = node1Tov2 + v1TonodeE;

    if(case1 < case2) {
      System.out.print(case1 + v1Tov2);
      return;
    }
    else {
      System.out.print(case2 + v1Tov2);
      return;
    }

  }
  static int ShortestPath(int start, int end, int[] dist) {
    dist[start] = 0;

    PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

    q.add(new Node(start, 0));
    while(!q.isEmpty()) {
      Node curNode = q.poll();

      if(curNode.idx == end) {
        if(curNode.cost == INF) return -1;
        else return dist[curNode.idx];
      }

      if(dist[curNode.idx] < curNode.cost) continue;

       for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
         Node adjNode = graph.get(curNode.idx).get(i);

         if(dist[adjNode.idx] > curNode.cost + adjNode.cost) {
           dist[adjNode.idx] = curNode.cost + adjNode.cost;
           q.add(new Node(adjNode.idx, dist[adjNode.idx]));
         }
       }

    }

    return -1;
  }
}
