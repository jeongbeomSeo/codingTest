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

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0 ; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      if (n1 == n2) continue;
      if (graph.get(n1).contains(n2)) continue;
      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    for (int i = 1; i < N + 1; i++) {
      Collections.sort(graph.get(i));
    }

    boolean[] isVisited = new boolean[N + 1];

    MinsikDfs(isVisited, graph, 1);

  }
  static void MinsikDfs(boolean[] isVisited,  ArrayList<ArrayList<Integer>> graph, int node) {
    ArrayList<Integer> notVisitedNode = new ArrayList<>();
    isVisited[node] = true;
    System.out.print(node + " ");

    while (true) {
      for (int i = 0; i < graph.get(node).size(); i++) {
        int curNode = graph.get(node).get(i);
        if (!isVisited[curNode]) {
          notVisitedNode.add(curNode);
        }
      }
      int size = notVisitedNode.size();
      if (size == 0) break;

      if (size == 1) MinsikDfs(isVisited, graph, notVisitedNode.get(0));
      else if (size % 2 == 1) {
        int idx = size / 2;
        MinsikDfs(isVisited, graph, notVisitedNode.get(idx));
      }
      else {
        MinsikDfs(isVisited, graph, notVisitedNode.get(0));
      }
      notVisitedNode.clear();
    }
  }
}