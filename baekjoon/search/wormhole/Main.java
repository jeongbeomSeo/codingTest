import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

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
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int TC = Integer.parseInt(br.readLine());

    for (int count = 0; count < TC; count++) {
      st = new StringTokenizer(br.readLine());
      // 지점의 수, 도로의 개수, 웜홀의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      // dist[] 초기화
      long[] dist = new long[N + 1];

      // graph 초기화
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
      }

      // 도로의 정보
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 도로는 무방향
        graph.get(n1).add(new Node(n1, n2, cost));
        graph.get(n2).add(new Node(n2, n1, cost));
      }

      // 웜홀의 정보
      for(int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 웜홀은 방향 and 음수 Cost
        graph.get(n1).add(new Node(n1, n2, -cost));
      }

      boolean check = false;

      // 모든 지점을 다 확인(사직점 미지정)
      for(int i = 1; i < N + 1; i++) {
        if(BellmanFord(N, i, dist, graph)) {
          System.out.println("YES");
          check = true;
          break;
        }
      }
      // 음의 사이클 발견 안될 시
      if(!check) System.out.println("NO");
    }
  }
  static boolean BellmanFord(int N, int start, long[] dist, ArrayList<ArrayList<Node>> graph) {
    // 음의 사이클 발견시 true 아니면 false Return
    Arrays.fill(dist, INF);
    dist[start] = 0;

    // 벨만-포드 N 회 실행
    for(int count = 1; count < N + 1; count++) {
      boolean update = false;
      for(int i = 1; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // adjNode.v == i
            Node adjNode = graph.get(i).get(j);
            if(dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
              if(count == N || dist[start] < 0) return true;
              dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
              update = true;
            }
          }
        }
      }
      if(!update) break;
    }
    return false;
  }
}