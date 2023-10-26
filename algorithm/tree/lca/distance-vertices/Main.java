import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st;

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }


    for (int i = 0; i < N - 1; i++){
      st = new StringTokenizer(br.readLine());

      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(n1).add(new Node(n2, cost));
      graph.get(n2).add(new Node(n1, cost));
    }

    boolean[] isVisited = new boolean[N + 1];
    int[] level = new int[N + 1];

    int k = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int[][][] parent= new int[N + 1][k][2];

    // DFS
    // purpose: level[], parent[]
    DFS(graph, 1, level, parent, 1, isVisited);

    set_spaTable(parent, N, k);

    int M = Integer.parseInt(br.readLine());

    // LCA
    // purpose: find distance
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      bw.write(LCA(parent, level, k, n1, n2) + "\n");
    }

    bw.flush();
    bw.close();
  }
  static void DFS(ArrayList<ArrayList<Node>> graph, int node, int[] level, int[][][] parent, int curLevel, boolean[] isVisited) {
    isVisited[node] = true;
    level[node] = curLevel;

    for (Node childNode : graph.get(node)) {
      if (!isVisited[childNode.idx]) {
        parent[childNode.idx][0][0] = node;
        parent[childNode.idx][0][1] = childNode.cost;
        DFS(graph, childNode.idx, level, parent, curLevel + 1, isVisited);
      }
    }
  }

  static void set_spaTable(int[][][] parent, int N, int k) {
    for (int i = 1; i < k; i++) {
      for (int j = 1; j < N + 1; j++) {
        parent[j][i][0] = parent[parent[j][i - 1][0]][i - 1][0];
        parent[j][i][1] = parent[j][i - 1][1] + parent[parent[j][i - 1][0]][i - 1][1];
      }
    }
  }

  static int LCA(int[][][] parent, int[] level, int k, int n1, int n2) {
    if (level[n1] > level[n2]) {
      int temp = n1;
      n1 = n2;
      n2 = temp;
    }

    int cost1 = 0;
    int cost2 = 0;

    for (int i = k - 1; i >= 0; i--) {
      if (level[n2] - level[n1] >= Math.pow(2, i)) {
        cost2 += parent[n2][i][1];
        n2 = parent[n2][i][0];
      }
    }
    if (n1 == n2) return cost2;

    for (int i = k - 1; i >= 0; i--) {
      if (parent[n1][i][0] != parent[n2][i][0]) {
        cost1 += parent[n1][i][1];
        cost2 += parent[n2][i][1];

        n1 = parent[n1][i][0];
        n2 = parent[n2][i][0];
      }
    }
    cost1 += parent[n1][0][1];
    cost2 += parent[n2][0][1];

    return cost1 + cost2;
  }
}

class Node {
  int idx;
  int cost;

  Node (int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}

