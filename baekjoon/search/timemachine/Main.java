import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int v;
  int w;
  int cost;

  Node(int v, int w, int cost) {
    this.v = v;
    this.w = w;
    this.cost = cost;
  }
}

public class Main {
  static ArrayList<ArrayList<Node>> graph;
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 도시의 개수 와 버스
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for(int i  = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }


    // 버스의 정보
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(v).add(new Node(v, w, cost));
    }

    // dist[] 초기화
    // N이 최대 500, M이 최대 6,000, 음의 간선 최소 수치가 -10,000라는 것을 고려
    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);
    dist[1] = 0;

    // 벨만-포드 알고리즘
    BellmanFord(N, dist);
  }
  static void BellmanFord(int N, long[] dist) {
    // 순환 사이클을 도는 도시 넣어줄 Collection 생성
    boolean cycle = false;
    // N - 1 번 실행
    for(int count = 0; count < N - 1; count++) {
      // 모든 버스 정보에 대해서 BellMan Ford 실행
      for(int i = 1; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // cf) adjNode.v == i
            Node adjNode = graph.get(i).get(j);
            dist[adjNode.w] = Math.min(dist[adjNode.w], dist[adjNode.v] + adjNode.cost);
          }
        }
      }
    }
    for(int i = 1; i < graph.size(); i++) {
      if(dist[i] != INF) {
        for(int j = 0; j < graph.get(i).size(); j++) {
          Node adjNode = graph.get(i).get(j);
          if (dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
            dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
            cycle = true;
          }
        }
      }
    }
    // 도시로 가는 경로가 무한히 오래전으로 되돌릴 수 있는 경우 즉, 모든 경로가 음의 사이클 존재
    if(cycle) System.out.println(-1);
    else {
      for(int i = 2; i < dist.length; i++) {
        // 해당 도시로 가는 경로조차 없는 경우
        if(dist[i] == INF) System.out.println(-1);
        // 정상적인 경로
        else System.out.println(dist[i]);
      }
    }
  }
}
