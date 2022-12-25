import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int count;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 수, 간선의 수, 시작 정점
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());

    boolean[] visitied = new boolean[N + 1];

    ArrayList<Integer>[] graph = new ArrayList[N + 1];
    for(int i = 0; i < graph.length; i++) {
      graph[i] = new ArrayList<>();
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      graph[n1].add(n2);
      graph[n2].add(n1);
    }

    // 오름차순 정렬
    for(int i = 1; i < graph.length; i++) {
      Collections.sort(graph[i]);
    }

    count = 1;

    int[] visitOrder = new int[N + 1];
    DFS(visitOrder,visitied, graph, R);

    for(int i = 1; i < visitied.length; i++) {
      System.out.println(visitOrder[i]);
    }
  }
  static void DFS(int[] visitOrder, boolean[] visited, ArrayList<Integer>[] graph, int curNode) {
    visited[curNode] = true;
    visitOrder[curNode] = count++;

    for(int adjNode: graph[curNode]) {
      if(!visited[adjNode]) {
        DFS(visitOrder, visited, graph, adjNode);
      }
    }
  }
}
