import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Node {
  int s;
  int idx;
  int cost;

  Node(int s, int idx, int cost) {
    this.s = s;
    this.idx = idx;
    this.cost = cost;
  }
}
public class Main {
  static int INF = Integer.MAX_VALUE;

  static ArrayList<Node> path = new ArrayList<>();
  static Set<Node> shortestPath = new LinkedHashSet<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 노드와 간선 입력 받기
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // 시작점과 도착점 입력 받기
    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    // 간선 넣어줄 그래프 생성
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for(int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }

    // 간선 입력 받기
    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      // 단방향 그래프
      graph.get(node1).add(new Node(node1, node2, cost));
    }

    int[] dist = new int[N];

    Dijkstra(start, end, dist, graph, 0);

    checkVertex(start, end, dist, graph, 0);

    removeVertex(shortestPath, graph);

    Dijkstra(start, end, dist, graph, 1);

  }
  static void Dijkstra(int start, int end, int[] dist, ArrayList<ArrayList<Node>> graph, int version) {
    Arrays.fill(dist, INF);
    dist[start] = 0;

    PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

    q.add(new Node(start, start, 0));

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      if(curNode.idx == end)  {
        if(version == 1) {
          System.out.println(dist[curNode.idx] != INF ? dist[curNode.idx] : -1);
        }
        return;
      }

      if(dist[curNode.idx] < curNode.cost) continue;;

      for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node adjNode = graph.get(curNode.idx).get(i);

        if(dist[adjNode.idx] > curNode.cost + adjNode.cost) {
          dist[adjNode.idx] = curNode.cost + adjNode.cost;

          q.add(new Node(curNode.idx, adjNode.idx, dist[adjNode.idx]));
        }
      }
    }
  }
  static void checkVertex(int curNodeIdx, int end, int[] dist, ArrayList<ArrayList<Node>> graph, int distance) {
    // DFS 사용
    // 경로 추적할 ArrayList와 삭제할 간선을 저장해둘 Set 둘 다 이용

    if(curNodeIdx == end && dist[curNodeIdx] == distance) {
      for(int i = 0; i < path.size(); i++) {
        shortestPath.add(path.get(i));
      }
    }

    for(Node nxtNode : graph.get(curNodeIdx)) {
      path.add(nxtNode);
      checkVertex(nxtNode.idx, end, dist, graph, distance + nxtNode.cost);
      path.remove(nxtNode);
    }
  }
  static void removeVertex(Set<Node> shortestPath, ArrayList<ArrayList<Node>> graph) {
    Iterator<Node> node = shortestPath.iterator();
    while (node.hasNext()) {
      Node removeNode = node.next();
      // 이중 ArrayList 이지만 되는지 의문
      graph.get(removeNode.s).remove(removeNode);
    }
  }
}