import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int max = 0;
  static int max_Idx = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    StringTokenizer st;
    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int parNode = Integer.parseInt(st.nextToken());
      while (true) {
        int node = Integer.parseInt(st.nextToken());
        if (node == -1) break;
        int cost = Integer.parseInt(st.nextToken());

        graph.get(parNode).add(new Node(node, cost));
        graph.get(node).add(new Node(parNode, cost));
      }
    }


    boolean[] isVisited = new boolean[N + 1];
    Dfs(graph, 1, 0, isVisited);

    Arrays.fill(isVisited, false);
    Dfs(graph, max_Idx, 0, isVisited);
    System.out.println(max);
  }
  static void Dfs(ArrayList<ArrayList<Node>> graph, int node, int cost, boolean[] isVisited ) {
    isVisited[node] = true;

    if (max < cost) {
      max = cost;
      max_Idx = node;
    }

    for (Node childNode: graph.get(node)) {
      if (!isVisited[childNode.idx]) {
        Dfs(graph, childNode.idx, cost + childNode.cost, isVisited);
      }
    }
  }
}


class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}
