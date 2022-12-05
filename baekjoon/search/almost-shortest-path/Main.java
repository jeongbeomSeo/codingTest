import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
  int srcIdx;
  int idx;
  int cost;

  // 촤단 경로 간선 삭제 할 때 srcIdx가 있으면 화살표의 도착 지점만 알아서 graph를 전부 확인해야 하는 문제를 해결
  Node(int srcIdx, int idx, int cost) {
    this.srcIdx = srcIdx;
    this.idx = idx;
    this.cost = cost;
  }

}
public class Main {
  static int INF = Integer.MAX_VALUE;
  static ArrayList<ArrayList<Node>> path;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (true){
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 노드와 간선 수 입력 받기
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      if(N == 0 && M == 0) break;

      // 시작점과 도착점 입력 받기
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      // 그래프와 최단 경로 간선 넣어 줄 List 초기화
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      path = new ArrayList<>();
      for(int i = 0; i < N; i++) {
        graph.add(new ArrayList<>());
        path.add(new ArrayList<>());
      }

      // graph에 간선 정보 넣어 주기
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int node1 = Integer.parseInt(st.nextToken());
        int node2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(node1).add(new Node(node1, node2, cost));
      }

      int[] dist = new int[N];

      SpecDijkstra(start, end, dist, graph, true);

      SpecDijkstra(start, end, dist, graph, false);
    }
  }
  static void SpecDijkstra(int start, int end, int[] dist, ArrayList<ArrayList<Node>> graph, boolean remove) {
    // if remove == true 최단경로 찾기 + 최단 경로 간선 삭제
    // else 최단경로 찾기

    Arrays.fill(dist, INF);
    dist[start] = 0;

    PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
    q.add(new Node(start , start, 0));

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      if(dist[curNode.idx] < curNode.cost) continue;

      if(curNode.idx == end) {
        if(remove) removeShortestPath(end, graph);
        else {
          System.out.println(dist[end]);
          return;
        }
      }

      if(dist[curNode.idx] > dist[end]) return;

      for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node adjNode = graph.get(curNode.idx).get(i);

        if(dist[adjNode.idx] > curNode.cost + adjNode.cost) {
          dist[adjNode.idx] = curNode.cost + adjNode.cost;

          q.add(new Node(curNode.idx, adjNode.idx, dist[adjNode.idx]));
          // 최단 경로 간선 넣어주기
          if(remove) {
            if(path.get(adjNode.idx).size() != 0) path.get(adjNode.idx).clear();
            path.get(curNode.idx).forEach(node -> path.get(adjNode.idx).add(node));
            path.get(adjNode.idx).add(adjNode);
          }
        }
        else if(dist[adjNode.idx] == curNode.cost + adjNode.cost) {
          if(remove) {
            path.get(curNode.idx).forEach(node -> {
              if(!path.get(adjNode.idx).contains(node)) path.get(adjNode.idx).add(node);
            });
            path.get(adjNode.idx).add(adjNode);
          }
        }
      }
    }
    if(!remove && dist[end] == INF) System.out.println(-1);
  }

  static void removeShortestPath(int end, ArrayList<ArrayList<Node>> graph) {
    for(int i = 0; i < path.get(end).size(); i++) {
      Node node = path.get(end).get(i);
      graph.get(node.srcIdx).remove(node);
    }
  }

}