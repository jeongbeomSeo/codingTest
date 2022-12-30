import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 개수, 간선의 개수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    if(N == 0) return;

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    for(int i = 1; i < N + 1; i++) {
      Collections.sort(graph.get(i));
    }

    boolean[] visited = new boolean[N + 1];
    minsik(1, graph, visited);
  }
  static void minsik(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {

    visited[node] = true;
    System.out.print(node + " ");

    // 방문할 수 있는 노드가 없을 시에 break
    while(true) {
      ArrayList<Integer> notVisitied = new ArrayList<>();
      for(int i = 0; i < graph.get(node).size(); i++) {
        int adjNode = graph.get(node).get(i);
        if(!visited[adjNode]) notVisitied.add(adjNode);
      }
      int len = notVisitied.size();
      if(len == 0) break;
      else if(len % 2 == 0 || len == 1) minsik(notVisitied.get(0), graph, visited);
      else minsik(notVisitied.get(len / 2), graph, visited);
    }
  }
}
