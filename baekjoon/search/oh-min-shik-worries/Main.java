import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  /*
  이 방식 보다 아래 방식이 후에 경로 추적하기에 편할 것임.
  int v;
  int w;
  int cost;

  Node(int v, int w, int cost) {
    this.v = v;
    this.w = w;
    this.cost = cost;
  }
  */
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
    StringTokenizer st= new StringTokenizer(br.readLine());

    // 도시의 수, 시작 노드, 끝 노드, 교통 수단의 개수
    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // 그래프 초기화 (0번 노드부터 시작)
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for(int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }

    // 그래프 정보
    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      // 단방향 그래프
      graph.get(n1).add(new Node(n2, cost));
    }

    // 도시 도착시 획득 비용
    st = new StringTokenizer(br.readLine());
    int[] cityFee = new int[N];
    for(int i = 0; i < N; i++) {
      cityFee[i] = -Integer.parseInt(st.nextToken());
    }

    // 미리 획득 비용을 그래프에 적용 시키기
    for(int i = 0; i < graph.size(); i++) {
      for(int j = 0; j < graph.get(i).size(); j++) {
        Node node = graph.get(i).get(j);
        node.cost += cityFee[node.idx];
      }
    }

    // dist 배열 초기화
    long[] dist = new long[N];
    Arrays.fill(dist, INF);
    dist[start] = cityFee[start];

    // 벨만 포드 알고리즘
    if(BellmanFord(end, N, graph, dist)) {
      if(dist[end] == INF) System.out.println("gg");
      else System.out.println(-dist[end]);
    }
  }
  static boolean BellmanFord(int end, int N, ArrayList<ArrayList<Node>> graph, long[] dist) {

    Set<Integer> negativeCycle = new HashSet<>();

    // N - 1번 순회
    for(int count = 0; count < N -1; count++) {
      boolean update = false;

      for(int i = 0; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // adjNode의 시작 노드는 i, 도착 노드가 idx;
            Node adjNode = graph.get(i).get(j);
            dist[adjNode.idx] = Math.min(dist[adjNode.idx], dist[i] + adjNode.cost);
            update = true;
          }
        }
      }
      if(!update) break;
    }

    for(int i = 0; i < graph.size(); i++) {
      if(dist[i] != INF) {
        for(int j = 0 ; j < graph.get(i).size(); j++) {
          Node adjNode = graph.get(i).get(j);
          if(dist[adjNode.idx] > dist[i] + adjNode.cost) {
            // 음의 사이클 노드 전부 저장
            negativeCycle.add(adjNode.idx);
            negativeCycle.add(i);
          }
        }
      }
    }

    Iterator<Integer> itIdx = negativeCycle.iterator();
    // BFS 혹은 DFS로 경로 Check
    // end노드까지 도달 가능 시 => "Gee" 불가능 할 시 => return true
    while (itIdx.hasNext()) {
      int idx = itIdx.next();
      if(checkPath(N, idx, end, graph)) {
        System.out.println("Gee");
        return false;
      }
    }
    return true;
  }
  static boolean checkPath(int N, int start, int end, ArrayList<ArrayList<Node>> graph) {
    // BFS 사용
    Queue<Integer> q = new LinkedList();
    boolean[] isVisited = new boolean[N];
    q.add(start);
    isVisited[start] = true;
    while (!q.isEmpty()) {
      if(isVisited[end] == true) return true;
      int idx = q.poll();

      for(int i = 0; i < graph.get(idx).size(); i++) {
        Node adjNode = graph.get(idx).get(i);
        //if(adjNode.idx == end) return true;

        if(!isVisited[adjNode.idx]) {
          q.add(adjNode.idx);
          isVisited[adjNode.idx] = true;
        }
      }
    }
    return false;
  }
}